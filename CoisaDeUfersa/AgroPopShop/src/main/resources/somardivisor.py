
def Verificar(int):
   if int>=0:
     print("positivo")
   else:
     print("negativo")
   return

def SomaDivisores(int):
    soma=0
    for i in range(1,int):
        if int%i==0:soma=soma+i
    return soma

for i in range(5):
    str1=int(input('digite  numero'))
    Verificar(str1)
    print(SomaDivisores(str1))
            
            



