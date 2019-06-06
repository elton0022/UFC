package br.ufc.crateus.hashtable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class SeparateChainingHashMap<K, V> implements Map<K, V> {
	
	private Node[] table;
	private int size;
	
	private static class Node {
		
		Object key;
		Object value;
		Node next;
		
		Node(Object key, Object value, Node next) {
			
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	private static class Term {
		
		int exp;
		String coef;
		
		Term(int exp, String coef) {
			
			this.exp = exp;
			this.coef = coef;
		}
	}
	
	
	public SeparateChainingHashMap() {
		this(97);
	}
	
	public SeparateChainingHashMap(int m) {
		
		this.table = new Node[m];
		size = 0;
	}
	
	private Node getNode(K key) {
		
		int i = hash(key);
		
		for(Node n=table[i]; n!=null; n=n.next) {
			
			if(key.equals(n.key)) {
				
				return n;
			}
		}
		
		return null;
	}
	
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % table.length; // Remove bit signal
	}
	
	@Override
	public void put(K key, V value) {
		
		if ((this.size / table.length) >= 8) resize(2 * this.size);
		
		int i = hash(key);
		for (Node n = table[i]; n != null; n = n.next) { 
			if (key.equals(n.key)) {
				n.value = value;
				return;
			}
		}
		table[i] = new Node(key, value, table[i]);
		size++;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		
		Node n = getNode(key);
		
		return (n!=null) ? (V) n.value : null;
	}
	
	@Override
	public void remove(K key) {
		
		int i = hash(key);
		
		Node tmp = new Node(null, null, table[i]);
		
		for(Node n = tmp; n.next!=null; n=n.next) {
			
			if(key.equals(n.next.key)) {
				n.next = n.next.next;
				this.size--;
				if((this.size / table.length) == 2) resize(table.length/2);
				break;
			}
		}
		
		table[i] = tmp.next;
	}
	
	private void resize(int newLength) {
		
		SeparateChainingHashMap <K,V> tmp = new SeparateChainingHashMap<>(newLength);
		for(int i = 0; i < table.length;i++) {
			for(Node n= table[i];n != null;n = n.next) {
				tmp.put((K)n.key, (V)n.value);
			}
		table = tmp.table;
		
		}
	}
	
	//@Questao 10
	public void lists(int N) {
		SeparateChainingHashMap <Integer,Integer> tmp = new SeparateChainingHashMap<>(100);
		Random r = new Random();
		int bigger = 0;
		int smaller = N;
		for(int  i = 0; i <= N;i++) {
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
	
	//@Item a)
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
	//@Item b)
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
	//Item c)
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
	
	
	public void print() {
		for(int i = 0; i < table.length;i++) {
			for(Node x = table[i]; x!=null; x = x.next) {
				System.out.println("Linha-"+i+": Chave-"+(String)x.key+": Valor-"+x.value);
			}
		}
	}
	
	public Iterable<String> keys() {
		List<String> list = new ArrayList<>(size);
		for (int i = 0; i < size; i++)
			for (Node l =table[i]; l != null; l = l.next) 
				list.add((String)l.key);
		return list;
	}
	

	
}
