elefantes :: Int-> IO ()
elefantes n = texto 2 n

texto :: Int -> Int -> IO ()
texto n k
 |n < k = do putStrLn(frase0 ++ show(n) ++ frase1); putStrLn( show( n+1 ) ++ frase2); texto (n +1) k
 |otherwise = return()
 where
  frase0 = "Se "
  frase1 = " elefantes incomodam muita gente,"
  frase2 = " incomodam muito mais!"