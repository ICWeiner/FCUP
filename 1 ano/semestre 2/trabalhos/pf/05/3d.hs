-- esconder a definição do prelúdio 
import Prelude hiding (reverse)

-- completar esta definição
reverse :: [a] -> [a]
reverse xs = foldl f z xs
    where 
     f     = flip (:)
     z     = []