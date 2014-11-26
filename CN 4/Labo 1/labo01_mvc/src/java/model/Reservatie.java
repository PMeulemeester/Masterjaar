package model;


public class Reservatie
{
	private long concertId;
	private String gebruikersnaam;
	private int aantalNormaal;
	private int aantalReductie;

	public Reservatie(long concertId)
	{
		this.concertId = concertId;
	}

	public Reservatie(long concertId, String gebruikersnaam, int aantalNormaal, int aantalReductie)
	{
		this.concertId = concertId;
		this.gebruikersnaam = gebruikersnaam;
		this.aantalNormaal = aantalNormaal;
		this.aantalReductie = aantalReductie;
	}

	public int getAantalNormaal()
	{
		return aantalNormaal;
	}

	public void setAantalNormaal(int aantalNormaal)
	{
		this.aantalNormaal = aantalNormaal;
	}

	public int getAantalReductie()
	{
		return aantalReductie;
	}

	public void setAantalReductie(int aantalReductie)
	{
		this.aantalReductie = aantalReductie;
	}

	public long getConcertId()
	{
		return concertId;
	}

	public void setConcertId(long concertId)
	{
		this.concertId = concertId;
	}

	public String getGebruikersnaam()
	{
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam)
	{
		this.gebruikersnaam = gebruikersnaam;
	}

	
	@Override
	public String toString()
	{
		return "Reservatie{" + "concertId=" + concertId + ", gebruikersnaam=" + gebruikersnaam + ", aantalNormaal=" + aantalNormaal + ", aantalReductie=" + aantalReductie + '}';
	}

	
}
