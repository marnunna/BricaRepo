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
			out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.append("	<title>Brica</title>");
			out.append("	<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">");
			out.append("</head>");
			out.append("<body>");
			out.append("<body>");
			out.append("	<h1 class=\"text-center\">Editor</h1>");
			out.append("	<div class=\"container\">");
			out.append("	<div class=\"row\">");
			out.append("	<div class=\"col-md-4\"></div>");
			out.append("	<div class=\"col-md-6\">");
			out.append(" 	<form id=\"fed\" name=\"fed\" method=\"post\" action=\"PaginaEditorBS\"> ");
			
			out.append("		<table id=\"table\">");
			out.append("		<tbody>");
			
			out.append("			<tr><td>Nome</td>");
			out.append("				<td><input id=\"txt_n\" class=\"form-control\" name=\"nome\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Cognome</td>");
			out.append("				<td><input id=\"txt_c\" class=\"form-control\" name=\"cognome\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Telefono</td>");
			out.append("				<td><input id=\"txt_t\" class=\"form-control\" name=\"telefono\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Indirizzo</td>");
			out.append("				<td><input id=\"txt_i\" class=\"form-control\" name=\"indirizzo\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Età</td>");
			out.append("				<td><input id=\"txt_e\" class=\"form-control\" name=\"eta\" type=\"text\" /> </td> "
					 + "			</tr>");
			
			out.append("		</tbody>");
			out.append("			</table>");
			
			out.append("	<div class=\"row\">");
			out.append("	<div class=\"col-md-2\"></div>");
			out.append("	<div class=\"col-md-6\">");
			out.append("	<input class=\"btn btn-success\" type=\"submit\" name=\"salva\" value=\"salva\"> "
					 + " 	<input class=\"btn btn-success\" type=\"submit\" name=\"annulla\" value=\"annulla\"> ");
			out.append("	</div>");
			out.append("	</div class=\"col-md-3\"></div>");
			out.append("	</div>");
			
			out.append("	</form>"
					+ "</div>"
					+ "<div class=\"col-md-3\"></div>"
					+ "</div>"
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
