delete :: Eq a => a -> [a] -> [a]
delete n (x:xs)
 |n == x = xs
 |otherwise = [x] ++ delete n xs

mymin :: Ord a => [a] -> a
mymin [x]= x
mymin (x:y:xs)
 |x < y = mymin (x:xs)
 |otherwise = mymin (y:xs)

ssort :: Ord a => [a] -> [a]
ssort [] = []
ssort [x] = [x]
ssort (x:xs) = [minimal] ++ ssort( delete minimal (x:xs))
 where
 minimal = mymin(x:xs) 

