import Data.Char

forte :: String -> Bool
forte pw
 | length(pw) < 8 = False
 | otherwise = final
 where
 upper = or(map isUpper pw)
 lower = or(map isLower pw)
 digit = or(map isDigit pw)
 final = and[upper,lower,digit] 