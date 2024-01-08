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
@WebServlet("/AddComment")
public class Create_Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Comment_DAO_Impl commentDAO;
	private User_DAO_Impl userDAO;
	private Session_DAO_Impl sessionDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public void init() throws ServletException {
		 DAOFactory daoFactory = DAOFactory.getInstance();
	        this.commentDAO = daoFactory.getCommentDAO();
	        this.userDAO = daoFactory.getUserDAO();
	        this.sessionDAO = daoFactory.getSessionDAO();
	        }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	
    	System.out.println("daba ");
	
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

         String postId_srt = getValueFromJsonString(jsonString, "postId");
         
         System.out.printf("userid", postId_srt);
         String comment = getValueFromJsonString(jsonString, "commentText");
         String token = getValueFromJsonString(jsonString, "authToken");
         System.out.println("Name11: " + postId_srt);
         
         int postId = Integer.parseInt(postId_srt);
         
         String username= sessionDAO.getUsernameByToken(token);
         System.out.println("Name22: " + username);
         int userId=1;
         System.out.println("userid: " + userId);
         try {
             commentDAO.create(postId, userId, comment);
             // Perform any necessary backend logic with the received data

             // Send a response back to the client
             response.setContentType("application/json");
             response.setCharacterEncoding("UTF-8");
             PrintWriter out = response.getWriter();
             out.print("{");
             out.print("\"author\": \"" + username + "\"");
             out.print("}");
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


