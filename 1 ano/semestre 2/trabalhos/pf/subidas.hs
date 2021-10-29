subidas::[Float] -> Int
subidas(x:y:xs)
 |x < y = 1 + subidas (y:xs)
 |otherwise = subidas (y:xs)
subidas [x] = 0
subidas [] = 0