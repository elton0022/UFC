# -----------Comando de repetição: while--------------

#Exercício 1

quant = int(input("Digite a quantidade de numeros: "))
soma = 0
while quant > 0:
    n = int(input("Digite um numero para somar: "))
    if n != 0:
        soma+=n
    else:
        print('Digite um numero diferente de 0')
    quant-=1

print('A soma e',soma)

# Exercício 2

n = int(input("Digite a base: "))
k = int(input("Digite o expoente: "))

if k >= 0:
    print(n**k)
else:
    print('Digite um expoente >=0: ')

# Exercício 3

n = int(input("Digite o valor de n: "))
fat = 1
i = 2
while i <= n:
    fat = fat*i
    i = i + 1
print(fat)

# Exercício 4

n = int(input("Digite o valor de n: "))
i = int(input("Digite o valor de i: "))
j = int(input("Digite o valor de j: "))
x = 0
if n > 0 and i > 0 and j > 0:
    while x <= n + 1:
        if (x%i==0) or (x%j==0):
            print(x)
        x+=1
else:
    print('Digite valores maiores que 0')

# Exercício 5

a = int(input("Digite o valor de a: "))
b = int(input("Digite o valor de b: "))
    
while (b != 0):
    r = a % b
    a = b
    b = r
print('MDC: ',a)