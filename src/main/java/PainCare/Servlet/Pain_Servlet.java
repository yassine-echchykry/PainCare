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

import PainCare.DAO_Impl.*;
import PainCare.Beans.*;
import com.JDBC.DAO.*;

/**
 * Servlet implementation class Pain_Servlet
 */
@WebServlet("/pain")
public class Pain_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Pain_DAO_Impl painDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.painDAO = daoFactory.getPainDAO();
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
        
        String jsonString = requestBody.toString();
        System.out.println("Passed ");
        System.out.printf("JSON Data: %s%n", jsonString);
     // Parse JSON data
        int userId = 1;
        String pain_level_str = getValueFromJsonString(jsonString, "painLevel");
        String[] locations = getArrayFromJsonString(jsonString, "painLocations");
        String[] symptoms = getArrayFromJsonString(jsonString, "symptoms");
        String[] worse_pain = getArrayFromJsonString(jsonString, "worsePain");
        String[] feelings = getArrayFromJsonString(jsonString, "feelings");
        
        int pain_level = Integer.parseInt(pain_level_str);

        // Perform any necessary backend logic with the received data
        
        
        
        System.out.printf("Pain Level: " , pain_level);
        System.out.printf("Locations: ", locations);
        System.out.println("Symptoms: " + String.join(", ", symptoms));
        System.out.println("Worse Pain: " + String.join(", ", worse_pain));
        System.out.println("Feelings: " + String.join(", ", feelings));
        
        painDAO.insertPainData(userId, pain_level, locations,symptoms,worse_pain,feelings);

        // Send a response back to the client
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print("{\"status\": \"success\"}");
        out.flush();
    }

    private String getValueFromJsonString(String jsonString, String key) {
        String formattedKey = "\"" + key + "\":";
        int startIndex = jsonString.indexOf(formattedKey);
        if (startIndex == -1) {
            return null; // Key not found in JSON string
        }

        startIndex += formattedKey.length();
        char startChar = jsonString.charAt(startIndex);

        int endIndex;
        if (startChar == '"') {
            // Value is a string (enclosed in double quotes)
            startIndex++; // Move past the opening double quote
            endIndex = jsonString.indexOf("\"", startIndex);
        } else {
            // Value is not a string and may contain digits or other characters
            endIndex = jsonString.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = jsonString.indexOf("}", startIndex);
            }
        }

        if (endIndex == -1) {
            return null; // Couldn't find the end of the value
        }

        return jsonString.substring(startIndex, endIndex).replace("\"", "");
    }

    private String[] getArrayFromJsonString(String jsonString, String key) {
        String formattedKey = "\"" + key + "\":";
        int startIndex = jsonString.indexOf(formattedKey);
        if (startIndex == -1) {
            return new String[0]; // Key not found in JSON string
        }

        startIndex += formattedKey.length();
        int arrayStartIndex = jsonString.indexOf("[", startIndex);
        int arrayEndIndex = jsonString.indexOf("]", arrayStartIndex);

        if (arrayStartIndex == -1 || arrayEndIndex == -1) {
            return new String[0]; // Couldn't find the start or end of the array
        }

        String arrayContent = jsonString.substring(arrayStartIndex + 1, arrayEndIndex);
        String[] arrayValues = arrayContent.split(",");
        for (int i = 0; i < arrayValues.length; i++) {
            arrayValues[i] = arrayValues[i].trim().replace("\"", "");
        }

        return arrayValues;
    }
}