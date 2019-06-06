//Nesse arquivo contêm as implementações de forma separada do projeto, a fim de uma melhor visualização
//Os metódos encontram-se implementados na classe SeparateChainingHashMap do projeto "TableHash"

//@Questao 2 - a)
	public void polynomials(LinkedList<Term> p1, LinkedList<Term> p2) {
		SeparateChainingHashMap <Integer,String> hash = new SeparateChainingHashMap<>(100);
		LinkedList<Term> L = new LinkedList<>();

		for (Term a :p1) {
			for (Term b :p2) {
				if(a.coef.equals(b.coef)) L.add(new Term(a.exp + b.exp,b.coef));
				else L.add(new Term(a.exp + b.exp,a.coef+b.coef));
			}
		}
		for(Term t :L) hash.put(t.exp,t.coef);
		hash.print();
	}

//@Questão 4
	public void analyze() {
		SeparateChainingHashMap<String,String> hash = loadingWorks();
		try {
		      FileReader arq = new FileReader("dicionarioPessoal.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = ""; 
		      while (linha != null) {
		    	  linha = lerArq.readLine();
		    	  if(linha!=null && hash.get(linha) == null)hash.cases(linha);
		      }
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
	}
	
	private SeparateChainingHashMap<String,String> loadingWorks() {
		SeparateChainingHashMap<String,String> hash = new SeparateChainingHashMap<>(100);
		try {
		      FileReader arq = new FileReader("dicionario.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = ""; 
		      while (linha != null) {
		    	linha = lerArq.readLine();
		    	if(linha != null)hash.put(linha,linha);
		      }
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		return hash;
	}
	
	private void cases(String a){
		for(int i = 0; i < table.length;i++) {
			for(Node x = table[i]; x!=null; x = x.next) {
				compara1(a, (String)x.key);
				compara2(a, (String)x.key);
				compara3(a, (String)x.key);
			}
		}
	}
	
	//@Questão 4 - a)
	private void compara1 (String a, String b) {
		Character x = null;
		String aux = "";
		if(a.length() == b.length() + 1) {
			for(Character c : a.toCharArray()) {
				if(b.contains(String.valueOf(c))) aux = aux+c;
				else x = c;
			}
		}
		if(b.equals(aux)) System.out.println("A letra -"+ x +"- não pertence a palavra "+b);
	}
	//@Questão 4 - b)
	private void compara2 (String a, String b) {
		Character x = null;
		String aux = "";
		if(a.length() + 1 == b.length()) {
			for(Character c : b.toCharArray()) {
				if(a.contains(String.valueOf(c))) aux = aux+c;
				else x = c;
			}
		}
		if(a.equals(aux)) System.out.println("Ausencia da letra -"+ x +"- na palavra "+a +" = "+b);
	}
	//@Questão 4 - c)
	private void compara3 (String a, String b) {
		char[] a1 = a.toCharArray();
		char[] b1 = b.toCharArray();
		String part = "";
		String part2 = "";
		Character l1 = ' ';
		Character l2 = ' ';
		int aux = 0;
		if(a.length() == b.length()) {
			for(int i = 0; i < a.length();i++) {
				if(i+1 == a.length()) break;
				if(aux == 1) part2+=a1[i+1]; 
				if(a1[i] != b1[i] && a1[i+1] != b1[i+1] && aux == 0) {
					l1 = a1[i+1];
					l2 = a1[i];
					aux++;
				}else if(a1[i] == b1[i] && aux == 0) part+=a1[i];
			}
		}
		String result = part+l1+l2+part2;
		if(b.equals(result)) System.out.println("Caracteres invertidos-"+l2+"-"+l1+" na palavra "+a+" == "+b);
	}

	//@Questao 10
	public void lists(int N) {
		SeparateChainingHashMap <Integer,Integer> tmp = new SeparateChainingHashMap<>(100);
		Random r = new Random();
		int bigger = 0;
		int smaller = N;
		for(int  i = 0; i < N;i++) {
			int value = r.nextInt();
			tmp.put(value,value);
		}
		for(int  i = 0; i < table.length;i++) {
			if(goThrough(tmp.table[i]) < smaller) smaller = goThrough(tmp.table[i]);
			if(goThrough(tmp.table[i]) > bigger) bigger = goThrough(tmp.table[i]);
		}
		tmp.print();
		System.out.println("Maior lista: "+ bigger +"\n Menor lista: "+smaller);
	}
	
	private int goThrough(Node i) {
		int d = 0;
		for(Node x = i; x != null; x = x.next) d++;
		return d;
	}

	/*									Relatório
			No método lists geramos N chaves aleatórias através da primeira estrutura
		de repetição presente no método, e inserimos na tabela. Repare que para o
		primeiro caso de teste (10^3) teremos o total de 1000 chaves, ou seja isso
		"quebra" a condição da quantidade de nós que teremos em cada possição da
		tabela (N/100 == 10 > 8) assim a tabela teria que dobrar seu tamanho para
		uma melhor distribuição. Assim para 10^x tal que x > 2 teriamos que conti-
		nuar a  dobrar o tamanho da tabela.
			Na segunda estrutura de repetição presente no método iriamos percorrer
		cada lista de todas as posições da tabela a fim de armazenar nas variáveis
		smaller e bigger, o tamanho da menor lista e da maior lista respectivamente
		através do método goThrough.

	*/
