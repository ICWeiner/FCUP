import Log  -- definições de tipos; não alterar!

-- as duas funções pedidas
-- construir uma árvore ordenada de mensagens
build :: [LogEntry] -> MessageTree
build [] = Empty
build [x] = insert x Empty
build (x:xs) = insert x (build xs) 
--build xs = foldl insert xs Empty

-- inclua também a sua definição da função insert
-- esta função não será testada mas é útil
-- para definir a função build 
insert :: LogEntry -> MessageTree -> MessageTree
insert (LogMessage tipo tempo texto) Empty = Node (LogMessage tipo tempo texto) Empty Empty
insert (Unknown _) messageTree = messageTree 
insert (LogMessage tipo tempo texto) (Node (LogMessage tipoNo tempoNo textoNo) esq dir)
 | tempo <  tempoNo = Node  (LogMessage tipoNo tempoNo textoNo) ( insert ( LogMessage tipo tempo texto ) esq ) dir
 | otherwise        = Node  (LogMessage tipoNo tempoNo textoNo) esq ( insert ( LogMessage tipo tempo texto ) dir )

-- listar mensagens por ordem
inOrder :: MessageTree -> [LogEntry]
inOrder Empty = []
inOrder (Node logEntry esq dir) = inOrder esq ++ [logEntry] ++ inOrder dir

sortMessages :: [LogEntry] -> [LogEntry]
sortMessages msgs = inOrder (build msgs)