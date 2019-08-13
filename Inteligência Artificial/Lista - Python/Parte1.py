#------------Expressções lógicas e operadores relacionais-----------

# Exercício 1

n = int(input("Digite o ano: "))

if (n % 4 == 0) and (n%100 != 0):
    print('E bisexto')
if n % 400 == 0:
    print('E bisexto')

# Exercício 2

    # dados da Penny
    nome = "Penny"
    sexo = "feminino"
    altura = 167
    peso   =  52
    idade  =  25
    cabelo = "loiro"
    escolaridade = "medio"

    # Complete a expressão que determina se a Penny é compatível
    # com as preferências do Leonard
    compativel = True
    
    if (sexo == "feminino") and (altura >= 155 and altura <= 170):
        print('true')
    else:
        compativel = False
    if peso >= 45 and peso <= 65:
        print('true')
    else:
        compativel = False
    if (idade >= 25 and idade <= 35):
        print('true')
    else:
        compativel = False
    if cabelo == "loiro" and escolaridade != "PhD":
        print('true')
    else:
        compativel = False
    
    print("Candidata", nome, ":", compativel)

