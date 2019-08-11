/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.crateus.analise;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class Lexico {

    private List<Token> reservadas = new ArrayList<>();
    public List<Token> tipos = new ArrayList<>();
    public List<Token> opRelacionais = new ArrayList<>();
    public List<Token> opAritmeticos = new ArrayList<>();
    public List<Token> delimitadores = new ArrayList<>();

    public Lexico(String armazena) {
        reservadas.add(new Token("Palavra reservada", "PROGRAM", "program", ""));
        reservadas.add(new Token("Palavra reservada", "IF", "if", ""));
        reservadas.add(new Token("Palavra reservada", "USES", "uses", ""));
        reservadas.add(new Token("Palavra reservada", "BEGIN", "begin", ""));
        reservadas.add(new Token("Palavra reservada", "CONST", "const", ""));
        reservadas.add(new Token("Palavra reservada", "DEFAULT", "default", ""));
        reservadas.add(new Token("Palavra reservada", "FOR", "for", ""));
        reservadas.add(new Token("Palavra reservada", "FUNCTION", "function", ""));
        reservadas.add(new Token("Palavra reservada", "IN", "in", ""));
        reservadas.add(new Token("Palavra reservada", "IS", "is", ""));
        reservadas.add(new Token("Palavra reservada", "INLINE", "inline", ""));
        reservadas.add(new Token("Palavra reservada", "BOOL", "bool", ""));
        reservadas.add(new Token("Palavra reservada", "WRITE", "write", ""));
        reservadas.add(new Token("Palavra reservada", "PROCEDURE", "procedure", ""));
        reservadas.add(new Token("Palavra reservada", "READ", "read", ""));
        reservadas.add(new Token("Palavra reservada", "TYPE", "type", ""));
        reservadas.add(new Token("Palavra reservada", "WRITELN", "writeln", ""));
        reservadas.add(new Token("Palavra reservada", "PROCEDURE", "procedure", ""));
        reservadas.add(new Token("Palavra reservada", "READLN", "readln", ""));
        reservadas.add(new Token("Palavra reservada", "THEN", "then", ""));
        reservadas.add(new Token("Palavra reservada", "END", "end", ""));
        reservadas.add(new Token("Palavra reservada", "ELSE", "else", ""));
        reservadas.add(new Token("Palavra reservada", "DO", "do", ""));
        reservadas.add(new Token("Palavra reservada", "TO", "to", ""));
        reservadas.add(new Token("Palavra reservada", "OF", "of", ""));
        reservadas.add(new Token("Palavra reservada", "WHILE", "while", ""));
        reservadas.add(new Token("Palavra reservada", "DOWNTO", "downto", ""));
        reservadas.add(new Token("Palavra reservada", "IMPLEMENTATION", "implementation", ""));
        reservadas.add(new Token("Palavra reservada", "VAR", "var", ""));
        reservadas.add(new Token("Palavra reservada", "CLASS", "class", ""));
        reservadas.add(new Token("Palavra reservada", "AS", "as", ""));
        reservadas.add(new Token("Palavra reservada", "LABEL", "label", ""));
        reservadas.add(new Token("Palavra reservada", "TRY", "try", ""));
        reservadas.add(new Token("Palavra reservada", "FINALLY", "finally", ""));
        reservadas.add(new Token("Palavra reservada", "FILE", "file", ""));
        reservadas.add(new Token("Palavra reservada", "LIBRARY", "library", ""));
        reservadas.add(new Token("Palavra reservada", "OBJECT", "object", ""));
        reservadas.add(new Token("Palavra reservada", "UNIT", "unit", ""));
        reservadas.add(new Token("Palavra reservada", "PROPERTY", "property", ""));
        /////////////////////TIPOS//////////////////////////////////////////////
        tipos.add(new Token("TIPO", "INTEGER", "integer", ""));
        tipos.add(new Token("TIPO", "DOUBLE", "double", ""));
        tipos.add(new Token("TIPO", "BOOLEAN", "boolean", ""));
        tipos.add(new Token("TIPO", "CHAR", "char", ""));
        tipos.add(new Token("TIPO", "POINTER", "pointer", ""));
        tipos.add(new Token("TIPO", "STRING", "string", ""));
        tipos.add(new Token("TIPO", "ARRAY", "array", ""));
        ///////////////////OPERADORES RELACIONAIS///////////////////////////////
        opRelacionais.add(new Token("Operador relacional", "MENORQ", "<", ""));
        opRelacionais.add(new Token("Operador relacional", "MENORIGUAL", "<=", ""));
        opRelacionais.add(new Token("Operador relacional", "DIFERENTE", "<>", ""));
        opRelacionais.add(new Token("Operador relacional", "MAIORQ", ">", ""));
        opRelacionais.add(new Token("Operador relacional", "MAIORIGUAL", ">=", ""));
        opRelacionais.add(new Token("Operador relacional", "IGUAL", "=", ""));
        opRelacionais.add(new Token("Operador atribuição", "ATRIB", ":=", ""));
        opRelacionais.add(new Token("Operador lógico", "OR", "or", ""));
        opRelacionais.add(new Token("Operador lógico", "NOT", "not", ""));
        opRelacionais.add(new Token("Operador lógico", "AND", "and", ""));
        /////////////////OPERADORES ARITMETICOS///////////////////////////////
        opAritmeticos.add(new Token("Operador aritmetico", "SOMA", "+", ""));
        opAritmeticos.add(new Token("Operador aritmetico", "SUB", "-", ""));
        opAritmeticos.add(new Token("Operador aritmetico", "MULT", "*", ""));
        opAritmeticos.add(new Token("Operador aritmetico", "DIVREAIS", "/", ""));
        opAritmeticos.add(new Token("Operador aritmetico", "DIVINT", "Div", ""));
        opAritmeticos.add(new Token("Operador aritmetico", "RESTOINT", "Mod", ""));
        /////////////////DELIMITADORES ///////////////////////////////////////
        delimitadores.add(new Token("Pontuação", "PTVIRGULA", ";", ""));
        delimitadores.add(new Token("Pontuação", "PONTO", ".", ""));
        delimitadores.add(new Token("Pontuação", "VIRGULA", ",", ""));
        delimitadores.add(new Token("Pontuação", "DOISPONTOS", ":", ""));
        delimitadores.add(new Token("Pontuação", "INTERROG", "?", ""));
        delimitadores.add(new Token("Pontuação", "ABRPAREN", "(", ""));
        delimitadores.add(new Token("Pontuação", "FECHPAREN", ")", ""));

        	
    }

    public List<Token> getReservadas() {
        return reservadas;
    }

    public void setReservadas(List<Token> reservadas) {
        this.reservadas = reservadas;
    }

    public Token autoReservadas(String palavra) {
        if (palavra == null) {
            return null;
        }
        for (Token t : reservadas) {
            if (t.getLexema().equals(palavra)) {
                return t;
            }
        }
        return null;
    }

    public Token autoDelimitadores(String palavra) {
        for (Token d : delimitadores) {
            if (d.getLexema().equals(palavra)) {
                return d;
            }
        }
        return null;
    }

    public Token autoOpAritmeticos(String palavra) {
        for (Token d : opAritmeticos) {
            if (d.getLexema().equals(palavra)) {
                return d;
            }
        }
        return null;
    }

    public Token autoOpRelacionais(String palavra) {
        for (Token d : opRelacionais) {
            if (d.getLexema().equals(palavra)) {
                return d;
            }
        }
        return null;
    }

    public Token autoTipos(String palavra) {
        for (Token d : tipos) {
            if (d.getLexema().equals(palavra)) {
                return d;
            }
        }
        return null;
    }

    public Token autoId(String palavra) {
        int aux = 0, auxFirst = 0;
        char[] c = palavra.toCharArray();

        for (int i = 0; i < c.length; i++) {
         
            if (!(Character.isLetter(c[0]) || c[i] == '_')) {
                auxFirst = 1;
            }
            if (Character.isLetterOrDigit(c[i]) || c[i] == '_' ) {
                aux++;
            }
        }

        if (aux == c.length && auxFirst == 0) {
            return new Token("Id", "ID", palavra, "");
        }

        return null;

    }

    public Token autoDigitos(String s) {
        char[] c = s.toCharArray();
        int d = 0;
        if (Character.isDigit(c[0])) {
            for (int i = 0; i < c.length; i++) {
                if (Character.isDigit(c[i])) {
                    d++;
                }
            }
        } else {
            return null;
        }
        if (d == s.length()) {
            return new Token("integer", "DIGITO", s, "");
        }
        return null;
    }

    public Token autoNumReal(String s) {
        char[] c = s.toCharArray();
        int d = 0, ponto = 0;
        if (Character.isDigit(c[0])) {
            for (int i = 0; i < c.length; i++) {
                if (Character.isDigit(c[i])) {
                    d++;
                }
                if (c[i] == '.' && c[c.length - 1] != '.') {
                    ponto++;
                }
            }
        } else {
            return null;
        }
        if (d == s.length() - ponto && ponto == 1) {
            return new Token("double", "NUMREAL", s, "");
        }
        return null;
    }

    public Token autoString(String s) {
        int d = 0;
        char[] c = s.toCharArray();
        if (c[0] == '\'' && c[c.length - 1] == '\'') {
            return new Token("string", "STRING", s, "");
        }
        return null;

    }

}
