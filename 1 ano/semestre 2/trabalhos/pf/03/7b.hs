myor :: [Bool] -> Bool
myor [] = False
myor [x] = x
myor (x:xs)
 | x == True = True
 | otherwise = myor xs