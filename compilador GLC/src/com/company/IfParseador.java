package com.company;

public class IfParseador extends Parseador {

	@Override
	public boolean estaCorrecto() {
		if (this.tokens.size() != 7) {
			return false;
		}
        // then
        String lastword = this.tokens.get(6);
        if(!lastword.equals("then")){
            return false;
        }
        // )
        String finalPar = this.tokens.get(5);
        if(!finalPar.equals(")")){
            return false;
        }
        // var/num
        String varOrNum = this.tokens.get(4);
        if(!varOrNum.matches("^[-]{0,1}[$][a-zA-Z0-9]+|[-]{0,1}[0-9]+$")){
            return false;
        }
        // operador
        String operator = this.tokens.get(3);
        if(!operator.matches("^[<][=]{0,1}|[>][=]{0,1}|[=]{2}|[!][=]$")){
            return false;
        }
        // var
        String var = this.tokens.get(2);
        if(!var.matches("^[-]{0,1}[$][a-zA-Z0-9]+$")){
            return false;
        }
		// (
        String initialPar = this.tokens.get(1);
        if(!initialPar.equals("(")){
            return false;
        }
		// if
        String keyword = this.tokens.get(0);
        if(!keyword.equals("if")){
            return false;
        }
		return true;
	}
	
}
