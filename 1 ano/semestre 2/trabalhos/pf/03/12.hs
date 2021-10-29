intersperse :: a -> [a]-> [a]
intersperse _ [] = []
intersperse symbol [x] = [x]
intersperse symbol (x:y:xs) = [x] ++ [symbol] ++ intersperse symbol (y:xs)