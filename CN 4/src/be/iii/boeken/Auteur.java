package be.iii.boeken;

import java.util.List;

public class Auteur {
	private String initial;

	private String name;

	private List<String> firstname;

	public Auteur() {
		name = null;
		initial = null;
		firstname = null;
	}

	public Auteur(String name, String initial, List<String> firstname) {
		this.name = name;
		this.initial = initial;
		this.firstname = firstname;
	}

	public Auteur(String name, List firstname) {
		this.name = name;
		this.initial = null;
		this.firstname = firstname;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String value) {
		initial = value;
	}

	public List getFirstname() {
		return firstname;
	}

	public void setFirstname(List<String> value) {
		firstname = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		name = value;
	}

}