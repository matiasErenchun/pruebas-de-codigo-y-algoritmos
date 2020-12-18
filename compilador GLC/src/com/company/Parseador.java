package com.company;

import java.util.ArrayList;

public abstract class Parseador {
	protected ArrayList<String> tokens;
	protected Parseador() {
		this.tokens = new ArrayList<String>();
	}
	public void agregarToken(String token) {
		this.tokens.add(token);
	}
	public abstract boolean estaCorrecto();
}
