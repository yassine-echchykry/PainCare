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
@WebServlet("/GetPosts")
public class Get_PostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Posts_DAO_Impl postDAO;
	private User_DAO_Impl userDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.postDAO = daoFactory.getPostDAO();
        this.userDAO = daoFactory.getUserDAO();
    }
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
    	response.setHeader("Access-Control-Allow-Methods", "POST");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	System.out.println("Naaaaah");
    	
    	try {
            // Retrieve all posts from the database
            ArrayList<Posts_Bean> posts = postDAO.all();
            
            System.out.printf("Name: %s%n", posts);

            // Set the content type of the response
            response.setContentType("application/json");

            // Get the PrintWriter object to write the response
            PrintWriter out = response.getWriter();

         // Write the JSON response manually (without using Gson)
            out.print("[");
            for (int i = 0; i < posts.size(); i++) {
                Posts_Bean post = posts.get(i);
                
                User_Bean user = userDAO.getUserById(post.getUserID());
                String user1=user.getName();
                out.print("{");
                out.print("\"id\": " + post.getID() + ",");
                out.print("\"userId\": " + post.getUserID() + ",");
                out.print("\"title\": \"" + post.getTitle() + "\",");
                out.print("\"auther\": \"" + user1  + "\",");
                out.print("\"description\": \"" + post.getDescription() + "\",");
                out.print("\"date\": \"" + post.getDate() + "\"");
                out.print("}");
                
                

                // Add a comma if it's not the last element
                if (i < posts.size() - 1) {
                    out.print(",");
                }
                System.out.printf("Name: %s%n", posts);
            }
            out.print("]");

            out.flush();
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching posts");
        }
    }
}