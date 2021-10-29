testaTriangulo :: Float -> Float -> Float -> Bool
testaTriangulo a b c = a < (b+c) && b < (a+c) && c < (b+a)
