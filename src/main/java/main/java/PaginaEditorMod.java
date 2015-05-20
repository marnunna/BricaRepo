package main.java;

import brica.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaginaEditorMod
 */
@WebServlet(name = "PaginaEditorMod",urlPatterns = {"/PaginaEditorMod/*"})


public class PaginaEditorMod extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persona contatto;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaEditorMod() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
   	
		try ( PrintWriter out = response.getWriter()) {
			
			
			contatto = (Persona) request.getAttribute("contatto");
			String valNome= contatto.getNome();
			String valCognome= contatto.getCognome();
			String valTelefono = ""; if (contatto.getTelefono() != null) { valTelefono= contatto.getTelefono(); }
			String valIndirizzo = ""; if (contatto.getIndirizzo() != null) { valIndirizzo= contatto.getIndirizzo(); }
			int valEta= contatto.getEta();
			
			out.append("<html><body>");
			out.append(" <form id=\"fed\" name=\"fed\" method=\"post\" action=\"PaginaEditorMod\"> ");
			out.append("<p>Nome <input id=\"txt_n\" name=\"nome\" type=\"text\" value=\""+valNome+"\" /> </p>");
			out.append("<p>Cognome <input id=\"txt_c\" name=\"cognome\" type=\"text\" value=\""+valCognome+"\" /> </p>");
			out.append("<p>Telefono <input id=\"txt_t\" name=\"telefono\" type=\"text\" value=\""+valTelefono+"\" /> </p>");
			out.append("<p>Indirizzo <input id=\"txt_i\" name=\"indirizzo\" type=\"text\" value=\""+valIndirizzo+"\" /> </p>");
			out.append("<p>Età <input id=\"txt_e\" name=\"eta\" type=\"text\" value=\""+valEta+"\" /> </p>");
			
			
			out.append(" <p> <input type=\"submit\" name=\"salva\" value=\"salva\"> "
					 + " 	 <input type=\"submit\" name=\"annulla\" value=\"annulla\"> ");
			
			out.append("</form></html></body>");
			
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
			
			contatto.setNome(nome);
			contatto.setCognome(cognome);
			contatto.setTelefono(telefono);
			contatto.setIndirizzo(indirizzo);
			contatto.setEta(eta);
			
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
