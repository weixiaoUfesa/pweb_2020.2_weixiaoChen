impar=1
par=0
while True:
    str=input("digitre registrar ou exibir1")
    if str=='registrar': 
        print(impar)
        print(par)
        break
    else:
      if (str%2)==0 :   
       par=par+str
      else:
       impar=impar*str