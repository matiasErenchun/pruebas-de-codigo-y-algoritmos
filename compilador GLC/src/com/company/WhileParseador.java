package com.company;

public class WhileParseador extends Parseador{
	@Override
	public boolean estaCorrecto() {
		if (this.tokens.size() != 7) {
			return false;
		}
		String doKeyword = this.tokens.get(6);
		if (!doKeyword.equals("do")) {
			return false;
		}
		String rightPar = this.tokens.get(5);
		if (!rightPar.equals(")")) {
			return false;
		}
		String varOrNum = this.tokens.get(4);
		if (!varOrNum.matches("^[-]{0,1}[$][a-zA-Z0-9]+|[-]{0,1}[0-9]+$")) {
			return false;
		}
		String operator = this.tokens.get(3);
		if (!operator.matches("^[<][=]{0,1}|[>][=]{0,1}|[=]{2}|[!][=]$")) {
			return false;
		}
		String variable = this.tokens.get(2);
		if (!variable.matches("^[-]{0,1}[$][a-zA-Z0-9]+|[-]{0,1}[0-9]+$")) {
			return false;
		}
		String leftPar = this.tokens.get(1);
		if (!leftPar.equals("(")) {
			return false;
		}
		String whileKeyword = this.tokens.get(0);
		if (!whileKeyword.equals("while")) {
			return false;
		}
		return true;
	}
}
