import Stack
parent :: String -> Bool
parent str = parentAux str empty

parentAux :: String -> Stack Char -> Bool
parentAux [] stk = isEmpty stk
parentAux (x:xs) stk
 | x == '(' || x == '{' || x == '[' = parentAux xs (push x stk)
 | x == ')' = not (isEmpty stk) &&
 top stk == '(' &&
 parentAux xs (pop stk)
 | x == '}' = not (isEmpty stk) &&
 top stk == '{' &&
 parentAux xs (pop stk)
 | x == ']' = not (isEmpty stk) &&
 top stk == '[' &&
 parentAux xs (pop stk)
 | otherwise = parentAux xs stk
