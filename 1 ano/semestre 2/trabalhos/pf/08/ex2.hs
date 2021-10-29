import Log   -- definições de tipos; não alterar!

insert :: LogEntry -> MessageTree -> MessageTree
insert (LogMessage tipo tempo texto) Empty = Node (LogMessage tipo tempo texto) Empty Empty
insert (Unknown _) messageTree = messageTree 
insert (LogMessage tipo tempo texto) (Node (LogMessage tipoNo tempoNo textoNo) esq dir)
 | tempo <  tempoNo = Node  (LogMessage tipoNo tempoNo textoNo) ( insert ( LogMessage tipo tempo texto ) esq ) dir
 | otherwise        = Node  (LogMessage tipoNo tempoNo textoNo) esq ( insert ( LogMessage tipo tempo texto ) dir )