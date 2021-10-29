-- declaração de árvores binárias 
data Arv a = Vazia | No a (Arv a) (Arv a)
           deriving (Show)

sumArv :: Num a => Arv a -> a
sumArv Vazia = 0
sumArv (No y esq dir) = y + sumArv ( esq ) + sumArv ( dir )