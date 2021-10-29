h :: [Int] -> Int
h [] = 1
h [x] = x
h (x:y:xs) = x*y + h (y:xs)
