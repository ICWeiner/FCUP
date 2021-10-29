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
image* transparentImage(image *overImg, image* underImg ,float alpha,int x1, int y1);
void writeImg(image *imgr,FILE *f);


int main(int argc, char* argv[]){
	FILE *fichIn, *fichOut;
	image *img, *imgB;
	int x1 ,y1;
	double alpha;

	alpha = atof(argv[1]);
	x1 = atoi(argv[2]);
	y1 = atoi(argv[3]);

	if (argc > 4) fichIn = openFile(argv[4],"r");
	else fichIn = stdin;

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	imgB = readImg(fichIn);

	if (argc > 5) fichIn = openFile(argv[5],"r");
	else fichIn = stdin;

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	img = readImg(fichIn);

	img = transparentImage(img,imgB,alpha,y1,x1);

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

image* transparentImage(image *overImg, image* underImg ,float alpha,int x, int y){
	int xLimit = x + underImg->rows;
	int yLimit = y + underImg->cols;
	int rows   = overImg->rows;
	int cols   = overImg->cols;
	float alphaReverse = 1 - alpha;

	image *transImg;

	transImg = (image*) malloc(sizeof(image));

	transImg->p = overImg->p;
	transImg->pnumber = overImg->pnumber;

	transImg->cols = cols;
	transImg->rows = rows;

	transImg->max = overImg->max;

	transImg->mat = (ppmPixel **) malloc(rows * sizeof(ppmPixel*));

	for(int i = 0 ,k = i - x; i < rows;i++, k++){
		transImg->mat[i] = (ppmPixel*) malloc(cols * sizeof(ppmPixel));
		for(int j = 0, l = j - y; j < cols; j++, l++){
			//printf("%d %d\n",i,j);
			if(i >= x  && i < xLimit && j >= y && j < yLimit){
				//printf("%d %d\n",k,l);
				//int r, g, b;
				transImg->mat[i][j].r = myRound((float) overImg->mat[i][j].r * alphaReverse + underImg->mat[k][l].r * alpha );
				transImg->mat[i][j].g = myRound((float) overImg->mat[i][j].g * alphaReverse + underImg->mat[k][l].g * alpha );
				transImg->mat[i][j].b = myRound((float) overImg->mat[i][j].b * alphaReverse + underImg->mat[k][l].b * alpha );
				//printf("%f %d %d\n",alpha,g,b);
			}else{
				transImg->mat[i][j].r =  overImg->mat[i][j].r;
				transImg->mat[i][j].g =  overImg->mat[i][j].g;
				transImg->mat[i][j].b =  overImg->mat[i][j].b;
			}
		}
	}
	free(overImg);
	free(underImg);

	return transImg;
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