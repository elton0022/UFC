#-------------------Indicadores de Passagem ------------------
# Exercício 1

n = int(input("Digite o valor de n: "))
Epar = True
    
while n + 1 > 1:
    x = int(input("Digite o valor para a senquencia: "))
    if x % 2 == 1:
        Epar = False
    n-=1
print('Reultado: ',Epar)


# Exercício 2

n = int(input("Digite o valor de n: "))
Ehprimo = True
i = 2
    
if n > 1:
    while i != n:
        if n % 2 == 0:
            Ehprimo = False
        i+=1
            
    if Ehprimo:
        print('E primo')
    else:
        print('Nao e primo')
else:
    print('Digite um n > 1')
        
# Exercício 3

    n = int(input("Digite o valor de n: "))
    resultado = False
    
    if n > 1 :
        while n > 0:
            x = n%10
            n = n/10
            y = n%10
            if x == y:
                resultado = True
        print('O resultado e: ',resultado)
    else:
        print('Digite um n > 1')

# Exercício 4

    n = int(input("Digite o valor de n: "))
    m = n
    soma = 0
    x = 0
   
    while n > 0:
        v = int(input("Digite um valor para n: "))
        if n == m:
            x+=v
        if n==1:
            x+=v
        soma+=v
        n-=1
    formula = (m * x) / 2
    if soma == formula:
        print('E uma PA')
    else:
        print('Nao e uma PA')


