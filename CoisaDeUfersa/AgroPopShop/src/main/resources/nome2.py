while True:
        resultado=''
        list=['e', 'do', 'da', 'dos', 'das', 'de', 'di', 'du']
        str1=input('digite nome:')
        new=str1.split()
        for i in new:
            if (i not in list):
                resultado=resultado+i[0].upper()
        print(resultado)
  