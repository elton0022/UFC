#---------------------Numeros Reais-----------------

#   Exercicio 1

    n = int(input("Digite a quantidade de fracoes: "))
    m = n
    i = 1
    soma1 = 0
    soma2 = 1
    while n > 0:
        soma1+= 1/n
        n-=1
    while i <= n:
        soma2+= 1/i
        i+=1
        
    print('Soma - D: ',soma1,'Soma - C', soma2)

#  Exercício 2

    n = int(input("Digite a quantidade de termos: "))
    x = int(input("Digite a quantidade de fracoes: "))
    i = 1
    soma = 0
    f = 2
    
    while i <= n:
        fat = 1
        j = 2
        while j <= f:
            fat = fat*j
            j = j + 1
        soma-= x**2 + fat
        i+=1
    
    print(soma)

# Exercício 4

    x_pos = x = float(input("Digite x: "))
    y = float(input("Digite y: "))

    if x < 0:
        x_pos = -x

    face = x_pos <  5 and 0 < y < 8

    boca = x_pos <= 3 and 1 <= y <= 2

    olho = 1 <= x_pos <= 4 and 4 <= y <= 7

    iris = 2 <  x_pos <  3 and 5 <  y <  6

    if iris or face and not (boca or olho):
        print("dentro")
    else:
        print("fora")

# Exercicio 5

    x   = float(input("Digite x: "))
    eps = float(input("Digite epsilon: "))
    
    soma = 1
    fat = 1
    k,tabs = 1,1
    while tabs >= eps:
        pot = x**k
        fat *= k
        t = pot/fat
        tabs = t
        if tabs < 0:
            tabs = -tabs
        soma += t
        k += 1
    
    print("e**(%5.3f) = %7.5f"%(x,soma))

# Exercício 6

   eps = float(input("Digite y: "))
    fat = pot = 1.0
    tabs = soma = 1.0
    k = 1
    while tabs >= eps:
        pot *= x
        fat *= k
        t = pot/fat
        tabs = t
        if tabs < 0.0:
          tabs = -tabs
        soma += t
        k += 1
    print('x: ',x,'soma: ',soma)


