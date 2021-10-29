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
image* rotate(image *img,int xx, int yy);
image* adjust(image *img, int radjust,int gadjust, int badjust);
image* greyscale(image *img,int threshold);
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

	scanf("%d",&op);

	if (op<0 || op>6) message("operaçao invalida");

	switch(op){
		int radjust, gadjust, badjust,threshold;
		case 1:
			img = rotate(img,1,-1);//rodar imgem na horizontal
			break;
		case 2:
			img = rotate(img,-1,1);//rodar imgem na vertical
			break;
		case 3:
			img = rotate(img,-1,-1);//rodar imgem na diagonal
			break;
		case 4:
			printf("Insira 3 valores positivos ou negativos para ajustar cada cor(vermelho verde e azul)\n");
			scanf("%d %d %d",&radjust, &gadjust, &badjust);
			img = adjust(img,radjust,gadjust,badjust);
			break;
		case 5:
			img = greyscale(img,256);// meter cores em "grayscale"
			break;
		case 6:
			printf("Insira uma valor para o maximo de intensidade de cor\n");
			scanf("%d",&threshold);
			if(threshold<0 || threshold>img->max) message("Valor invalido para maximo");
			img = greyscale(img,threshold);// meter cores em preto e branco
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
	
	fscanf(f, "%c %c\n", &img->p, &img->pnumber);

	fscanf(f, "%d %d\n", &img->cols, &img->rows);

    fscanf(f, "%d\n", &img->max);

	img->mat = (ppmPixel **) malloc(img->rows * sizeof(ppmPixel*));

	for (int i = 0; i < img->rows; ++i){
		img->mat[i] = (ppmPixel *) malloc(img->cols * sizeof(ppmPixel));	
		for (int j = 0; j < img->cols ; j++){
			ppmPixel pixel;
			fscanf(f,"%d%d%d\n",&pixel.r,&pixel.g,&pixel.b);
			img->mat[i][j] = pixel;
		}
	}
	return img;
}

image* rotate(image *img,int xx, int yy){
	ppmPixel pixel[img->rows][img->cols];
	int k = 0 , l = 0, kk = 1 , ll = 1 ;

	if(xx == -1) k = (img->rows) - 1;

	for(int i = 0 ; i < img->rows; i++, k+=xx){
		if(yy == -1) l = (img->cols) - 1;
		else l = 0;
		for(int j = 0 ; j < img->cols; j++, l+=yy){
			pixel[k][l] = img->mat[i][j];
			printf("%d %d\n",k ,l );
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

void writeImg(image *img, FILE *f){
	fprintf(f, "%c%c\n", img->p, img->pnumber);

	fprintf(f, "%d %d\n", img->cols, img->rows);

    fprintf(f, "%d\n", img->max);

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			fprintf(f,"%d   %d   %d\n",img->mat[i][j].r,img->mat[i][j].g,img->mat[i][j].b);
		}
	}
}