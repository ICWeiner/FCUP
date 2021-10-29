import Set

union:: Ord a => Set a -> Set a -> Set a 
union Empty b = b
union (Node x left right) b
 |not ( member x b) = delete 

delete :: Ord a => a -> Set a -> Set a
delete x (Node y left right)
  | x == y = Node y left right
  | x > y  = Node y left (insert x right)
  | x < y  = Node y (insert x l) right  