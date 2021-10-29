main :: IO ()
main = do 
    contents <- getContents;
    let myLines = lines(contents);
    linhas( myLines);

linhas :: [String] -> IO ()
linhas (x:xs) = do
    putStrLn( reverse ( x ) );
    (linhas xs) 
linhas [] = return()