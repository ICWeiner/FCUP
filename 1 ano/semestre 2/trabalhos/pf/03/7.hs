myand :: [Bool] -> Bool
myand [] = True
myand [x] = x
myand (x:xs)
 | x == False = False
 | otherwise = myand xs