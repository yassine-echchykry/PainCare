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
import java.util.ArrayList;

import PainCare.DAO_Impl.*;
import PainCare.Beans.*;
import com.JDBC.DAO.*;

/**
 * Servlet implementation class Pain_Servlet
 */
@WebServlet("/Comments")
public class GetComments_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Comment_DAO_Impl commentDAO;
	private User_DAO_Impl userDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.commentDAO = daoFactory.getCommentDAO();
        this.userDAO = daoFactory.getUserDAO();
    }
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	System.out.println("Naaaaah");
    	
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

        
        int jsonStringint = Integer.parseInt(jsonString);
        System.out.printf("####################");
        
        
    	
    	try {
            // Retrieve all posts from the database#
            ArrayList<Comment_Bean> comments = commentDAO.postComments(jsonStringint);
            System.out.printf("Name: %s%n", comments);
            
           

            // Set the content type of the response
            response.setContentType("application/json");
            
            // Get the PrintWriter object to write the response
            
            PrintWriter out = response.getWriter();
            
         if (comments.isEmpty()) {
            out.print("[]");
		        } else {
		            // Write the JSON response for non-empty comments list
		            out.print("[");
		            for (int i = 0; i < comments.size(); i++) {
		                Comment_Bean comment = comments.get(i);
		                User_Bean user = userDAO.getUserById(comment.getUserID());
		                String user1 = user.getName();
		                System.out.printf("\n Name: %s%n", comment.getContenu());
		                out.print("{");
		                out.print("\"text\": \"" + comment.getContenu() + "\",");
		                out.print("\"author\": \"" + user1 + "\"");
		                out.print("}");
		
		                // Add a comma if it's not the last element
		                if (i < comments.size() - 1) {
		                    out.print(",");
		                }
		            }
		            out.print("]");
		            out.flush();
		            
        }


            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching posts");
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