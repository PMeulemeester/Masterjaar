package be.tiwi.bo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"name","firstname","initial"})
public class Auteur {
	private String initial;

	private String name;

	private String[] firstname;

	public Auteur() {
		name = null;
		initial = null;
		firstname = null;
	}

	public Auteur(String name, String initial, String[] firstname) {
		this.name = name;
		this.initial = initial;
		this.firstname = firstname;
	}

	public Auteur(String name, String[] firstname) {
		this.name = name;
		this.initial = null;
		this.firstname = firstname;
	}

        @XmlElement(name="initiaal")
	public String getInitial() {
		return initial;
	}

	public void setInitial(String value) {
		initial = value;
	}

	public String[] getFirstname() {
		return firstname;
	}

	public void setFirstname(String[] value) {
		firstname = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		name = value;
	}

}