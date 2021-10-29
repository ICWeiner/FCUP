-- esconder a definição do prelúdio 
import Prelude hiding (reverse)

-- completar esta definição
reverse :: [a] -> [a]
reverse xs = foldr f z lista 
 where 
  f     = (:)
  z     = [head xs]
  lista = tail xs