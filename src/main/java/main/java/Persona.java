package main.java;



public class Persona {

	private String nome;
	private String cognome;
	private String telefono;
	private String indirizzo;
	private int eta;
	
	public Persona() {
		
	}
	
	public Persona(String nome, String cognome) {		
		this.nome = nome;
		this.cognome = cognome;
	}
	
	
	public Persona(String nome, String cognome, String telefono,
			String indirizzo, int eta) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.indirizzo= indirizzo;
		this.eta= eta;
	}

	
	public void setNome (String nome) {
		this.nome=nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	
	public void setCognome (String cognome) {
		this.cognome=cognome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	
	public void setTelefono (String telefono) {
		this.telefono=telefono;
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	
	public void setIndirizzo (String indirizzo) {
		this.indirizzo=indirizzo;
	}
	
	public String getIndirizzo() {
		return this.indirizzo;
	}
	
	
	public void setEta(int eta) {
		this.eta=eta;
	}
	
	public void setEta (String etaString) {
		
		try {
			this.eta= Integer.parseInt(etaString);
		} catch (Exception e) {
			this. eta = 0;
		}
		
	}
	
	public int getEta() {
		
		return this.eta;
	}
	
	
	
}
