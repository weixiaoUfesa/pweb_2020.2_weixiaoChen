
impar=1
par=0
while True:
    str=int(input("digitrar numero"))
    if str<=0 : 
        print(impar)
        print(par)
        break
    else:
      if (str%2)==0 :   
       par=par+str
      else:
       impar=impar*str