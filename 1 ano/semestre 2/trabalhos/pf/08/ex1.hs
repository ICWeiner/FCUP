import Log

parseMessage :: String -> LogEntry
parseMessage texto--   tipo                tempo             texto
 | tipo == 'I' = LogMessage Info           num1  (unwords ( tail( mensagem) ) )
 | tipo == 'W' = LogMessage Warning        num1  (unwords ( tail( mensagem) ) )
 | tipo == 'E' = LogMessage (Error num1) ( read (mensagem!!1)::Int ) (unwords (tail ( tail( mensagem) ) ) )
 |otherwise    = Unknown texto
  where
   mensagem = tail( words (texto) )
   tipo = head( texto )
   num1 = read (head ( mensagem ) )::Int