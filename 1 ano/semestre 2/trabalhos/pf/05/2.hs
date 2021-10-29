primo :: Integer -> Bool
primo n
 |n > 1 = not(any (\x -> n`mod`x==0)[2..upperLimit])
 |otherwise = False
 where
 upperLimit = floor( sqrt( fromIntegral n))
