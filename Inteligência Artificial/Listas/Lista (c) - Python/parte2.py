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
    count = 0
    lista = []
     
    while n != 0:
        lista.append(int(input("Digite um numero ")))
        n-=1
    for i in lista:
        for j in lista:
            if i == j:
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


# Exercício 3

#-----------------------------------------------
def indice(item, lista):
    for i in range(0,len(lista)):
        if lista[i] == item:
            return i
    
    

#-----------------------------------------------
# testes
lista  = [1, "oi", 3.14, 7, True]

print(len(lista))


# teste 1
if indice("oi",lista) == 1:
    print("Passou no primeiro teste! :-)")
else:
    print("Nao passou no primeiro teste! :-(")

# teste 2
if indice(True,lista) == 4:
    print("Passou no segundo teste! :-)")
else:
    print("Nao passou no segundo teste! :-(")

# teste 3
if indice(False,lista) == None:
    print("Passou no terceiro teste! :-)")
else:
    print("Nao passou no terceiro teste! :-(")


# Exercício 4

def main():
    n = int(input("Digite o n: "))
    count = 0
    lista = []
     
    while n != 0:
        lista.append(int(input("Digite um numero ")))
        n-=1
    for i in lista:
        for j in lista:
            if i == j:
                count+=1
        print(i , " apareceu ", count)
        while count > 1:
            lista.remove(i)
            count-=1
        count = 0
    print(lista)

#-----------------------------------------------
def indice(item, lista):
    for i in range(0,len(lista)):
        if lista[i] == item:
            return i


#-----------------------------------------------
if __name__ == '__main__':
    main()

# Exercício 5

def soma_elementos(ini, fim, lista):
    soma = 0
    for i in lista:
        soma+=i
    return soma

# testes
lista  = [1, 2, 3, 4, 5]

# teste 1
if soma_elementos(0,len(lista),lista) == 15:
    print("Passou no primeiro teste! :-)")
else:
    print("Nao passou no primeiro teste! :-(")

# teste 2
if soma_elementos(1,4,lista) == 9:
    print("Passou no segundo teste! :-)")
else:
    print("Nao passou no segundo teste! :-(")

# teste 3
if soma_elementos(2,2,lista) == 0:
    print("Passou no terceiro teste! :-)")
else:
    print("Nao passou no terceiro teste! :-(")

# outros testes


# Exercício 6




