import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private String groupName;
    private Long id;

    private Moderator moderator;
    private ArrayList<Moderator>moderators;

    private Teacher teacher;
    private ArrayList<Teacher>teachers;

    private Student student;
    private ArrayList<Student> students;

    private Homework homework;
    private ArrayList<Homework>homeworks;

    private Grades grade;
    private ArrayList<Grades>grades;

    public PackageData() {
    }

    public PackageData(String operationType) {
        this.operationType = operationType;
    }

    public PackageData(String operationType, Long id) {
        this.operationType = operationType;
        this.id = id;
    }

    public PackageData(String operationType, String groupName) {
        this.operationType = operationType;
        this.groupName = groupName;
    }

    public PackageData(String operationType, Moderator moderator) {
        this.operationType = operationType;
        this.moderator = moderator;
    }

    public PackageData(String operationType, Long id, Grades grade) {
        this.operationType = operationType;
        this.id = id;
        this.grade = grade;
    }

    public PackageData(Moderator moderator) {
        this.moderator = moderator;
    }

    public PackageData(String operationType, Teacher teacher) {
        this.operationType = operationType;
        this.teacher = teacher;
    }

    public PackageData(Teacher teacher) {
        this.teacher = teacher;
    }

    public PackageData(String operationType, Student student) {
        this.operationType = operationType;
        this.student = student;
    }

    public PackageData(Student student) {
        this.student = student;
    }

    public PackageData(String operationType, Homework homework) {
        this.operationType = operationType;
        this.homework = homework;
    }

    public PackageData(Homework homework) {
        this.homework = homework;
    }

    public PackageData(String operationType, Grades grade) {
        this.operationType = operationType;
        this.grade = grade;
    }

    public PackageData(Grades grade) {
        this.grade = grade;
    }

    public ArrayList<Moderator> getModerators() {
        return moderators;
    }
    public void setModerators(ArrayList<Moderator> moderators) {
        this.moderators = moderators;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }
    public void setHomeworks(ArrayList<Homework> homeworks) {
        this.homeworks = homeworks;
    }

    public ArrayList<Grades> getGrades() {
        return grades;
    }
    public void setGrades(ArrayList<Grades> grades) {
        this.grades = grades;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getOperationType() {
        return operationType;
    }
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Moderator getModerator() {
        return moderator;
    }
    public void setModerator(Moderator moderator) {
        this.moderator = moderator;
    }

    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

    public Homework getHomework() {
        return homework;
    }
    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Grades getGrade() {
        return grade;
    }
    public void setGrade(Grades grade) {
        this.grade = grade;
    }

}
