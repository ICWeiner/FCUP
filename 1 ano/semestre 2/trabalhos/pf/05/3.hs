-- esconder a definição do prelúdio 
import Prelude hiding ((++))

-- completar esta definição
(++) :: [a] -> [a] -> [a]
xs ++ ys = foldr f z lista
 where 
 f           = (:)
 z           = ys
 lista       = xs