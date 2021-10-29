#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int r,g,b;
}ppmPixel;

typedef struct {
	char p;
	char pnumber;
	int cols;
	int rows;
	int max;
	ppmPixel **mat;
}image;


FILE* openFile(char* s, char* mode);
void message(char* m);
image* readImg(FILE *f);
image* rotateH(image *img);
image* rotateV(image *img);
image* rotateD(image *img);
image* adjust(image *img, int radjust,int gadjust, int badjust);
image* greyscale(image *img,int threshold);
//image* bw(image *img, int threshold);
void writeImg(image *imgr,FILE *f);


int main(int argc, char* argv[]){//possibilitar leitura de stdin e escrita em stdout
	FILE *fichIn, *fichOut;
	image *img;
	int op;

	printf("Escolha uma das seguintes operacoes: \n1- rotacao horizontal \n2- rotacao vertical \n3- rotacao diagonal \n4- ajuste de cores \n5- conversao para greyscale \n6- conversao para preto e branco\n");

	if (argc < 3) message("Uso: ./my_ppm_rot in out");
	
	fichIn = openFile(argv[1],"r");

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	img = readImg(fichIn);
	//img = rotateH(img);

	scanf("%d",&op);

	if (op<0 || op>6) message("operaçao invalida");

	switch(op){
		int radjust, gadjust, badjust,threshold;
		case 1:
			img = rotateH(img);
			break;
		case 2:
			img = rotateV(img);
			break;
		case 3:
			img = rotateD(img);
			break;
		case 4:
			printf("Insira 3 valores positivos ou negativos para ajustar cada cor(vermelho verde e azul)\n");
			scanf("%d %d %d",&radjust, &gadjust, &badjust);
			img = adjust(img,radjust,gadjust,badjust);
			break;
		case 5:
			img = greyscale(img,256);
			break;
		case 6:
			printf("Insira uma valor para o maximo de intensidade de cor\n");
			scanf("%d",&threshold);
			if(threshold<0 || threshold>img->max) message("Valor invalido para maximo");
			img = greyscale(img,threshold);
			break;
		default:
			message("operaçao invalida");

	}
	fichOut = openFile(argv[2],"w");

	if(fichOut==NULL) message("Não foi possivel abrir o ficheiro de escrita");

	writeImg(img,fichOut);
}

FILE* openFile(char* s,char* m){
    FILE *fptr;
    if ((fptr = fopen(s, m)) == NULL) {

        return NULL;
    }
    return fptr;
}

void message(char* m){
	printf("%s\n", m);
	exit(0);
}


image* readImg(FILE *f){
	image *img;

	img = (image*) malloc(sizeof(image));
	char temp[100];
	
	fscanf(f, "%c %c\n", &img->p, &img->pnumber);

	fscanf(f, "%d %d\n", &img->cols, &img->rows);

    fscanf(f, "%d\n", &img->max);

    fscanf(f, "%c\n", &temp[0]);

    /*fgets(&temp,100, f);//Saltar comentarios
    printf("%s\n",temp );

    while(temp[0] == '#'){
    	fgets(&temp,100, f);
    	printf("%s\n",temp );
    }
    printf("%s\n",temp );*/

	img->mat = (ppmPixel **) malloc(img->rows * sizeof(ppmPixel*));//alocar espaço para linhas

	for (int i = 0; i < img->rows; i++){
		img->mat[i] = (ppmPixel *) malloc(img->cols * sizeof(ppmPixel));//alocar espaço para colunas
		for (int j = 0; j < img->cols ; j++){
			ppmPixel pixel;
			fscanf(f,"%d%d%d\n",&pixel.r,&pixel.g,&pixel.b);
			img->mat[i][j] = pixel;
			
			//printf("%d%d%d\n",img->mat[i][j].r ,img->mat[i][j].g ,img->mat[i][j].b );
		}
	}
	return img;
}

