#include <stdio.h>
#include <stdlib.h>

typedef struct {
	int x,y;
}pointCoordinate;

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
pointCoordinate* searchImage(image *img, image* searchedImg ,float margin);
int comparePixel(ppmPixel pixel, ppmPixel searchPixel,int margin);
void writeImg(image *imgr,FILE *f);


int main(int argc, char* argv[]){
	FILE *fichIn, *fichOut;
	image *img, *imgB;
	int margin;
	pointCoordinate *points;

	margin = atoi(argv[1]);

	if (argc > 2) fichIn = openFile(argv[2],"r");
	//else fichIn = stdin;

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	imgB = readImg(fichIn);

	if (argc > 3) fichIn = openFile(argv[3],"r");
	else fichIn = stdin;

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	img = readImg(fichIn);

	points = searchImage(img,imgB,margin);

	/*if(argc > 4) fichOut = openFile(argv[4],"w");
	else fichOut = stdout;

	if(fichOut==NULL) message("Não foi possivel abrir o ficheiro de escrita");*/

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

int comparePixel(ppmPixel pixel, ppmPixel searchPixel,int margin){//return 1 if true
	if(pixel.r <= searchPixel.r + margin || pixel.r >= searchPixel.r - margin)
		if(pixel.g <= searchPixel.g + margin || pixel.g >= searchPixel.g - margin)
			if(pixel.b <= searchPixel.b + margin || pixel.b >= searchPixel.b - margin) return 1;
	return 0;
}

pointCoordinate* searchImage(image *img, image* searchedImg ,float margin){
	int rows   = img->rows;
	int cols   = img->cols;
	int searchRows = searchedImg->rows;
	int searchCols = searchedImg->cols;
	pointCoordinate *pointArray = malloc(sizeof(pointCoordinate));
	int arrayCounter = 0;
	int isTrue = 0;

	for(int i = 0 ; i < rows;i++){
		for(int j = 0 ; j < cols; j++){
			if( comparePixel(img->mat[i][j],searchedImg->mat[0][0],margin) == 1){//why are you printing so much
				printf("\n%d %d",i,j);
				isTrue = 1;
				for(int k = i + 1, l = 1; l < searchRows && isTrue > 0; k++,l++){
					for(int m = j + 1, n = 1; n < searchCols && isTrue > 0;m++, n++){
						if (comparePixel(img->mat[k][m],searchedImg->mat[l][n],margin) == 0) isTrue == 0;
					}
				}
				if(isTrue == 1){
					pointArray[arrayCounter].x = i;
					pointArray[arrayCounter].y = j;
					arrayCounter++;
					pointArray = realloc(pointArray, (1 + arrayCounter) * sizeof(pointCoordinate));
				} 
			}
		}
	}

	printf("\n%d %d",pointArray[0].x, pointArray[0].y);

	return pointArray;
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