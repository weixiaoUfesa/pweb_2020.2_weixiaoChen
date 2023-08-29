dict={}
multatotal = 0

class Motorista:
  
   empCount = 0
   multa=0
   def __init__(self, numero, multa):
      self.numero= numero
      self.multa = multa
   def adicionarmulta(multa):
     multa=multa+multa


while True:
    str=input('digite registrar ou exibir :')
    if str=='exibir': 
      
        break
    else:
        str1=input('digite numero')
        str2=int(input('digite multa'))
        if str1 in dict:
             dict.get(str1).adicionarmulta(str2)
             
        else:
              motorista=Motorista(str1,str2)
              dict[str1] = motorista
              print(dict)
              print(dict.get(str1))

        
       
      

