package com.company;

public class AsginarParseador extends Parseador {
	@Override
	public boolean estaCorrecto() {
		if (this.tokens.size() % 2 == 0 || this.tokens.size() < 3) {
			return false;
		}
		for (int i = 2; i < this.tokens.size(); i++) {
			if (i % 2 == 0 && !this.tokens.get(i).matches("^[-]{0,1}[$][a-zA-Z0-9]+|[-]{0,1}[0-9]+$")) {
				return false;
			} else if (i % 2 != 0 && !this.tokens.get(i).matches("[+]|[-]|[*]|[/]|[%]")) {
				return false;
			}
		}
		String igual = this.tokens.get(1);
		if (!igual.equals("=")) {
			return false;
		}
		String variable = this.tokens.get(0);
		if (!variable.matches("^[-]{0,1}[$][a-zA-Z0-9]+$")) {
			return false;
		}
		return true;
	}
}
