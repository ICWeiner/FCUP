module Main where
import Data.Char
main :: IO ()
main = do
    contents <- getContents
    rot13(contents)

rot13 :: String -> IO ()
rot13 [x] = do( putChar( charRot13 x)); 
rot13 (x:xs) = do( putChar( charRot13 x)); rot13 xs
rot13 [] = return ()

charRot13 :: Char -> Char
charRot13 character
 | not( isLetra (character) ) = character
 | biggerThanM ( character) = chr( ord (character) -13)
 | otherwise = chr( ord (character) + 13)

isLetra :: Char -> Bool
isLetra x = maiuscula x || minuscula x

maiuscula :: Char -> Bool
maiuscula x = x >='A' && x <= 'Z'

minuscula :: Char -> Bool
minuscula x = x >='a' && x <= 'z'

biggerThanM :: Char -> Bool
biggerThanM x = (x > 'M' && x <= 'Z') || x > 'm'