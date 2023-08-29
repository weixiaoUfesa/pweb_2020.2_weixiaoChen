total=0
maximo=0
Max_motorista=0
while True:
    str=input('digite registrar ou exibir :')
    
    if str=='exibir': 
        print('motorista que obteve o maior número de multas :',Max_motorista)  
        print('numero de multa',maximo)
        print('dinheiro total arrecadado',total)   
        break
    else:
        str1=int(input('digite numero de motorista'))
        str2=int(input('digite numero de multa'))
        subtotal=0
        for i in range(str2):
            str3=int(input('digite valor de multa'))
            subtotal=subtotal+str3
        total=total+subtotal
        if str2>maximo:
           maximo = str2
           Max_motorista=str1
        print('numero de motorista',str1)   
        print('precisa pagar multa',subtotal) 
       


  