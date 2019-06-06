package br.ufc.crateus.hashtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {

		SeparateChainingHashMap<String,String> hash = new SeparateChainingHashMap<>(4);
//		LinearProbingHashMap<Integer, String> a = new LinearProbingHashMap <>(3);
//		
//     	hash.loadingWorks();
//     	hash.print();
		hash.analyze();
//		String a = "qteste";
//		String b = "teste";
//		
//			System.out.println(hash.compara(a, b));
//		
//		hash.put("aaaa", "dsadsadsa");
//		hash.put("dfg", "aaaaaaaa");
//		hash.put("teste","xxsxsxsx");
//
//		hash.print();
//		
//		hash.Case1("tester");
//		String c = "aaaaa";
//		System.out.println(c.hashCode());
//				
		
	}

}
