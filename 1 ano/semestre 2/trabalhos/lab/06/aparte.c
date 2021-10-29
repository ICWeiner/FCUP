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
image* transpose(image *img,int dir);
image* border(image *img,int r,int g, int b,int borderSize);
void writeImg(image *imgr,FILE *f);


int main(int argc, char* argv[]){
	FILE *fichIn, *fichOut;
	image *img;
	int op;

	//printf("Escolha uma das seguintes operacoes: \n1- rotacao horizontal \n2- rotacao vertical \n3- rotacao diagonal \n4- ajuste de cores \n");
	//printf("5- conversao para greyscale \n6- conversao para preto e branco\n7 - rotacao para a esquerda\n8 - rotacao para a direita\n9 -adicao de margem\n");

	if (argc > 1) fichIn = openFile(argv[2],"r");
	else fichIn = stdin;

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	img = readImg(fichIn);

	//scanf("%d",&op);
	op=6;

	if (op<0 || op>9) message("operaçao invalida");

	switch(op){
		int r, g, b, threshold, borderSize;
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
			scanf("%d %d %d",&r, &g, &b);
			img = adjust(img,r,g,b);
			break;
		case 5:
			img = greyscale(img,256);// meter cores em "grayscale"
			break;
		case 6:
			//printf("Insira uma valor para o maximo de intensidade de cor\n");
			//scanf("%d",&threshold);
			threshold = atoi(argv[1]);
			if(threshold<0 || threshold>img->max) message("Valor invalido para maximo");
			img = greyscale(img,threshold);// meter cores em preto e branco
			break;
		case 7:
			img = transpose(img,1);//rodar para a direita
			break;
		case 8:
			img = transpose(img,-1);//rodar para a esquerda
			break;
		case 9:
			printf("Insira 1 valor para a moldura e 3 valores para a criacao da margem\n");
			scanf("%d %d %d %d",&borderSize,&r, &g, &b);
			img = border(img,r,g,b,borderSize);
			break;
		default:
			message("operaçao invalida");
	}

	if(argc > 2) fichOut = openFile(argv[3],"w");
	else fichOut = stdout;

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
	int k = 0 , l = 0;

	if(xx == -1) k = (img->rows) - 1;

	for(int i = 0 ; i < img->rows; i++, k+=xx){
		if(yy == -1) l = (img->cols) - 1;
		else l = 0;
		for(int j = 0 ; j < img->cols; j++, l+=yy){
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

image* greyscale(image *img, int threshold){//Fusao de greyscale e BW numa unica funçao gracas ao if dentro dos fors
	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			double r,g,b;

			r = img->mat[i][j].r * 2126;
			g = img->mat[i][j].g * 7152;
			b = img->mat[i][j].b * 722;

			int total = (float) ((r + g + b)/10000);
			if(total >= threshold) total=255;

			img->mat[i][j].r = total;
			img->mat[i][j].g = total;
			img->mat[i][j].b = total;
		}
	}
	return img;
}

image* transpose(image *img,int dir){
	image *rotImg;
	int rows = img->cols;
	int cols = img->rows;
	int xx,yy,k;

	rotImg = (image*) malloc(sizeof(image));

	rotImg->p = img->p ; 
	rotImg->pnumber = img->pnumber;

	rotImg->cols = cols;
	rotImg->rows = rows;

	rotImg->max = img->max;

	rotImg->mat = (ppmPixel **) malloc(rotImg->rows * sizeof(ppmPixel*));

	if( dir == 1){
		k = 0;
		xx = 1;
		yy = 1;
	}else{
		k = rows - 1;
		xx = -1;
		yy = -1; 
	}

	for (int i = 0 ; i < rows; i++, k+=xx){
		rotImg->mat[i] = (ppmPixel *) malloc(rotImg->cols * sizeof(ppmPixel));
		int l;
		if( yy == -1) l = 0;
		else l = cols - 1;
		for (int j = 0; j < cols ; j++,l-=yy){
			printf("%d %d\n",l,k);
			rotImg->mat[i][j].r = img->mat[l][k].r;
			rotImg->mat[i][j].g = img->mat[l][k].g;
			rotImg->mat[i][j].b = img->mat[l][k].b;
		}
	}
	free(img);
	return rotImg;
}

image* border(image *img,int r,int g, int b,int borderSize){
	image *borderImg;
	int rows = (img->rows) + (borderSize * 2);
	int cols = (img->cols) + (borderSize * 2);

	borderImg = (image*) malloc(sizeof(image));

	borderImg->p = img->p ; 
	borderImg->pnumber = img->pnumber;

	borderImg->cols = cols;
	borderImg->rows = rows;

	borderImg->max = img->max;

	borderImg->mat = (ppmPixel **) malloc(borderImg->rows * sizeof(ppmPixel*));

	for (int i = 0; i < rows; i++){
		borderImg->mat[i] = (ppmPixel *) malloc(borderImg->cols * sizeof(ppmPixel));	
		int offi = i - borderSize;
		for (int j = 0; j < cols ; j++){
			int offj = j - borderSize;
			if(i < borderSize || i >= (rows - borderSize) || j < borderSize || j >= (cols - borderSize) ){
				printf("border\n");
				borderImg->mat[i][j].r = r;
				borderImg->mat[i][j].g = g;
				borderImg->mat[i][j].b = b;
			}else{
				printf("not border\n");
				borderImg->mat[i][j].r = img->mat[offi][offj].r;
				borderImg->mat[i][j].g = img->mat[offi][offj].g;
				borderImg->mat[i][j].b = img->mat[offi][offj].b;
			}
		}
	}
	free(img);
	return borderImg;
}

void writeImg(image *img, FILE *f){
	fprintf(f, "%c%c\n", img->p, img->pnumber);

	fprintf(f, "%d %d\n", img->cols, img->rows);

    fprintf(f, "%d\n", img->max);

	for(int i = 0 ; i < img->rows; i++){
		for(int j = 0 ; j < img->cols; j++){
			fprintf(f,"%d %d %d\n",img->mat[i][j].r,img->mat[i][j].g,img->mat[i][j].b);
		}
	}
}