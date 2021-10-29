merge :: Ord a => [a] -> [a] -> [a]
merge []  []  = []
merge (x:xs) [] = x:xs
merge [] (y:ys) = y:ys
merge (x:xs) (y:ys)
 | x < y     = [x] ++ merge xs (y:ys)
 |otherwise  = [y] ++ merge (x:xs) ys

msort:: Ord a => [a] -> [a]
msort [] = []
msort [x] = [x]
msort xs = merge (msort primeira) (msort segunda)
 where
  (primeira, segunda) = metades xs

metades :: [a] -> ([a],[a])
metades lista = (inicio, fim)
 where
 tamanho = length lista `div` 2
 inicio  = take tamanho lista
 fim     = drop tamanho lista