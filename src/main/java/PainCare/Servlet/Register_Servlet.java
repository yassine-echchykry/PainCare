package PainCare.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.JDBC.DAO.*;
import PainCare.Beans.*;
import PainCare.DAO.*;
import PainCare.DAO_Impl.*;

@WebServlet("/register")
public class Register_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User_DAO_Impl userDAO;
	
	public void init() throws ServletException {
	    System.out.println("Init method called!");  // Ajoutez cette ligne pour le débogage
	    DAOFactory daoFactory = DAOFactory.getInstance();
	    this.userDAO = daoFactory.getUserDAO();
	}

	
    
    
   


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	 // Read the JSON data from the request body
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }
     // Parse JSON data
        String jsonString = requestBody.toString();
        System.out.println("pqssed ");
        System.out.printf("Name: %s%n", jsonString);

        String name = getValueFromJsonString(jsonString, "name");
        String email = getValueFromJsonString(jsonString, "email");
        String password = getValueFromJsonString(jsonString, "password");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        
        try {
            userDAO.register(name, email, password);
            // Perform any necessary backend logic with the received data

            // Send a response back to the client
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();
            out.print("{\"status\": \"success\"}");
            out.flush();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately (log it or return an error response)
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing the registration");
        }
    
        
    }
	private String getValueFromJsonString(String jsonString, String key) {
        int startIndex = jsonString.indexOf("\"" + key + "\":") + key.length() + 3; // 3 accounts for ": and optional space
        int endIndex = jsonString.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonString.indexOf("}", startIndex);
        }

        return jsonString.substring(startIndex, endIndex).replace("\"", "");
    }

}
