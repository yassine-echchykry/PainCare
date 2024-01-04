package PainCare.Beans;


public class Diagnostic_Bean {

    private Long id;
    private Long user_id;
    private int[] reponses;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int[] getReponses() {
        return reponses;
    }

    public void setReponses(int[] reponses) {
        this.reponses = reponses;
    }
}

