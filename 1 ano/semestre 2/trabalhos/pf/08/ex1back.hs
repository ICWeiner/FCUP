import Log

parseMessage :: String -> LogEntry
parseMessage texto--   tipo           tempo                      texto
 | tipo == 'I' = LogMessage Info    ( num1 ) (unwords ( tail( mensagem) ) )
 | tipo == 'W' = LogMessage Warning ( num1 ) (unwords ( tail( mensagem) ) )
 | tipo == 'E' = LogMessage (Error ) ( num1 ) (unwords ( tail( mensagem) ) )
  where
   mensagem = tail( words (texto) )
   tipo = head( texto )
   num1 = read (head ( mensagem ) )::Int