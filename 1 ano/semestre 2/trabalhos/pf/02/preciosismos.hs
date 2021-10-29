dialogo :: Int -> Int -> Int -> Int -> (String, String)


dialogo h1 m1 h2 m2
 | intervalo == 1         = ("Passou apenas " ++ show intervalo ++ " minuto!","De facto!")
 | intervalo < 60         = ("Passaram apenas " ++ show intervalo ++ " minutos!","De facto!")
 | intervalo == 60        = ("Passaram apenas " ++ show intervalo ++ " minutos!","Queres dizer, " ++ show intervaloHoras ++ " hora?!")
 | quot intervalo 60 == 1 &&
   intervaloMinutos == 1  = ("Passaram apenas " ++ show intervalo ++ " minutos!","Queres dizer, " ++ show intervaloHoras ++ " hora e " ++ show intervaloMinutos ++ " minuto?!")
 | quot intervalo 60 == 1 &&
   intervaloMinutos >= 1  = ("Passaram apenas " ++ show intervalo ++ " minutos!","Queres dizer, " ++ show intervaloHoras ++ " hora e " ++ show intervaloMinutos ++ " minutos?!")
 | quot intervalo 60 /= 1 &&
   intervaloMinutos == 1  = ("Passaram apenas " ++ show intervalo ++ " minutos!","Queres dizer, " ++ show intervaloHoras ++ " horas e " ++ show intervaloMinutos ++ " minuto?!")
 | intervaloMinutos > 1   = ("Passaram apenas " ++ show intervalo ++ " minutos!","Queres dizer, " ++ show intervaloHoras ++ " horas e " ++ show intervaloMinutos ++ " minutos?!")
 where
      intervalo = h2 * 60 + m2 - (h1 * 60 + m1)
      intervaloHoras = intervalo `div` 60
      intervaloMinutos = intervalo - intervaloHoras * 60 