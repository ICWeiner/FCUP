data Arv a = Vazia | No a (Arv a) (Arv a)
           deriving (Eq, Show)

remover :: Ord a => a -> Arv a -> Arv a
remover _ Vazia = Vazia
remover x (No y esq Vazia)
 | x == y = esq
remover x (No y Vazia dir)
 | x == y = dir
remover x (No y esq dir)
 | x<y = No y (remover x esq) dir
 | x>y = No y esq (remover x dir)
 | x==y = let z = maisDir esq
         in No z (remover z esq) dir

-- valor mais à esquerda
-- (menor valor numa árvore ordenada)
maisDir :: Arv a -> a
maisDir (No x  _ Vazia) = x
maisDir (No _ _ dir)   = maisDir dir

