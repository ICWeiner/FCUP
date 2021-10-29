fromBits :: [Int] -> Int
fromBits (x:y:xs) =fromBits(carry:xs)
 where
 carry = digit x y
fromBits (x:xs) = x 
fromBits [] = 0
 



digit :: Int -> Int -> Int
digit x y
 |y == 0 = x * 2
 |otherwise = (x * 2) + 1


toBits :: Int -> [Int]
toBits x  = reverse(toBitsRev x)

toBitsRev :: Int -> [Int]
toBitsRev x
 | x < 2 = [x]
 | x < 4 = [b] ++ [1]
 | otherwise = [b] ++ toBitsRev (r) 
 where
 b = x`mod`2 
 r = x`div`2

