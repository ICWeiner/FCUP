diferentes:: Eq a =>[a]->[a]
diferentes (x:y:xs)
 | x == y = diferentes(y:xs)
 |otherwise = x:diferentes(y:xs)
diferentes [x] = [x]
diferentes [] = []