package PainCare.Servlet;

import jakarta.servlet.ServletException;


import PainCare.DAO_Impl.*;
import PainCare.Beans.*;
import com.JDBC.DAO.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import PainCare.DAO_Impl.*;
import PainCare.Beans.*;
import com.JDBC.DAO.*;


@WebServlet("/GetLatestPainLevels")
public class Get_LatestPainLevels_Servlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private Pain_DAO_Impl painDAO;

	    public void init() throws ServletException {
	        DAOFactory daoFactory = DAOFactory.getInstance();
	        this.painDAO = new Pain_DAO_Impl(daoFactory);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	       
	        // Récupérer l'ID de l'utilisateur à partir des paramètres de requête ou de toute autre source
	        
	        BufferedReader reader = request.getReader();
	        StringBuilder stringBuilder = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	        }
	        //String userId = stringBuilder.toString();
	        int userId=1 ;

	        // Appeler la méthode DAO pour obtenir les derniers niveaux de douleur
	        List<Integer> latestPainLevels = painDAO.getLatestPainLevels(userId);

	        // Construire manuellement une représentation JSON de la liste
	        StringBuilder jsonBuilder = new StringBuilder();
	        jsonBuilder.append("[");
	        for (int i = 0; i < latestPainLevels.size(); i++) {
	            jsonBuilder.append(latestPainLevels.get(i));
	            if (i < latestPainLevels.size() - 1) {
	                jsonBuilder.append(",");
	            }
	        }
	        jsonBuilder.append("]");

	        // Envoyer la réponse JSON au client
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonBuilder.toString());
	        
	        System.out.printf("JSON Data: %s%n", jsonBuilder);
	    }
	    }
	

         
	

