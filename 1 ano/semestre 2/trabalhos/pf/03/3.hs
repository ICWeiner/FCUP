pitagoricos :: Integer -> [(Integer, Integer, Integer)]
pitagoricos n = [(x,y,z) | x <- [1..n], y <- [1..n],
 let z = floor( sqrt( (fromIntegral(x*x + y*y))))	,
 x*x + y*y == z*z && x<y]
	
	--[(x,y,z) | x <- [1..n], y <- [1..n],z <- [1..n], (x*x) + (y*y) == (z*z)]