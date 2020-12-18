package com.company;

import java.util.ArrayList;

/**
 * 
 */
public class SyntaxValidator {
	private boolean isValid;
	public boolean getIsValid() {
		return this.isValid;
	}
	public SyntaxValidator(ArrayList<String> line) {
		this.isValid = true;
		Parseador parseador = null;
		if (line.get(0).equals("read")) {
			parseador = new ReadParseador();
		} else if (line.get(0).equals("if")) {
			parseador = new IfParseador();
		} else if (line.get(0).equals("while")) {
			parseador = new WhileParseador();
		} else if (line.get(0).equals("write")){
			parseador = new WriteParseador();
		} else if (line.get(0).startsWith("$")) {
			parseador = new AsginarParseador();
		} else if (line.get(0).equals("endif") && line.size() == 1) {
			this.isValid = true;
		} else if (line.get(0).equals("wend") && line.size() == 1) {
			this.isValid = true;
		} else if (line.get(0).equals("else") && line.size() == 1) {
			this.isValid = true;
		} else {
			this.isValid = false;
		}
		if (parseador != null) {
			for (String token : line) {
				parseador.agregarToken(token);
			}
			this.isValid = parseador.estaCorrecto();
		}
	}
	///**
	// *  Inicio de la expresión.
	// */
	//private boolean parseE(ArrayList<String> linea, int index) {		
	//	boolean parseT = this.parseT(linea, index);
	//	if(!parseT){
	//		return false;
	//	}
	//	//boolean parseEprima = this.parseEprima(linea, index);	
	//	//if(!parseEprima){
	//	//	return false;
	//	//}
	//	return true;
	//}
//
	//private boolean parseT(ArrayList<String> linea, int index) {
	//	if (index > linea.size() - 1) {
	//		return false;
	//	}
	//	return this.parseJ(linea, index);
	//}
//
	//private boolean parseJ(ArrayList<String> linea, int index) {
	//	boolean parseD = this.parseD(linea, index);
	//	if (!parseD) {
	//		return false;
	//	}
	//	boolean parseNprima = this.parseNprima(linea, index + 1);
	//	if (!parseNprima) {
	//		return false;
	//	}
	//	return true;
	//}
//
	//private boolean parseD(ArrayList<String> linea, int index) {
	//	if (index > linea.size() - 1) {
	//		return false;
	//	}
	//	return linea.get(index).matches("^[0-9]+$");
	//}
//
	//private boolean parseNprima(ArrayList<String> linea, int index) {
	//	if (index > linea.size() - 1) {
	//		return true;
	//	}
	//	return this.parseJ(linea, index + 1);
	//}
	///**
	// * Llamada por parseE y parseEprima1
	// */
	//private boolean parseT(ArrayList<String> linea, int index) {		
	//	boolean parseF = this.parseF(linea, index);	
	//	if (!parseF) {
	//		return false;
	//	}		
	//	boolean parseTprima = this.parseTprima(linea, index);
	//	if (!parseTprima) {
	//		return false;
	//	}
	//	return true;
	//}
//
	//private boolean parseTprima(ArrayList<String> linea, int index) {
	//	return this.contieneSimbolo(linea, index, "*") ? this.parseTprima1(linea, index) : this.parseTprima2(linea, index);
	//}
//
	//private boolean parseTprima1(ArrayList<String> linea, int index) {
	//	index++;
	//	boolean parseF = this.parseF(linea, index);
	//	if (!parseF) {
	//		return false;
	//	}
	//	boolean parseTprima = this.parseTprima(linea, index);
	//	if (!parseTprima) {
	//		return false;
	//	}
	//	return true;
	//}
//
	//private boolean parseTprima2(ArrayList<String> linea, int index) {
	//	return true;
	//}
	//
	///*
	// * Llamada por parseT
	// */
	//private boolean parseF(ArrayList<String> linea, int index) {
	//	boolean abreParentesis = this.contieneSimbolo(linea, index, "(");	
	//	return abreParentesis ? this.parseF1(linea, index) : this.parseF2(linea, index);	
	//}
//
	//private boolean parseF1(ArrayList<String> linea, int index) {
	//	index++;
	//	boolean parseE = this.parseE(linea, index);
	//	if (!parseE) {
	//		return false;
	//	}
	//	boolean parentesisCierre = this.contieneSimbolo(linea, index, ")");
	//	if (!parentesisCierre) {
	//		return false;
	//	}
	//	index++;
	//	return true;
	//}
//
	//private boolean parseF2(ArrayList<String> linea, int index) {		
	//	return this.parseN(linea, index);								
	//}
//
	//private boolean parseN(ArrayList<String> linea, int index) {	
	//	boolean alfanumerico = linea.get(0).matches("^[a-zA-Z0-9]+$");	
	//	if (alfanumerico) {
	//		index++;		 
	//		return true;	
	//	}
	//	return false;	
	//}
//
	//private boolean parseEprima(ArrayList<String> linea, int index) {
	//	return this.contieneSimbolo(linea, index, "+") ? this.parseEprima1(linea, index) : this.parseEprima2(linea, index);
	//}
//
	//private boolean contieneSimbolo(ArrayList<String> linea, int index, String simbolo) {
	//	return index + 1 > linea.size() - 1 ? false : linea.get(index + 1).equals(simbolo);
	//}
//
	//private boolean parseEprima1(ArrayList<String> linea, int index) {
	//	index++;
	//	boolean parseT = this.parseT(linea, index);
	//	if (!parseT) {
	//		return false;
	//	}
	//	index++;
	//	boolean parseEprima = this.parseEprima(linea, index);
	//	if (!parseEprima) {
	//		return false;
	//	}
	//	return true;
	//}
//
	//private boolean parseEprima2(ArrayList<String> linea, int index) {
	//	return true;
	//}
}