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
image* rotate(image *img,int xx, int yy);
image* adjust(image *img, int radjust,int gadjust, int badjust);
image* greyscale(image *img,int threshold);
image* transpose(image *img,int dir);
image* border(image *img,int r,int g, int b,int borderSize);
image* concatImage(image *imgA, image *imgB);
image* cropImage(image *img,int x1, int y1, int x2,int y2);
image* overlayImage(image* overImg, image* underImg, int x, int y);
image* zoomImage(image *img,int factor);
image* transparentImage(image *overImg, image* underImg ,float alpha,int x1, int y1);
void writeImg(image *imgr,FILE *f);


int main(int argc, char* argv[]){//possibilitar leitura de stdin e escrita em stdout
	FILE *fichIn, *fichOut;
	image *img , *imgB;
	int op;

	printf("Escolha uma das seguintes operacoes: \n1- rotacao horizontal \n2- rotacao vertical \n3- rotacao diagonal \n4- ajuste de cores \n");
	printf("5- conversao para greyscale \n6- conversao para preto e branco\n7 - rotacao para a esquerda\n8 - rotacao para a direita\n9 -adicao de margem\n");
	printf("10- concatenacao de imagens\n11- recorte de imagem\n12- sobreposicao de imagens\n13- Zoom de imagens\n");

	if (argc > 1) fichIn = openFile(argv[1],"r");
	else fichIn = stdin;

	if(fichIn==NULL) message("Não foi possivel abrir o ficheiro de leitura");

	img = readImg(fichIn);

	scanf("%d",&op);

	if (op<0 || op>13) message("operaçao invalida");

	switch(op){
		int r, g, b,factor,threshold, borderSize,x1,x2,y1,y2;
		float alpha;
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
			printf("Insira uma valor para o maximo de intensidade de cor\n");
			scanf("%d",&threshold);
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
		case 10:
			imgB = readImg(openFile(argv[2],"r"));
			img  = concatImage(img,imgB);
			break;
		case 11:
			printf("Insira as coordenadas dos pontos\n");
			scanf("%d %d %d %d",&x1,&y1, &x2, &y2);
			img = cropImage(img,x1,y1,x2,y2);
			break;
		case 12:
			printf("Insira as coordenadas dos pontos\n");
			scanf("%d %d",&x1,&y1);
			imgB= readImg(openFile(argv[2],"r"));
			img = overlayImage(img,imgB,x1,y1);
			break;
		case 13:
			printf("Insira o fator zoom:\n");
			scanf("%d",&factor);
			img = zoomImage(img,factor);
			break;
		case 12:
			printf("Insira as coordenadas dos pontos\n");
			scanf("%d %d",&x1,&y1);
			printf("Insira o valor de alpha(entre 0 e 1):\n");
			scanf("%d",%alpha);
			if((factor < 1 && factor > 0)) img = transparentImage(img,imgB,alpha,x1,y1);
		default:
			message("operaçao invalida");
	}

	if(argc > 2 && (op!=10 || op!= 12 )) fichOut = openFile(argv[2],"w");
	else if(argc > 2) fichOut = openFile(argv[3],"w");
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
			//printf("%d %d\n",l,k);
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
				//printf("border\n");
				borderImg->mat[i][j].r = r;
				borderImg->mat[i][j].g = g;
				borderImg->mat[i][j].b = b;
			}else{
				//printf("not border\n");
				borderImg->mat[i][j].r = img->mat[offi][offj].r;
				borderImg->mat[i][j].g = img->mat[offi][offj].g;
				borderImg->mat[i][j].b = img->mat[offi][offj].b;
			}
		}
	}
	free(img);
	return borderImg;
}

