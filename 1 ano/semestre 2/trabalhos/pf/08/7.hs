-- árvores binárias de pesquisa
data Arv a = Vazia | No a (Arv a) (Arv a)
           deriving (Show)

listarDecr :: Arv a -> [a]
listarDecr Vazia = []
listarDecr (No y esq dir) = listarDecr dir ++ [y] ++ listarDecr esq