classifica :: Float -> Float -> String

classifica peso altura
 | valor <18.5                = "baixo peso"
 | valor >=18.5 && valor <25  = "peso normal"
 | valor >=25   && valor <30  = "excesso de peso"
 | valor >=30                 = "obesidade"
 where
     valor = peso / ( altura * altura)