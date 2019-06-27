package br.ufc.crateus.eda.btree;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		ArvoreB a = new ArvoreB(4,"teste.txt");
		
		a.inserir(5,231);
		
	}
}