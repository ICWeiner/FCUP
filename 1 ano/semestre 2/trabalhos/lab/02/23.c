#include <stdio.h>

void main(){
	char c, *cptr;
	c = 'a';
	cptr = &c;

	short s, *sptr;
	s = '3';
	sptr = &s;

	long l, *lptr;
	l = 214123753;
	lptr = &l;


	float f, *fptr;
	f = 1.23;
	fptr = &f;



	printf("tamanho de um char: %lu\n", sizeof(c));
	printf("tamanho do endereço de um char: %lu\n", sizeof(&c));
	printf("tamanho de um apontador para um char: %lu\n", sizeof(cptr));
	printf("tamanho do conteúdo apontado por um apontador para um char: %lu\n", sizeof(*cptr));
	printf("Os valores apontados pelos endereços ’%p’ e ’%p’ são ’%c’ e ’%c’\n", &c, cptr, c, *cptr);

	printf("tamanho de um short: %lu\n", sizeof(s));
	printf("tamanho do endereço de um short: %lu\n", sizeof(s));
	printf("tamanho de um apontador para um short: %lu\n", sizeof(sptr));
	printf("tamanho do conteúdo apontado por um apontador para um short: %lu\n", sizeof(*sptr));
	printf("Os valores apontados pelos endereços ’%p’ e ’%p’ são ’%d’ e ’%d’\n", &s, sptr, s, *sptr);

	printf("tamanho de um long: %lu\n", sizeof(l));
	printf("tamanho do endereço de um long: %lu\n", sizeof(&l));
	printf("tamanho de um apontador para um long: %lu\n", sizeof(lptr));
	printf("tamanho do conteúdo apontado por um apontador para um long: %lu\n", sizeof(*lptr));
	printf("Os valores apontados pelos endereços ’%p’ e ’%p’ são ’%ld’ e ’%ld’\n", &l, lptr, l, *lptr);

	printf("tamanho de um float: %lu\n", sizeof(f));
	printf("tamanho do endereço de um float: %lu\n", sizeof(&f));
	printf("tamanho de um apontador para um float: %lu\n", sizeof(fptr));
	printf("tamanho do conteúdo apontado por um apontador para um float: %lu\n", sizeof(*fptr));
	printf("Os valores apontados pelos endereços ’%p’ e ’%p’ são ’%f’ e ’%f’\n", &f, fptr, f, *fptr);
}