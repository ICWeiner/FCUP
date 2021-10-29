-- árvores binárias de pesquisa
data Arv a = Vazia | No a (Arv a) (Arv a)
           deriving (Show)

nivel :: Int -> Arv a -> [a]
nivel _ Vazia = []
nivel x (No v esq dir)
 | x == 0 = [v]
 |otherwise = nivel y esq ++ nivel y dir
 where
  y = x -1