import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Grades implements Serializable {
    private static final long serialVersionUID = 1L;

    private String subjectName;

    private double grade;
    private Date date;
    private boolean attendance;

    ArrayList<Double> grades = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<Boolean> attendances = new ArrayList<>();

    public Grades(String subjectName, ArrayList<Double> grades, ArrayList<String> dates, ArrayList<Boolean> attendances) {
        this.subjectName = subjectName;
        this.grades = grades;
        this.dates = dates;
        this.attendances = attendances;
    }

    public Grades(String subjectName, double grade, Date date, boolean attendance) {
        this.subjectName = subjectName;
        this.grade = grade;
        this.date = date;
        this.attendance = attendance;
    }

    public Grades(String subjectName, Date date) {
        this.subjectName = subjectName;
        this.date = date;
    }

    public Grades(){}


    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Double> grades) {
        this.grades = grades;
    }

    public ArrayList<String> getDates() {
        return dates;
    }

    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    public ArrayList<Boolean> getAttendances() {
        return attendances;
    }

    public void setAttendances(ArrayList<Boolean> attendances) {
        this.attendances = attendances;
    }


    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        String res = "> Subject: " + subjectName + "\n";
        for(int i = 0; i < grades.size();i++){
            res += "Grade: " + grades.get(i) + " | Date: " + dates.get(i) + " | Attendance: " + attendances.get(i) + '\n';
        }
        return res + "\n";
    }

}
