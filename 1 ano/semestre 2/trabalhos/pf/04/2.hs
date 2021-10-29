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

