#include <stdio.h>
#define  TAM_MAX 100
#include <math.h>
int main() {
	 
	int n;
	float num,vetor[TAM_MAX],maximo,minimo,media,mediana,DP,soma1,soma2,variancia;
	int i;
	printf("quer quantos numeros?: ");
	scanf("%d",&n);
	

	for(i=0; i<n ; i++) { //somar
	    
        printf("digita:");
        scanf("%f", &num);
        soma1+=num;
        vetor[i] = num;
        
        
        
    }
    
 
 void quickSort(float arr[], int left,int right ){//ordenar
	if(left>=right)
	return;
	int i = left;
	int j = right;
	int pivot=arr[i];
	while(i<j){
		while(i<j&&arr[j]>=pivot)j--;
		arr[i]=arr[j];
		while(i<j&arr[i]<=pivot)i++;
		arr[j]=arr[i];
		
	}
	arr[i]=pivot;
	quickSort(arr,left,i - 1); 
	quickSort(arr,i + 1,right);
}

    
    void exibir(){ //exibir vetor
    	for(i=0; i<n ; i++) { 
        printf(" exibir: %.2f\n", vetor[i]);}
    }  
    exibir();
    
    
    quickSort(vetor,0,n-1);
    maximo=vetor[n-1];
	minimo=vetor[0];
	media=soma1/n;
	
	 //desvio padrao
	 for (i = 0 ; i< n; i++) {
	 soma2 += ((vetor[i]-media)* (vetor[i]-media)); 
	 } 
	 variancia = soma2/ (float)n ; 
	 
	 DP = sqrt (variancia) ;
	
	
    
    printf(" depois de mudar ordem:\n");
    exibir();
    printf(" exibir maximo: %.2f\n", maximo);
    printf(" exibir minimo: %.2f\n", minimo);
    printf(" exibir media: %.2f\n", media);
    if (n%2==0)
    printf(" exibir mediano: %.2f\n",(vetor[(n)/2]+vetor[(n/2-1)])/2);//par
    else
    printf(" exibir mediano: %.2f\n",vetor[(n-1)/2]);//impar
    printf(" exibir desvio padrao: %.2f\n",DP);
    
    
    
	 }
