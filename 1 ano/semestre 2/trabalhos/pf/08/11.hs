data Arv a = Vazia | No a (Arv a) (Arv a)
           deriving (Eq, Show)

remover :: Ord a => a -> Arv a -> Arv a
remover _ Vazia = Vazia
remover x (No y Vazia Vazia)
 | x == y = Vazia
 |otherwise = (No y Vazia Vazia)
remover _ (No _ esq Vazia) = esq
remover _ (No _ Vazia dir) = dir
remover x (No y esq dir)
 | x == y    = remover (maisDir esq) esq
 | otherwise = No y esq (remover x dir)

-- valor mais à esquerda
-- (menor valor numa árvore ordenada)
maisDir :: Arv a -> a
maisDir (No x  _ Vazia) = x
maisDir (No _ _ dir)   = maisDir dir

