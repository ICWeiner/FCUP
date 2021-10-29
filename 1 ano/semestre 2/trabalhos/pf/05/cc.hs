validar :: Integer -> Bool
validar n = checkCard(sum(dropDigit(duplicar(algarismos n))))

checkSum ::Integer -> Integer
checkSum n = sum(dropDigit(duplicar(algarismos n)))

checkCard :: Integer -> Bool
checkCard x
 | x`mod`10 == 0 = True
 | otherwise = False

dropDigit :: [Integer] -> [Integer]
dropDigit [] = []
dropDigit (x:xs) 
 | x > 9 =  [a] ++ dropDigit(xs)
 | x < 10 = [x] ++ dropDigit(xs)
 where
  q = x`mod`10
  r = x`div`10
  a = r + q

duplicar :: [Integer] -> [Integer]
duplicar [] = []
duplicar (x:y:xs)
 | length(xs)`mod`2 == 0 = [x*2] ++ [y] ++ duplicar(xs)
 | otherwise = [x] ++ [y*2] ++ duplicar(xs)
duplicar (x:xs)   = [x] ++ duplicar(xs) 

algarismos :: Integer -> [Integer]
algarismos x = reverse(algarismosRev x)


algarismosRev :: Integer -> [Integer]
algarismosRev 0 = []
algarismosRev x = [q] ++ algarismosRev (r)
 where
 q = x`mod`10
 r = x`div`10
