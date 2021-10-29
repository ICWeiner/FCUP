binom :: Integer -> Integer -> Integer

binom n k
 | n>0 && k>1  = product[a..n] `div` product[1..k]
 | k == 1      = n
 | k == 0      = 1
 where
  a = n-k+1

