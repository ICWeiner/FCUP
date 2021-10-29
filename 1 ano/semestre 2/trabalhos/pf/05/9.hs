aproxPi1 :: Int -> Double
aproxPi1 n = sum (take n  (zipWith (/) ( ( cycle [4,-4])) ((iterate (+2) 1))))


aproxPi2 :: Int -> Double
aproxPi2 n = 3 + sum (zipWith (quot) nums denoms)
 where
  nums = take (n -1) (cycle [fromIntegral 4,-4])
  denoms = genDenoms [2..(n*2)]

genDenoms :: [Int] -> [Int]
genDenoms [x] = []
genDenoms (x:xs) = foldr (*) x  (take 2 (xs)) : genDenoms(drop 1 xs)
