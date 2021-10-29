data Arv a = F | N a (Arv a) (Arv a)

alturas :: Arv a -> Arv Int
alturas (N _ esq dir) = criarAlturas 0

criarAlturas :: Arv a -> Int -> [Int]
criarAlturas (N _ esq dir) x
|esq == F && dir == F = (criarAlturas 
