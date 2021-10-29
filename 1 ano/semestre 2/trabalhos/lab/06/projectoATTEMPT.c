#include <stdio.h>
#include <stdlib.h>

typedef struct 
{
	int r,g,b;
}ppmPixel;

//struct ppmPixel ppmPixels[5][5];


typedef struct 
{
	char p;
	char pnumber;
	int cols;
	int rows;
	int max;
	ppmPixel **mat;
} image;


FILE* abre_fich(char* s, char* mode);
void message(char* m);
image* readImg(FILE *f);
image* rotateH(image *img,int xx, int yy);
//image* rotateV(image *img);
//image* rotateD(image *img);
void writeImg(image *imgr,FILE *f);
//void print(PPMPixel p);//what?


int main(int argc, char* argv[]){//possibilitar leitura de stdin e escrita em stdout
	FILE *fichIn, *fichOut;
	image *img;
	int op = atoi(argv[3]);

	if (argc < 3) message("Uso: ./my_ppm_rot in out operaçao");
	
	fichIn = abre_fich(argv[1],"r");

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	img = readImg(fichIn);
	//img = rotateH(img);

	switch(op){
		case 1:
			img = rotateH(img,0,1);
			break;
		case 2:
			img = rotateH(img,1,0);
			break;
		case 3:
			//img = rotateV(img);
			break;
		case 4:
			//img = rotateH(img);
			break;
		case 5:
			//img = rotateH(img);
			break;
		case 6:
			//img = rotateH(img);
			break;
		default:
			message("operaçao invalida");

	}
	fichOut = abre_fich(argv[2],"w");

	if(fichOut==NULL) message("Não foi possivel abrir o ficheiro de escrita");

	writeImg(img,fichOut);


}

FILE* abre_fich(char* s,char* m){
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
			//printf("%d\n",j );
			ppmPixel pixel;
			fscanf(f,"%d%d%d\n",&pixel.r,&pixel.g,&pixel.b);
			img->mat[i][j] = pixel;
			
			//printf("%d%d%d\n",img->mat[i][j].r ,img->mat[i][j].g ,img->mat[i][j].b );
		}
	}


	return img;
}

image* rotateH(image *img,int xx, int yy){
	ppmPixel pixel[img->rows][img->cols];
	int k = 0 , l = 0, kk = 1 , ll = 1 ;


	if(xx == 0){
		k = (img->rows) - 1;
		kk = -1;
	}

	if(yy == 0){
		l = (img->cols) - 1;
		ll = -1;
	}


	for(int i = 0 ; i < img->rows; i++, k+=kk){
		for(int j = 0 ; j < img->cols; j++, l+=ll){
			pixel[k][l] = img->mat[i][j];
			printf("%d %d\n",k ,l );
		}
		if(l<0)
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
/*
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
