-- esconder a definição do prelúdio 
import Prelude hiding (reverse)

-- completar esta definição
reverse :: [a] -> [a]
reverse [] = []
reverse xs = last xs : reverse (init xs)

