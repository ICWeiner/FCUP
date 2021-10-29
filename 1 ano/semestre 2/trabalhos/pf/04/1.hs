algarismos :: Int -> [Int]
algarismos x = reverse(algarismosRev x)


algarismosRev :: Int -> [Int]
algarismosRev 0 = []
algarismosRev x = [q] ++ algarismosRev (r)
 where
 q = x`mod`10
 r = x`div`10
