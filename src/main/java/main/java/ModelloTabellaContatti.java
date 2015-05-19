package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class ModelloTabellaContatti {
    
	Vector<Persona> contatti;

    public ModelloTabellaContatti()
    {
    	contatti = new Vector<Persona>();
    }

    
    // aggiungo un contatto
    public void addContatto(Persona contatto) {
    	
    	this.contatti.add(contatto);
    } 
    
    public void removeContatto(int row) {
    	
    	this.contatti.remove(row);
    }
    
    public Persona getContatto(int row) {
    	
    	if (row !=-1) {
    		return this.contatti.elementAt(row);
    	}
    	else return null;
    }
    
    
   
    
	
}
