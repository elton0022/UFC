package br.ufc.crateus.analise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;

public class Sintatico {
	static ObservableList<Token> tokens;
	private List<String> ids = new ArrayList<>();
	private HashMap<String, String> identificadores = new HashMap<>();
	int index = 0;
	private String erros = "";

	public String getErros() {
		return erros;
	}

	public void setErros(String erros) {
		this.erros = erros;
	}

	public Sintatico(ObservableList<Token> tokens) {
		this.tokens = tokens;
	}

	public void iniciaAnalise() {
		try {
			programa();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void programa() throws CompiladorException {
		validaLexema("program");
		if (verificaTipo("Id")) {
			proximoToken();
			validaLexema(";");

			declaracoesVariaveis();
			
			
			validaLexema("begin");
			comandosOpcionais();
			validaLexema("end");
			try {
				validaLexema(".");
			} catch (NullPointerException e) {
				erros = erros + "Falta o ponto apï¿½s o end" + "\n";

				System.err.println("Falta o ponto apï¿½s o end");
			}
		} else {
			erros = erros + "O token " + tokenAtual() + " nao ï¿½ igual ao Id\n";
			throw new SintaticoException("O token " + tokenAtual() + " nao ï¿½ igual ao Id");
		}
	}

	private Token tokenAtual() {
		if (index < tokens.size())
			return tokens.get(index);
		else
			return null;
	}

	private boolean verificaLexema(String lexema) throws SintaticoException {
		if (tokenAtual().getLexema().equals(lexema))
			return true;
		else
			return false;
	}

	private boolean verificaTipo(String tipo) throws SintaticoException {
		if (tokenAtual().getTipo().equals(tipo))
			return true;
		else
			return false;
	}

	private void validaLexema(String lexema) throws SintaticoException {
		if (verificaLexema(lexema))
			proximoToken();
		else {
			erros = erros + "O token " + tokenAtual() + " nao ï¿½ igual a: " + lexema + "\n";
			throw new SintaticoException("O token " + tokenAtual() + " nao ï¿½ igual a: " + lexema);
		}
	}

	private void validaTipo(String tipo) throws SintaticoException {
		if (verificaTipo(tipo))
			proximoToken();
		else {
			erros = erros + "O token " + tokenAtual() + " nao é ao tipo:" + tipo;
			throw new SintaticoException("O token " + tokenAtual() + " nao é ao tipo:" + tipo);
		}
	}

	private void proximoToken() throws SintaticoException {
		index++;
	}

	private void retornaToken() throws SintaticoException {
		index--;
	}

	private void declaracoesVariaveis() throws CompiladorException { 
		if (verificaLexema("var")) {
			proximoToken();
			while (listaDeclaracoesVariaveis())
				;
		}
	}

	private boolean listaDeclaracoesVariaveis() throws CompiladorException {
		if (verificaTipo("Id")) {
			ids.add(tokenAtual().getLexema()); 
			proximoToken();
			if (verificaLexema(",")) {
				proximoToken();
				listaDeclaracoesVariaveis();
			} else {
				validaLexema(":");
				tipo();
				validaLexema(";");
			}
			if (tokenAtual().getLexema().equals("begin")) {
				return false;
			}
			return true;
		} else {
			erros = erros + "O token " + tokenAtual() + " não é um Id";
			throw new SintaticoException("O token " + tokenAtual() + " nao é um Id");
		}
	}

	private void tipo() throws SintaticoException {
		if (verificaLexema("integer") || verificaLexema("double") || verificaLexema("boolean") || verificaLexema("char")
				|| verificaLexema("pointer") || verificaLexema("string") || verificaLexema("array")) {
			armazenaIds();
			ids.clear();
			proximoToken();
			return;
		} else {
			erros = erros + "Nao foi declarado um tipo pra essa variavel, token: " + tokenAtual();
			throw new SintaticoException("Nao foi declarado um tipo pra essa variavel, token: " + tokenAtual());
		}
	}

	private void armazenaIds() throws SintaticoException{
		for (String s : ids) {
			if (identificadores.get(s) == null) {
				identificadores.put(s, tokenAtual().getLexema());
			} else {
				erros+= "O identificador "+ s +" já foi usado em uma declaração";
				throw new SintaticoException("O identificador "+ s +" já foi usado");
			}
			
		}

	}

	private void comandosOpcionais() throws CompiladorException {
		if (listaDeComandos())
			;
	}

	private boolean listaDeComandos() throws CompiladorException {
		if (comando()) {
			todosOsComandos();
			return true;
		} else {
			return false;
		}
	}

	private void todosOsComandos() throws SintaticoException, CompiladorException {
		if (verificaLexema(";")) {
			proximoToken();
			comando();
			todosOsComandos();
		}
	}

	private boolean comando() throws CompiladorException {
		String type = "";
		String typeAtual = "";
		if (verificaTipo("Id")) {
			if (identificadores.get(tokenAtual().getLexema()) == null) {
				throw new SintaticoException("identificador não declarado");
			} else {
				typeAtual = tokenAtual().getLexema();
			}
			for (Map.Entry<String, String> ids : identificadores.entrySet()) {
				if (typeAtual.equals(ids.getKey()))
					type = ids.getValue();
			}
			proximoToken();
			if (verificaLexema(":=")) {

				proximoToken();
				if (fator1(type) || fator2(type) || fator3(type))
					return true;
				else {
					erros = erros + "Atribuição Inválida";
					throw new SintaticoException("Atribuição Inválida");
				}
			} else
				retornaToken();
		}

		if (verificaLexema("if")) {
			proximoToken();
			if (verificaLexema("(")) {
				proximoToken();
				expressao();
				validaLexema(")");
				validaLexema("then");
				comando();
				opElse();
				return true;
			}
			expressao();
			validaLexema("then");
			comando();
			opElse();
			return true;
		} else
			return false;
	}

	String valor1 = "", valor2 = "";

	private boolean fator1(String tipo) throws SintaticoException {

		if (verificaTipo("Id") && comparaTipos(tokenAtual().getLexema()).equals(tipo)
				|| tokenAtual().getTipo().equals(tipo)) {
			valor1 = comparaTipos(tokenAtual().getLexema());
			if (verificaTipo("Id") && identificadores.get(tokenAtual().getLexema()) == null) {
				erros += "identificador não declarado :" + tokenAtual().getLexema();
				throw new SintaticoException("Identificador não declarado :" + tokenAtual().getLexema());
			}
			proximoToken();
			if (verificaTipo("Operador aritmetico")) {
				proximoToken();
				valor2 = comparaTipos(tokenAtual().getLexema());
				if (valor1 != valor2 && (valor1 != "err" && valor2 != "err")) {
					return false;
				}
				fator1(tipo);
				fator2(tipo);
			}

			return true;
		} else
			return false;
	}

	private boolean fator2(String tipo) throws SintaticoException {
		if (verificaTipo("integer") || verificaTipo("double") && comparaTipos(tokenAtual().getLexema()).equals(tipo)) {
			proximoToken();
			if (verificaTipo("Operador aritmetico")) {
				proximoToken();
				fator1(tipo);
				fator2(tipo);
			}
			return true;
		}

		if (verificaLexema("(")) {
			proximoToken();
			fator1(tipo);
			fator2(tipo);
			validaLexema(")");

			if (verificaTipo("Operador aritmetico")) {
				proximoToken();
				fator1(tipo);
				fator2(tipo);
			}

			return true;
		}

		else
			return false;
	}

	private boolean fator3(String tipo) throws SintaticoException {

		if (verificaTipo("string") && comparaTipos(tokenAtual().getLexema()).equals(tipo)) {
			proximoToken();
			if (verificaLexema("+")) {
				proximoToken();
				fator3(tipo);
			}
			return true;
		} else
			return false;
	}

	private String comparaTipos(String id) {
		for (Map.Entry<String, String> ids : identificadores.entrySet()) {
			if (id.equals(ids.getKey())) {
				return ids.getValue();
			}
		}
		return "err";
	}

	private void expressao() throws SintaticoException {
		if (fator1(tokenAtual().getTipo()) || fator2(tokenAtual().getTipo())) {
			if (verificaTipo("Operador relacional")) {
				proximoToken();
				if (fator1(tokenAtual().getTipo()) || fator2(tokenAtual().getTipo())) {
				} else {
					erros = erros + "Ausência de um segundo fator";
					throw new SintaticoException("Ausência de um segundo fator");
				}
			} else {
				erros = erros + "Ausência de um segundo fator";
				throw new SintaticoException("Ausência de um segundo fator");
			}
		} else {
			erros = erros + "Ausência do primeiro fator :" + tokenAtual();
			throw new SintaticoException("Ausência do primeiro fator");
		}
	}

	private void opElse() throws CompiladorException {
		if (verificaLexema("else")) {
			proximoToken();
			comando();
		}
	}

}
