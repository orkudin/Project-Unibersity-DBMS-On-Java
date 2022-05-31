import java.io.Serializable;

public class Moderator extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    public Moderator(Long id, String password, String name, String surname, String phoneNumber) {
        super(id, password, name, surname, phoneNumber);
    }

    public Moderator() {
    }

    @Override
    public String toString() {
        return getId() + " " + getPassword() + " " + getName() + " " + getSurname() + " " + getPhoneNumber();
    }

}
