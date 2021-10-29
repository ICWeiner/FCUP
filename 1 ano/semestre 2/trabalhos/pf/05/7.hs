palavras :: String -> [String]
palavras [] = []
palavras xs
 |head xs == ' ' = palavras (tail xs)
 |otherwise      = keep : palavras rest
 where
  keep = takeWhile (/=' ') xs
  rest = dropWhile (/=' ') xs