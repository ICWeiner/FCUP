#!/bin/bash

MYFILE=~/Desktop/aulas/ic/praticas/texto.txt

echo escolha a opcao desejada
read choice

if [$choice == "ler"];
then
	cat $MYFILE
fi