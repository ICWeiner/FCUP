{-
  Um verificador de tautologias em Haskell

  Pedro Vasconcelos, 2010-2013
  Baseado no exemplo de G.Hutton, "Programming in Haskell", 2007, 
  Cambridge University Press
-}

import Data.List (nub)  -- remover repetidos

-- tipo para expressões proposicionais
data Prop = Const Bool        -- constantes
          | Var Char          -- variáveis
          | Neg Prop          -- negação
          | Conj Prop Prop    -- conjunção
          | Disj Prop Prop    -- disjunção
          | Impl Prop Prop    -- implicação
          | Equiv Prop Prop   -- Equivalencia
            deriving (Eq,Show)

-- listas de associações
type Assoc ch v = [(ch,v)]


-- encontrar o valor associado a uma chave
-- parcial: erro se a chave não ocorre
find :: Eq ch => ch -> Assoc ch v -> v
find x assocs = head [v | (x',v)<-assocs, x==x']


-- atribuições de valores a variaveis
type Atrib = Assoc Char Bool

-- calcular o valor duma proposição
valor :: Atrib -> Prop -> Bool
valor s (Const b) = b
valor s (Var x)    = find x s
valor s (Neg p)    = not (valor s p)
valor s (Conj p q) = valor s p && valor s q
valor s (Disj p q) = valor s p || valor s q
valor s (Impl p q) = not (valor s p) || valor s q
valor s (Equiv p q)=(not (valor s p) || valor s q ) && (not (valor s q) || valor s p)


-- gerar todas sequências de `n' boleanos
bits :: Int -> [[Bool]]
bits 0 = [[]]
bits n = [b:bs | bs<-bits (n-1), b<-[False,True]]


-- todas as atribuições às variáveis duma proposição
atribs :: Prop -> [Atrib]
atribs p = map (zip vs) (bits (length vs))
    where vs = nub (vars p)  -- variáveis sem repetições

-- coleciona todas as variáveis duma proposição
vars :: Prop -> [Char]
vars (Const _)   =  []
vars (Var x)     =  [x]
vars (Neg p)     =  vars p
vars (Conj p q)  =  vars p ++ vars q
vars (Disj p q)  =  vars p ++ vars q
vars (Impl p q)  =  vars p ++ vars q
vars (Equiv p q) =  vars p ++ vars q


-- verificar se é taulogia
tautologia :: Prop -> Bool
tautologia p = and [valor s p | s<-atribs p]

satisfaz :: Prop -> Bool
satisfaz p = or [valor s p | s<-atribs p]

equiv :: Prop -> Prop -> Bool
equiv p q = tautologia( Conj (Impl p q) (Impl q p))

showProp :: Prop -> String
showProp (Const x)
 | x == True = "T"
 | otherwise = "F"
showProp (Var x) = [x]
showProp (Neg p) = "(~" ++ showProp p ++ ")"
showProp (Conj p q) = "(" ++ showProp p ++ " && " ++ showProp q ++ ")"
showProp (Disj p q) = "(" ++ showProp p ++ " || " ++ showProp q ++ ")"
showProp (Impl p q) = "(" ++ showProp p ++ " => " ++ showProp q ++ ")"
showProp (Equiv p q)= "(" ++ showProp( (Impl p q) ) ++ " && " ++ showProp( (Impl q p) ) ++ ")"
