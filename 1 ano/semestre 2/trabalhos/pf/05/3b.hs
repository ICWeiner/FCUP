-- esconder a definição do prelúdio 
import Prelude hiding (concat)

-- completar esta definição
concat :: [[a]] -> [a]
concat xs = foldr f z lista
 where
  f = (++)
  z = last xs
  lista = init xs