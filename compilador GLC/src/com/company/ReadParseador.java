package com.company;

public class ReadParseador extends Parseador {
	@Override
	public boolean estaCorrecto() {
		if (this.tokens.size() != 2) {
			return false;
		}
		String variable = this.tokens.get(1); // Sacamos lo que supuestamente es una variable
		if (!variable.matches("^[-]{0,1}[$][a-zA-Z0-9]+$")) {
			return false;
		}
		String keyword = this.tokens.get(0); // Sacamos lo que supuestamente es un read
		if (!keyword.equals("read")) {
			return false;
		}
		return true;
	}
}
