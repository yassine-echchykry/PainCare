package PainCare.Servlet;

import java.io.BufferedReader;

import java.io.IOException;
import java.sql.SQLException;
import PainCare.DAO_Impl.*;
import PainCare.Beans.*;
import com.JDBC.DAO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DiagnosticResult")
public class ResultDiagnostic_Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Diagnostic_DAO_Impl diagnosticDAO;

    public void init() throws ServletException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.diagnosticDAO = daoFactory.getDiagnosticDAO();
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

        try {
            // Supposez que vous avez un utilisateur fictif avec l'ID 1
            int userId = 1;
            
            System.out.println("after");
            Diagnostic_Bean diagnosticBean = diagnosticDAO.Dernier_Test(userId);
            System.out.println("Diagnostic Bean: " + diagnosticBean); // Log the value

            String diagnosticResult = diagnosticBean.getResult();
            
            System.out.println("result: "+diagnosticResult);
            // Envoyer le résultat directement dans la réponse HTTP en tant que texte brut
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(diagnosticResult);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);
            response.getWriter().write(e.getMessage());
        }
    }

   
}
