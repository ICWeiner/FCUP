import Stack

calcular :: String -> Integer
calcular x = calcularAux (words x) empty

calcularAux :: [String] -> Stack Integer -> Integer
calcularAux [] stk = top stk
calcularAux x stk
 |first == "+" = calcularAux rest (push( topo + bottom ) newStk)
 |first == "-" = calcularAux rest (push( bottom - topo  ) newStk)
 |first == "*" = calcularAux rest (push( topo * bottom ) newStk)
 |first == "/" = calcularAux rest (push( div bottom topo) newStk)
 |otherwise    = calcularAux rest (push(read(first)::Integer) stk) 
  where
   first = head x
   rest  = tail x
   topo = top stk
   bottom = top(pop stk)
   newStk = pop(pop stk)