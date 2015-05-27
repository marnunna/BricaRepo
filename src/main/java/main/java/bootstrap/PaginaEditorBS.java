package main.java.bootstrap;

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
@WebServlet(name = "PaginaEditorBS",urlPatterns = {"/PaginaEditorBS"})


public class PaginaEditorBS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaEditorBS() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	
   	
		try ( PrintWriter out = response.getWriter()) {
			
			//out.append("<p>Lavori in corso...</p>");	
			out.append("<!DOCTYPE html>");
			out.append("<html>");
			out.append("<head>");
			out.append("	<title>Brica la Rubrica</title>");
			out.append("	<link type=\"text/css\" rel=\"stylesheet\" href=\"fogliodistile.css\" />");
			out.append("</head>");
			out.append("<body>");
			out.append("	<h1>Editor</h1>");
			out.append("	<div id=\"container\">");
			out.append(" 	<form id=\"fed\" name=\"fed\" method=\"post\" action=\"PaginaEditorBS\"> ");
			
			out.append("		<table id=\"editor\">");
			
			out.append("			<tr><td>Nome</td>");
			out.append("				<td><input id=\"txt_n\" name=\"nome\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Cognome</td>");
			out.append("				<td><input id=\"txt_c\" name=\"cognome\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Telefono</td>");
			out.append("				<td><input id=\"txt_t\" name=\"telefono\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Indirizzo</td>");
			out.append("				<td><input id=\"txt_i\" name=\"indirizzo\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Età</td>");
			out.append("				<td><input id=\"txt_e\" name=\"eta\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("		</table");
			
			out.append(" 		<div id=\"pulsantiEd\"> 	"
					+ "				<input class=\"buttonEd\" type=\"submit\" name=\"salva\" value=\"salva\"> "
					 + " 	 		<input class=\"buttonEd\" type=\"submit\" name=\"annulla\" value=\"annulla\"> "
					 + " 		</div>");
			
			out.append("	</form>"
					+  "	</div>"
					+  "</html>"
					+  "</body>");
			
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
			
			PaginaPrincipaleBS.tabella.addContatto(nuovoContatto);
			PaginaPrincipaleBS.pc.daListaADb();
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaPrincipaleBS");
			dispatcher.forward(request, response);
		
	     } else if (request.getParameter("annulla") != null) {
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PaginaPrincipaleBS");
			dispatcher.forward(request, response);
	    	 
	     } else {
	    	doRequest(request, response);
	     }
		
	}

}
