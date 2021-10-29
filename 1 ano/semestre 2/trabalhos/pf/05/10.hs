binom :: Integer -> Integer -> Integer
binom n k
 | n>0 && k>1  = product[a..n] `div` product[1..k]
 | k == 1      = n
 | k == 0      = 1
 where
  a = n-k+1


pascal :: [[Integer]]
pascal = map funcao [0..] --takeWhile (/=0) [binom x y| x <-[2..], y <- [0..]]