package view;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VueConnexion
 */
@WebServlet("/VueConnexion")
public class VueConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VueConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html");
		PrintStream out = new PrintStream(response.getOutputStream());
		out.println("<!DOCTYPE html><html><head><title>VueConnexion</title></head>");
		out.println("<body>");
		out.println("<form action='S_controlLogin' method='post'>");
		out.println("<table><tr><td>");
		out.println("<Label>Nom d'utilisateur : </label></td>");
		out.println("<td><input type='text' name='uLogin'></td></tr>");
		out.println("<tr><td><Label>Mot de passe : </label></td>");
		out.println("<td><input type='password' name='uPassword'></td></tr>");
		out.println("<tr><td><input type='submit' name='submit' value='Connexion'></td>");
		out.println("</tr></table>");
		out.println("</form>");
		out.println("</body></html>");
	}
}
