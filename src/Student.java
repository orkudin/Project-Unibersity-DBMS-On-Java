import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String group;

    public Student(Long id, String password, String name, String surname, String phoneNumber, String group) {
        super(id, password, name, surname, phoneNumber);
        this.group = group;
    }

    public Student(String password, String name, String surname, String phoneNumber, String group) {
        super(password, name, surname, phoneNumber);
        this.group = group;
    }

    public Student(String group){
        this.group = group;
    }

    public Student(){}

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    @Override
    public String toString() {
        return getId() + " " + getPassword() + " " + getName() + " " + getSurname() + " " + getPhoneNumber() + " " + getGroup();
    }

    public String toStringForModerator(){
        return "ID: " + getId() + "\nPassword: " + getPassword() + "\nName: " + getName() + " | Surname: " + getSurname() + "\nGroup: " + getGroup() + "\nPhone: " + getPhoneNumber();
    }
}
