package be.iii.boeken;

import java.util.List;

public class Boek {

	private String isbn;

	private String title;

	private List<Auteur> auteurs;

	public Boek() {
		isbn = null;
		title = null;
		auteurs = null;
	}

	public Boek(String isbn, String title, List<Auteur> auteurs) {
		this.isbn = isbn;
		this.title = title;
		this.auteurs = auteurs;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String value) {
		isbn = value;
	}

	public List<Auteur> getAuthor() {
		return auteurs;
	}

	public void setAuthor(List<Auteur> value) {
		auteurs = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String value) {
		title = value;
	}
}