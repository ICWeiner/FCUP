-- árvores binárias de pesquisa
data Arv a = Vazia | No a (Arv a) (Arv a)
           deriving (Show)

mapArv :: (a -> b) -> Arv a -> Arv b
mapArv _ Vazia = Vazia
mapArv f (No y esq dir) = No  (f y ) (mapArv f esq  ) (mapArv f dir ) 