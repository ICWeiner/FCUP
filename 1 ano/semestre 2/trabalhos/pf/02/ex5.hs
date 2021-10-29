safetail :: [a] -> [a]
safetail list = if length list == 0 then [] else tail list