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
 * Servlet implementation class PaginaEditor
 */
@WebServlet(name = "PaginaEditor",urlPatterns = {"/PaginaEditor"})


public class PaginaEditor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaEditor() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
   	
		try ( PrintWriter out = response.getWriter()) {
			
			//out.append("<p>Lavori in corso...</p>");	
			out.append("<html><body>");
			out.append(" <form id=\"fed\" name=\"fed\" method=\"post\" action=\"PaginaEditor\"> ");
			out.append("<p>Nome <input id=\"txt_n\" name=\"nome\" type=\"text\" /> </p>");
			out.append("<p>Cognome <input id=\"txt_c\" name=\"cognome\" type=\"text\" /> </p>");
			out.append("<p>Telefono <input id=\"txt_t\" name=\"telefono\" type=\"text\" /> </p>");
			out.append("<p>Indirizzo <input id=\"txt_t\" name=\"telefono\" type=\"text\" /> </p>");
			out.append("<p>Età <input id=\"txt_t\" name=\"telefono\" type=\"text\" /> </p>");
			
			out.append(" <p> <input type=\"submit\" name=\"salva\" value=\"salva\"> "
					 + " 	 <input type=\"submit\" name=\"annulla\" value=\"annulla\"> ");
			
			out.append("</form></html></body>");
			
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
		if (request.getParameter("salva") != null) {
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String telefono = request.getParameter("telefono");
			String indirizzo = request.getParameter("indirizzo");
			String eta = request.getParameter("eta");
								
			Persona nuovoContatto= new Persona();;
			
			nuovoContatto.setNome(nome);
			nuovoContatto.setCognome(cognome);
			nuovoContatto.setTelefono(telefono);
			nuovoContatto.setIndirizzo(indirizzo);
			nuovoContatto.setEta(eta);
			
			PaginaPrincipale.tabella.addContatto(nuovoContatto);
			PaginaPrincipale.pc.daListaADb();
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaPrincipale");
			dispatcher.include(request, response);
		
	     } else if (request.getParameter("annulla") != null) {
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaPrincipale");
			dispatcher.include(request, response);
	    	 
	     } else {
	    	doRequest(request, response);
	     }
		
	}

}
