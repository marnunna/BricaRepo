package brica;

import main.java.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;




public class PersistenzaClass {
	 
	
	public void daDbALista() {
				
		// Nome del driver.
		String DRIVER = "com.mysql.jdbc.Driver";
		
		// Indirizzo del database.
		String DB_URL = "jdbc:mysql://localhost:3306/rubrica";
		
		try {
			// Carico il driver.
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e1) {
			// Il driver non può essere caricato.
			System.out.println("Driver non trovato...");
			System.exit(1);
		}
		
		// Preparo il riferimento alla connessione.
		Connection connection = null;
		
		try {
			
			// Apro la connessione verso il database.
			connection = DriverManager.getConnection(DB_URL, "admRubrica", "rubricapass");
			
			// Ottengo lo Statement per interagire con il database.
			Statement statement = connection.createStatement();
			// Estrapolo dati con le query e li immagazzino in un oggetto Resultset
			ResultSet resultset = statement.executeQuery( "select * from contatti" );
			
			while (resultset.next()) {
									
				Persona nuovoContatto = new Persona ();
				
				String nome = resultset.getString(1);
				nuovoContatto.setNome(nome);
				
				String cognome = resultset.getString(2);
				nuovoContatto.setCognome(cognome);
				
				String telefono = resultset.getString(3);
				nuovoContatto.setTelefono(telefono);
				
				String indirizzo = resultset.getString(4);
				nuovoContatto.setIndirizzo(indirizzo);
				
				int eta = resultset.getInt(5);
				nuovoContatto.setEta(eta);
				
				
				PaginaPrincipale.tabella.contatti.add(nuovoContatto);
				
				
			}
		
		} catch (SQLException e) {
			// In caso di errore...
			System.out.println("errore di connessione...");
		} catch (Exception ex) {
			System.out.println("errore sconosciuto ma catturato...");
		} finally {
			if (connection != null) {
				try {
		
					connection.close();
				} 
				catch (Exception e) {
				}
			}
		}
		
		
	 }
	 
	 
	 public void daListaADb() {
		 		 
				
			// Nome del driver.
			String DRIVER = "com.mysql.jdbc.Driver";		
				
			// Indirizzo del database.
			String DB_URL = "jdbc:mysql://localhost:3306/rubrica";
				
			try {
				// Carico il driver.
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				// Il driver non può essere caricato.
				System.out.println("Driver non trovato...");
				System.exit(1);
			}
				
			// Preparo il riferimento alla connessione.
			Connection connection = null;
				
			try {
				// Apro la connessione verso il database.
				connection = DriverManager.getConnection(DB_URL, "admRubrica", "rubricapass");
				// Ottengo lo statement per interagire con il database mettendo anche stringhe con apici e apostrofi
				PreparedStatement statement;
				statement = connection.prepareStatement("truncate contatti;");
				statement.executeUpdate();
				//Vector<Persona> lista = Main.tabella.getModel().contatti;
				Vector<Persona> lista = PaginaPrincipale.tabella.contatti;
				for (int i = 0; i < lista.size(); i++) {
					
					Persona contatto = lista.get(i);

					String nome = contatto.getNome();
					String cognome = contatto.getCognome();
					String telefono = contatto.getTelefono();
					String indirizzo = contatto.getIndirizzo();
					int eta = contatto.getEta();
					
					PreparedStatement statement2 = connection.prepareStatement(
							"INSERT INTO contatti ( " +
							" nome, cognome, telefono, indirizzo, eta " +
							") VALUES ( " +
							" ?, ?, ?, ?, ? " +
							")"
							);
					// Imposto i parametri.
					statement2.setString(1, nome);
					statement2.setString(2, cognome);
					statement2.setString(3, telefono);
					statement2.setString(4, indirizzo);
					statement2.setInt(5, eta);
					// Eseguo l'aggiornamento.
					statement2.executeUpdate();
					
				}
				
				
			} catch (SQLException e) {
					// In caso di errore...
					System.out.println("errore di connessione...");
			} catch (Exception e) {
					// In caso di errore...
					System.out.println("errore sconosciuto...");
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} 
					catch (Exception e) {
					}
				}
			}
			 
		 }
	 
	 
	 
	 
	 
	 
	 
}
