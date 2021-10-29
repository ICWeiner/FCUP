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

int myRound(double f);
FILE* openFile(char* s, char* mode);
void message(char* m);
image* readImg(FILE *f);
image* cropImage(image *img,int x1, int y1, int x2,int y2);
void writeImg(image *imgr,FILE *f);


int main(int argc, char* argv[]){
	FILE *fichIn, *fichOut;
	image *img;
	int x1 ,y1, x2, y2;

	x1 = atoi(argv[1]);
	y1 = atoi(argv[2]);
	x2 = atoi(argv[3]);
	y2 = atoi(argv[4]);

	if (argc > 5) fichIn = openFile(argv[5],"r");
	else fichIn = stdin;

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	img = readImg(fichIn);

	img = cropImage(img,x1,y1,x2,y2);// why u no work, possivelmente x e y trocados

	if(argc > 6) fichOut = openFile(argv[6],"w");
	else fichOut = stdout;

	if(fichOut==NULL) message("Não foi possivel abrir o ficheiro de escrita");

	writeImg(img,fichOut);
}

int myRound(double f) {
    int n = (int)(f < 0 ? (f - 0.5) : (f + 0.5));
    return n;
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

image* cropImage(image *img,int x1, int y1, int x2,int y2){
	int rows, cols;
	image *cropImg;
	
	if(x2 > img->cols ) x2 = img->cols - 1;
	if(y2 > img->rows ) y2 = img->rows - 1;

	cols = x2 - x1 + 1;
	rows = y2 - y1 + 1;

	cropImg = (image*) malloc(sizeof(image));

	cropImg->p = img->p ; 
	cropImg->pnumber = img->pnumber;

	cropImg->cols = cols;
	cropImg->rows = rows;

	cropImg->max = img->max;

	cropImg->mat = (ppmPixel **) malloc(rows * sizeof(ppmPixel*));


	for (int i = y1,k = 0;k < rows; i++, k++){
		cropImg->mat[k] = (ppmPixel *) malloc(cols * sizeof(ppmPixel));	
		for (int j = x1, l = 0; l < cols ; j++ ,l++){
			//printf("k:%d l:%d - i:%d j:%d\n",k ,l ,i ,j );
			cropImg->mat[k][l].r = img->mat[i][j].r;
			cropImg->mat[k][l].g = img->mat[i][j].g;
			cropImg->mat[k][l].b = img->mat[i][j].b;
		}
	}
	free(img);
	return cropImg;
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