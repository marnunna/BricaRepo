package main.java;

import brica.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class PaginaPrincipale
 */
@WebServlet(name = "PaginaPrincipale",urlPatterns = {"/PaginaPrincipale"})

public class PaginaPrincipale extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static ModelloTabellaContatti tabella;
	static PersistenzaClass pc;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaPrincipale() {
        super();
        // TODO Auto-generated constructor stub
    	
    }
    
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	
    	tabella = new ModelloTabellaContatti();
    	pc = new PersistenzaClass();
    	pc.daDbALista();
    	Vector<Persona> lista = tabella.contatti;
    			
		try (PrintWriter out = response.getWriter()) {
	        
			out.append("<!DOCTYPE html>");
			out.append("<html>");
			out.append("<head>");
			out.append("	<title>Brica</title>");
			out.append("	<link type=\"text/css\" rel=\"stylesheet\" href=\"fogliodistile.css\" />");
			out.append("</head>");
			out.append("<body>");
			out.append("	<h1>Brica la Rubrica condivisa</h1>");
			out.append("	<div id=\"container\">");
			out.append("	<form id=\"f\" name=\"f\" method=\"post\" action=\"PaginaPrincipale\"> ");
			out.append("		<table id=\"contatti\">");
			out.append(" <tr> <th>Selezione</th> <th>Nome</th> <th>Cognome</th> <th>Telefono</th> </tr>");
			
			for (int i=0 ; i < lista.size(); i++) {
				
				Persona contatto = lista.get(i);
				
				String nome = contatto.getNome();
				String cognome = contatto.getCognome();
				String telefono = contatto.getTelefono();
				
	       		out.append("			<tr>");
	       		out.append("			<td> <input class=\"sel\" id=\"rig"+i+"\" name=\"sel\" type=\"radio\" value=\""+i+"\" /> </td>");
	       		out.append("			<td>&nbsp" + nome + "&nbsp</td>");
	       		out.append("			<td>&nbsp" + cognome + "&nbsp</td>");
	       		out.append("			<td>&nbsp" + telefono + "&nbsp</td>");
	       		out.append("			</tr>");
	       		
	       		
			}  
			
			out.append("			</table>");
			
			
			out.append(" <div id=\"pulsanti\"> "
					 + "	<input class=\"button\" type=\"submit\" name=\"nuovo\" value=\"nuovo\" > "
					 + "	<input class=\"button\" type=\"submit\" name=\"modifica\" value=\"modifica\"> "
					 + "	<input class=\"button\" type=\"submit\" name=\"elimina\" value=\"elimina\"> "
					 + " </div> ");
			
			
			out.append("	</form>"
					+ "</div>"
					+ "</body>"
					+ "</html>");
			
			
			
		} catch (Exception e) {
			
		}
    	
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doRequest(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doRequestGet(request, response);
		 if (request.getParameter("nuovo") != null) {
			 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaEditor");
			 dispatcher.forward(request, response);
				   
	     } else if (request.getParameter("modifica") != null) {
	    	 
	    	 try {
	    	 int rigaSel= Integer.parseInt(request.getParameter("sel"));
	    	 Persona contatto= tabella.getContatto(rigaSel);
			 request.setAttribute("contatto", contatto);
	    	 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaEditorMod");
			 dispatcher.forward(request, response);
	    	 } catch (Exception e) {
	    		 doRequest(request, response);
	    	 }
			 
	     } else if (request.getParameter("elimina") != null) {
	    	 
	    	 try {
	    	 int rigaSel= Integer.parseInt(request.getParameter("sel"));
		     tabella.removeContatto(rigaSel);
		     pc.daListaADb();
		     doRequest(request, response);
	    	 } catch (Exception e) {
	    		 doRequest(request, response);
	    	 }

	     }  else {
	    	 doRequest(request, response);
	     }
		 
		 
	}

}
