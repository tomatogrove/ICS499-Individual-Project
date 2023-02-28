package com.team4.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Rule {
	
	@Id
	@GeneratedValue
	private Long ruleID;
	private String rule;

	public Rule() {
		
	}

	public Long getRuleID() {
		return ruleID;
	}
	
	public void setRuleID(Long ruleID) {
		this.ruleID = ruleID;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
}