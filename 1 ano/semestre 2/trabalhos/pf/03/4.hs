divprop :: Integer -> [Integer]
divprop n = [ x | x <- [1..n-1], n`mod`x == 0 ]

primo :: Integer -> Bool
primo n 
 | divprop n == [1] = True
 | otherwise = False