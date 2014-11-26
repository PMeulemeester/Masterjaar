package be.tiwi.bo;

public class Boek {

	private String isbn;

	private String title;

	private Auteur[] auteurs;

	public Boek() {
		isbn = null;
		title = null;
		auteurs = null;
	}

	public Boek(String isbn, String title, Auteur[] auteurs) {
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

	public Auteur[] getAuthor() {
		return auteurs;
	}

	public void setAuthor(Auteur[] value) {
		auteurs = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String value) {
		title = value;
	}
}