image* rotateH(image *img){
	ppmPixel pixel[img->rows][img->cols];

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 , k = (img->cols) - 1; j < img->cols; j++, k--){
			pixel[i][k] = img->mat[i][j];
		}
	}

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			img->mat[i][j].r = pixel[i][j].r;
			img->mat[i][j].g = pixel[i][j].g;
			img->mat[i][j].b = pixel[i][j].b;
		}
	}

	/*for(int i = 0 ; i < img->rows; i++)
		for(int j = 0 ; j < img->cols; j++)

			printf("%d %d %d \n",img->mat[i][j].r ,img->mat[i][j].g ,img->mat[i][j].b );
			//printf("%d %d %d \n",pixel[i][j].r ,pixel[i][j].g ,pixel[i][j].b );*/
	return img;
}

image* rotateV(image *img){
	ppmPixel pixel[img->rows][img->cols];

	for(int i = 0 ,k = (img->rows) - 1; i < img->rows; i++, k--){
		for(int j = 0 ; j < img->cols; j++){
			pixel[k][j] = img->mat[i][j];
		}
	}

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			img->mat[i][j].r = pixel[i][j].r;
			img->mat[i][j].g = pixel[i][j].g;
			img->mat[i][j].b = pixel[i][j].b;
		}
	}
	return img;
}


image* rotateD(image *img){
	ppmPixel pixel[img->rows][img->cols];

	for(int i = 0 ,k = (img->rows) - 1; i < img->rows; i++, k--){
		for(int j = 0,  l = (img->cols) - 1 ; j < img->cols; j++, l--){
			pixel[k][l] = img->mat[i][j];
		}
	}

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			img->mat[i][j].r = pixel[i][j].r;
			img->mat[i][j].g = pixel[i][j].g;
			img->mat[i][j].b = pixel[i][j].b;
		}
	}
	return img;
}

image* adjust(image *img, int radjust,int gadjust, int badjust){
	ppmPixel pixel[img->rows][img->cols];

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			pixel[i][j].r = img->mat[i][j].r + radjust;
			pixel[i][j].g = img->mat[i][j].g + gadjust;
			pixel[i][j].b = img->mat[i][j].b + badjust;

			if(pixel[i][j].r > img->max) pixel[i][j].r = img->max;
			else if(pixel[i][j].r < 0) pixel[i][j].r = 0;

			if(pixel[i][j].g > img->max) pixel[i][j].g = img->max;
			else if(pixel[i][j].g < 0) pixel[i][j].g = 0;

			if(pixel[i][j].b > img->max) pixel[i][j].b = img->max;
			else if(pixel[i][j].b < 0) pixel[i][j].b = 0;

			img->mat[i][j] = pixel[i][j];
		}
	}
	return img;
}

image* greyscale(image *img, int threshold){
	ppmPixel pixel[img->rows][img->cols];

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			pixel[i][j].r = img->mat[i][j].r * 0.2126;
			pixel[i][j].g = img->mat[i][j].g * 0.7152;
			pixel[i][j].b = img->mat[i][j].b * 0.0722;

			int total= pixel[i][j].r + pixel[i][j].g +pixel[i][j].b;

			if(total> threshold) total=255;

			img->mat[i][j].r = total;
			img->mat[i][j].g = total;
			img->mat[i][j].b = total;
		}
	}
	return img;
}
/*
image* bw(image *img,int threshold){
	ppmPixel pixel[img->rows][img->cols];

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			pixel[i][j].r = img->mat[i][j].r * 0.2126;
			pixel[i][j].g = img->mat[i][j].g * 0.7152;
			pixel[i][j].b = img->mat[i][j].b * 0.0722;

			int total= pixel[i][j].r + pixel[i][j].g +pixel[i][j].b;

			if(total> threshold) total=255;

			img->mat[i][j].r = total;
			img->mat[i][j].g = total;
			img->mat[i][j].b = total;
		}
	}
	return img;
}*/

void writeImg(image *img, FILE *f){
	fprintf(f, "%c%c\n", img->p, img->pnumber);
	//&m.magicNumber[2] = '\0';

	fprintf(f, "%d %d\n", img->cols, img->rows);

    fprintf(f, "%d\n", img->max);

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			fprintf(f,"%d   %d   %d\n",img->mat[i][j].r,img->mat[i][j].g,img->mat[i][j].b);
		}
	}
}