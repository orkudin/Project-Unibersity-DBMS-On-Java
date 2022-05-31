import java.io.Serializable;

public class Teacher extends User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String subject;
    private String degree;

    public Teacher(Long id, String password, String name, String surname, String phoneNumber, String subject, String degree) {
        super(id, password, name, surname, phoneNumber);
        this.subject = subject;
        this.degree = degree;
    }

    public Teacher(String password, String name, String surname, String phoneNumber, String subject, String degree) {
        super(password, name, surname, phoneNumber);
        this.subject = subject;
        this.degree = degree;
    }

    public Teacher(String subject, String degree) {
        this.subject = subject;
        this.degree = degree;
    }

    public Teacher(){}

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


    @Override
    public String toString() {
        return getId() + " " + getPassword() + " " + getName() + " " + getSurname() + " " + getPhoneNumber() + " " + getSubject() + " " + getDegree();
    }

    public String toStringForModerator(){
        return "ID: " + getId() + "\nPassword: " + getPassword() + "\nName: " + getName() + " | Surname:" + getSurname() + " " + "\nPhone: " + getPhoneNumber() + "\nSubject: " + getSubject() + "\nDegree: " + getDegree();
    }

}
