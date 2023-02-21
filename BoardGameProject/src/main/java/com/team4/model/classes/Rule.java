package com.team4.model.classes;

import javax.persistence.Entity;

@Entity
public class Rule {
	private String rule;
	
	public Rule() {
		
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}
