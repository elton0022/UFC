#--------------------Funções com listas------------------

# Exercício 1

#-------------------------------------------------
def pertence(item,lista):
    if item in lista:
        return True   
    return False


#---------------------------------------------------
# testes
lista  = [1, "oi", 3.14, 7, True]

# teste 1
if pertence("oi",lista):
    print("Passou no primeiro teste! :-)")
else:
    print("Nao passou no primeiro teste! :-(")

# teste 2
if pertence(True,lista):
    print("Passou no segundo teste! :-)")
else:
    print("Nao passou no segundo teste! :-(")

# teste 3
if not pertence(False,lista):
    print("Passou no terceiro teste! :-)")
else:
    print("Nao passou no terceiro teste! :-(")


# Exercício 2

#-----------------------------------------------
def main():
    n = int(input("Digite o n: "))
    count = 1
    lista = [0]
     
    while n != 0:
        lista.append(int(input("Digite um numero ")))
        n-=1
    for i in lista:
        while count > 1:
            count+=1
        while count > 1:
            lista.remove(i)
            count-=1
        count = 0
    print(lista)
        
#-----------------------------------------------
def pertence(item,lists):
    if item in lists:
        return True
    if item not in lists:
        return False
    if not item in lists:
        return False

#--------------------------------------------------
if __name__ == '__main__':
    main()


