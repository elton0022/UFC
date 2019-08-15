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


