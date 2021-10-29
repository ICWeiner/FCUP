redo ::[a]->[a]

redo lista = fim
	where
         fim = take 1(drop ((length lista)-1) lista)

redodo::[a]->[a]

redodo lista = fim
	where
		 fim =take 1(reverse lista)


reredo::[a]->[a]

reredo lista = fim
	where
         fim = reverse( drop 1(reverse lista))

reredodo ::[a]->[a]

reredodo lista = fim
	where
         fim = take ((length lista)-1) lista