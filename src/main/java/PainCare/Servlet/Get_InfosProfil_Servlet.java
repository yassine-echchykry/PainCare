package PainCare.Servlet;

import java.io.BufferedReader;

import java.io.IOException;
import java.sql.SQLException;
import PainCare.DAO_Impl.*;
import com.JDBC.DAO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetUsername")
public class Get_InfosProfil_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Session_DAO_Impl sessionDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.sessionDAO = daoFactory.getSessionDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	BufferedReader reader = request.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String token = stringBuilder.toString();
        System.out.printf("token",token);

        System.out.println("before");
        String username= sessionDAO.getUsernameByToken(token);
		
		

		
		
		System.out.println(username);
		// Envoyer le résultat directement dans la réponse HTTP en tant que texte brut
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().write(username);
		System.out.println("after");
    }

   
}
