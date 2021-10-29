classifica :: Int -> String

classifica nota 
 | nota <10             = "Reprovado"
 | nota >9 && nota <13  = "Suficiente"
 | nota >12 && nota <16 = "Bom"
 | nota >15 && nota <19 = "Muito Bom"
 | nota >18 && nota <21 = "Muito Bom com distincao"