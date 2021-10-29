safetail :: [a] -> [a]
safetail list
 |length list == 0 = []
 |length list >= 1 = tail list