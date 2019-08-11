
package br.ufc.crateus.analise;

import java.util.*;

abstract class CompiladorException extends Exception {	
	static void imprimeErro(String detalhe, String tipo) {
		System.out.println("Erro " + tipo + ": " + detalhe);
	}
}

class SintaticoException extends CompiladorException {
	public SintaticoException(String detalhe) {
		imprimeErro(detalhe, "Sintatico");
	}
}

class LexicoException extends CompiladorException {
	public LexicoException(String detalhe) {
		imprimeErro(detalhe, "Lexico");
	}
}

