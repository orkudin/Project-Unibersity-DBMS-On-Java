import java.io.Serializable;
import java.sql.Date;

public class Homework implements Serializable {
    private static final long serialVersionUID = 1L;
    private String subject;
    private String group;
    private String homeworkText;
    private Date deadline;

    public Homework(String subject, String group, String homeworkText, Date deadline) {
        this.subject = subject;
        this.group = group;
        this.homeworkText = homeworkText;
        this.deadline = deadline;
    }

    public Homework(String group, Date deadline) {
        this.group = group;
        this.deadline = deadline;
    }

    public Homework(){
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getHomeworkText() {
        return homeworkText;
    }

    public void setHomeworkText(String homeworkText) {
        this.homeworkText = homeworkText;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Subject: " + subject + " " + "Group: " + group + ' ' + " \nTask: " + homeworkText + '\n' + "deadline: " + deadline + "\n\n";
    }
}
