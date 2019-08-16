# ------------- Funções -----------------------


# Exercicio 1

# leitura dos valores de entrada
m = int(input("Digite m: "))
n = int(input("Digite n: "))

# calcula o fatorial de m
k = m
k_fat = 1
cont = 1
while cont < k:
    cont += 1       # o mesmo que cont = cont + 1
    k_fat *= cont   # o mesmo que k_fat = k_fat * cont

m_fatorial = k_fat

# calcula o fatorial de n
k = n
k_fat = 1
cont = 1
while cont < k:
    cont += 1       # o mesmo que cont = cont + 1
    k_fat *= cont   # o mesmo que k_fat = k_fat * cont

n_fatorial = k_fat

# calcula o fatorial de m - n
k = m-n
k_fat = 1
cont = 1
while cont < k:
    cont += 1       # o mesmo que cont = cont + 1
    k_fat *= cont   # o mesmo que k_fat = k_fat * cont

mn_fatorial = k_fat

print("Comb(",m,",",n,"): ", m_fatorial/(mn_fatorial * n_fatorial))

# Exercício 2

def main():

    print("0! =", fatorial(0))
    print("5! =", fatorial(5))


def fatorial(k):
    k_fat = 1
    return k_fat

if __name__ == '__main__':
    main()

# Exercicío 3

def main():
   
    print("Combinacao(5,2) =", combinacao(5,2))
    print("Combinacao(10,4) =", combinacao(10,4))


def combinacao(m, n):
   
    k_fat = 1
    cont = 1
    while cont < k:
        cont += 1       # o mesmo que cont = cont + 1
        k_fat *= cont   # o mesmo que k_fat = k_fat * cont

    return k_fat

if __name__ == '__main__':
    main()

# Exercício 4

def main():
    # leitura do n
    n = int(input("Digite n: "))

    cont = 0
    while cont <= n:
        print("Coeficiente de x^%d y^%d: %d"%(n-cont, cont, combinacao(n, cont)))
        cont += 1


def fatorial(k):

    k_fat = 1
    cont = 1
    while cont < k:
        cont += 1       # o mesmo que cont = cont + 1
        k_fat *= cont   # o mesmo que k_fat = k_fat * cont

    return k_fat

def combinacao(m, n):
    return fatorial(m)/(fatorial(n)*fatorial(m-n))

main()