image* concatImage(image *imgA, image *imgB){
	int rows, cols;
	image *concatImg;

	cols = (imgA->cols) + (imgB->cols);

	if((imgA->rows) > (imgB->rows)){
		rows= imgB->rows;
	}else{
		rows = imgA->rows;
	}

	concatImg = (image*) malloc(sizeof(image));

	concatImg->p = imgA->p ; 
	concatImg->pnumber = imgA->pnumber;

	concatImg->cols = cols;
	concatImg->rows = rows;

	concatImg->max = imgA->max;

	concatImg->mat = (ppmPixel **) malloc(rows * sizeof(ppmPixel*));

	for (int i = 0; i < rows; i++){
		concatImg->mat[i] = (ppmPixel *) malloc(cols * sizeof(ppmPixel));	
		for (int j = 0; j < cols ; j++){
			if(j < imgA->cols){
				concatImg->mat[i][j].r = imgA->mat[i][j].r;
				concatImg->mat[i][j].g = imgA->mat[i][j].g;
				concatImg->mat[i][j].b = imgA->mat[i][j].b;
			}else{
				int offj = j - imgA->cols;
				concatImg->mat[i][j].r = imgB->mat[i][offj].r;
				concatImg->mat[i][j].g = imgB->mat[i][offj].g;
				concatImg->mat[i][j].b = imgB->mat[i][offj].b;
			}
		}
	}
	free(imgA);
	free(imgB);
	return concatImg;
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

image* overlayImage(image* overImg, image* underImg, int x, int y){
	int xLimit = x + overImg->rows;
	int yLimit = y + overImg->cols;
	int rows   = underImg->rows;
	int cols   = underImg->cols;

	image *overlayImg;

	overlayImg = (image*) malloc(sizeof(image));

	overlayImg->p = underImg->p;
	overlayImg->pnumber = underImg->pnumber;

	overlayImg->cols = underImg->cols;
	overlayImg->rows = underImg->rows;

	overlayImg->max = underImg->max;

	overlayImg->mat = (ppmPixel **) malloc(rows * sizeof(ppmPixel*));

	for(int i = 0 ,k = i - x; i < rows;i++, k++){
		overlayImg->mat[i] = (ppmPixel*) malloc(cols * sizeof(ppmPixel));
		for(int j = 0, l = j - y; j < cols; j++, l++){
			if(i >= x  && i < xLimit && j >= y && j < yLimit){
				//printf("%d %d\n",k,l);
				overlayImg->mat[i][j].r =  overImg->mat[k][l].r;
				overlayImg->mat[i][j].g =  overImg->mat[k][l].g;
				overlayImg->mat[i][j].b =  overImg->mat[k][l].b;
			}else{
				overlayImg->mat[i][j].r =  underImg->mat[i][j].r;
				overlayImg->mat[i][j].g =  underImg->mat[i][j].g;
				overlayImg->mat[i][j].b =  underImg->mat[i][j].b;
			}
		}
	}
	free(overImg);
	free(underImg);

	return overlayImg;
}

image* zoomImage(image *img,int factor){
	int rows, cols,ogRows, ogCols;


	ogRows = img->rows;
	ogCols = img->cols;

	rows = img->rows * factor;
	cols = img->cols * factor;

	image *zoomImg;

	zoomImg = (image*) malloc(sizeof(image));

	zoomImg->p = img->p;
	zoomImg->pnumber = img->pnumber;

	zoomImg->cols = cols;
	zoomImg->rows = rows;

	zoomImg->max = img->max;

	zoomImg->mat = (ppmPixel **) malloc(rows * sizeof(ppmPixel*));


	for(int i = 0, k = 0, iCount = 1; k < rows ; k++ ,iCount++){
		zoomImg->mat[k] = (ppmPixel *) malloc(cols * sizeof(ppmPixel));
		for(int j = 0 , l = 0, jCount = 1; l < cols; l++, jCount++){
			printf("k:%d l:%d - i:%d j:%d\n",k ,l, i, j );
			zoomImg->mat[k][l].r = img->mat[i][j].r;
			zoomImg->mat[k][l].g = img->mat[i][j].g;
			zoomImg->mat[k][l].b = img->mat[i][j].b;
			if(jCount == factor){
				jCount = 0;
				j++;
			}
		}if(iCount == factor){
			iCount = 0;
			i++;
		}
	}
	return zoomImg;
}

image* transparentImage(image *overImg, image* underImg ,float alpha,int x1, int y1){
	int xLimit = x + underImg->rows;
	int yLimit = y + underImg->cols;
	int rows   = overImg->rows;
	int cols   = overImg->cols;
	float alphaReverse = 1 - alpha;

	image *overlayImg;

	overlayImg = (image*) malloc(sizeof(image));

	overlayImg->p = overImg->p;
	overlayImg->pnumber = overImg->pnumber;

	overlayImg->cols = cols;
	overlayImg->rows = rows;

	overlayImg->max = overImg->max;

	overlayImg->mat = (ppmPixel **) malloc(rows * sizeof(ppmPixel*));

	for(int i = 0 ,k = i - x; i < rows;i++, k++){
		overlayImg->mat[i] = (ppmPixel*) malloc(cols * sizeof(ppmPixel));
		for(int j = 0, l = j - y; j < cols; j++, l++){
			if(i >= x  && i < xLimit && j >= y && j < yLimit){
				//printf("%d %d\n",k,l);
				int r, g, b;
				r=overlayImg->mat[i][j].r =  underImg->mat[i][j].r * alphaReverse + overImg->mat[k][l].r * alpha;
				g=overlayImg->mat[i][j].g =  underImg->mat[i][j].g * alphaReverse + overImg->mat[k][l].g * alpha;
				b=overlayImg->mat[i][j].b =  underImg->mat[i][j].b * alphaReverse + overImg->mat[k][l].b * alpha;
				printf("%f %d %d\n",alpha,g,b);
			}else{
				overlayImg->mat[i][j].r =  overImg->mat[k][l].r;
				overlayImg->mat[i][j].g =  overImg->mat[k][l].g;
				overlayImg->mat[i][j].b =  overImg->mat[k][l].b;
			}
		}
	}
	free(overImg);
	free(underImg);

	return overlayImg;
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