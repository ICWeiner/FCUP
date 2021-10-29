max2, min2 :: Ord a => a -> a -> a
max2 x y = if x>=y then x else y
min2 x y = if x<=y then x else y

max3, min3 :: Ord a => a -> a -> a -> a
max3 x y z = max2 (max2 x y) z
min3 x y z = min2 (min2 x y) z
