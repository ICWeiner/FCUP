module Main where

readDict :: IO [String]
readDict = do
    txt <- readFile "/usr/share/dict/words"
    return (lines txt);
