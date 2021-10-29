max2, min2 :: Ord a => a -> a -> a

max3, min3 :: Ord a => a -> a -> a -> a

mediana :: Integer -> Integer -> Integer -> Integer

max2 x y = if x>=y then x else y
min2 x y = if x<=y then x else y


max3 x y z = max2 (max2 x y) z
min3 x y z = min2 (min2 x y) z


mediana a b c 
 | max3 a b c == a = max2 b c
 | max3 a b c == b = max2 a c
 | max3 a b c == c = max2 a b
