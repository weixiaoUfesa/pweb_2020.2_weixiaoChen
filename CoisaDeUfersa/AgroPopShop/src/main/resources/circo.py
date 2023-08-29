melhorPrec=0
maiorLucro=0
while True:
        str1=int(input('digite o número adicional de pessoas:'))
        for i in [25,20,15]:
           pessoa=120+(25-i)/5*str1
           lucro=pessoa*i-500
           if maiorLucro<lucro:
               melhorPreco=i
               maiorLucro=lucro
               
           print('preco:',i)
           print('lucro:',lucro)
        print('melhor preco:',melhorPreco)
        print('maior lucro:',maiorLucro)  
           
           
        
           
       


  