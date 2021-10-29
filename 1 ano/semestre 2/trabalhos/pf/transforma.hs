transforma:: String -> String
transforma (x:xs)
 |isVogal x = (x:'p':x:transforma xs)
 |otherwise = (x:transforma xs)
transforma [] = []

isVogal:: Char -> Bool
isVogal x 
 | x == 'a' ||
   x == 'e' ||
   x == 'i' ||
   x == 'o' ||
   x == 'u' = True
 |otherwise = False 