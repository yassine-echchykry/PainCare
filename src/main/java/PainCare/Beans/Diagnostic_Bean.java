package PainCare.Beans;

public class Diagnostic_Bean {

    private Integer id;
    private Integer user_id;
    private String reponses;

    private static final float[][] questionCoefficients = {
            {0.2f, 0.4f, 0.4f},   // Coefficients for the first question
            {0.1f, 0.2f, 0.6f,0.1f},
            {0.2f, 0.2f, 0.6f}// Coefficients for the second question
            // Add more arrays for other questions
    };

    // Constructors, getters, setters

    public Diagnostic_Bean() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setReponses(String reponses) {
        this.reponses = reponses;
    }

    public String getReponses() {
        return this.reponses;
    }

    public String getResult() {
        float sum = 0;

        // Split the reponses string into an array of strings
        String[] answers = reponses.split(",");

        // Convert each string answer to an integer and calculate the sum of coefficients
        for (String answer : answers) {
            int answerInt = Integer.parseInt(answer.trim());
            sum += getCoefficientForAnswer(answers.length - 1, answerInt);
        }

        float average = sum / answers.length;

        if (average < 0.4) {
            return "Faible";
        } else if (average >= 0.4 && average <= 0.6) {
            return "Moyenne";
        } else {
            return "Fort";
        }
    }

    private float getCoefficientForAnswer(int question, int answer) {
        if (question >= 0 && question < questionCoefficients.length) {
            float[] coefficients = questionCoefficients[question];

            if (answer >= 0 && answer < coefficients.length) {
                return coefficients[answer];
            }
        }

        return 0; // Default or error handling
    }
}
