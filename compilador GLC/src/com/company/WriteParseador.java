package com.company;

public class WriteParseador extends Parseador{
    @Override
    public boolean estaCorrecto() {
		if (this.tokens.size() == 3) {
			return false;
		}
		if (this.tokens.size() > 3) {
			if ((this.tokens.size() - 2) % 2 != 0) {
				return false;
			}
			for (int i = 2; i < this.tokens.size(); i++) {
				if (i % 2 != 0 && !this.tokens.get(i).matches("^[-]{0,1}[$][a-zA-Z0-9]+|[-]{0,1}[0-9]+$")) {
					return false;
				} else if (i % 2 == 0 && !this.tokens.get(i).matches("[+]|[-]|[*]|[/]|[%]")) {
					return false;
				}
			}
		}
        String var = this.tokens.get(1);
        if(!var.matches("^[-]{0,1}[$][a-zA-Z0-9]+|[-]{0,1}[0-9]+$")){
            return false;
        }
		String keyword = this.tokens.get(0);
		if (!keyword.equals("write")) {
			return false;
		}
		return true;
	}
}