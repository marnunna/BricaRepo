package main.java.bootstrap;

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
@WebServlet(name = "PaginaEditorModBS",urlPatterns = {"/PaginaEditorModBS"})


public class PaginaEditorModBS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Persona contatto;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginaEditorModBS() {
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
			
			
			out.append("<!DOCTYPE html>");
			out.append("<html>");
			out.append("<head>");
			out.append("	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			out.append("	<title>Brica</title>");
			out.append("	<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">");
			out.append("</head>");
			out.append("<body>");
			out.append("	<h1>Editor</h1>");
			out.append("	<div id=\"container\">");
			out.append(" 	<form id=\"fedm\" name=\"fedm\" method=\"post\" action=\"PaginaEditorModBS\"> ");
			
			out.append("		<table id=\"editor\">");
			
			out.append("			<tr><td>Nome</td>");
			out.append("				<td><input id=\"txt_n\" name=\"nome\" type=\"text\" value=\""+valNome+"\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Cognome</td>");
			out.append("				<td><input id=\"txt_c\" name=\"cognome\" type=\"text\" value=\""+valCognome+"\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Telefono</td>");
			out.append("				<td><input id=\"txt_t\" name=\"telefono\" type=\"text\" value=\""+valTelefono+"\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Indirizzo</td>");
			out.append("				<td><input id=\"txt_i\" name=\"indirizzo\" type=\"text\" value=\""+valIndirizzo+"\" /> </td> "
					 + "			</tr>");
			
			out.append("			<tr><td>Età</td>");
			out.append("				<td><input id=\"txt_e\" name=\"eta\" type=\"text\" value=\""+valEta+"\" /> </td> "
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
			
		}  catch (Exception e) {
			
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
