package Revature.Project0.Model;

public class Hobby {
    //Fields should mirror DB columns as closely as possible
    private int fk_user_int;
    private String hobby;

    public Hobby(int fk_user_int, String activity) {
        this.fk_user_int = fk_user_int;
        this.hobby = activity;
    }

    public Hobby(){
        this.fk_user_int = 3;
        this.hobby = "Operation could not be completed. try again. This phony Hobby to inform you of your epic fail";
    }

    public int getFk_user_int() {
        return fk_user_int;
    }

    public void setFk_user_int(int fk_user_int) {
        this.fk_user_int = fk_user_int;
    }

    public String getHobby() {
        return this.hobby;
    }

    public void setHobby(String activity) {
        this.hobby = activity;
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "fk_user_int=" + fk_user_int +
                ", activity='" + hobby + '\'' +
                '}';
    }
}
