main :: IO ()
main = do contents <- getContents;
          putStrLn( show( length ( lines( contents) ) ) );
          putStrLn( show( length ( words( contents) ) ) );
          putStrLn( show( length ( contents) ) ) ;