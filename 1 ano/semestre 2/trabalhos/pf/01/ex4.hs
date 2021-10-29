metades :: [a] -> ([a],[a])

metades lista = (inicio, fim)
 where
      tamanho = length lista `div` 2
      inicio  = take tamanho lista
      fim     = drop tamanho lista

