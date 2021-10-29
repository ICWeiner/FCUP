divisores :: Int -> [Int]
divisores n = filter(\x-> n`mod`x==0) [1..n]

divisores1 :: Int -> [Int]
divisores1 n = [d | d <-[1..n], n`mod`d == 0 ]

divisores2 :: Int -> [Int]
divisores2 n = map (n`mod`) filter(n`mod`2==0) [1..n]