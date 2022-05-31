import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DBManager {
    public static final String url = "jdbc:postgresql://localhost/university";
    public static final String user = "postgres";
    public static final String password = "postgres";
    public static Connection connection;

    public static void connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connection to PostgreSQL server successful");

        }catch (Exception e){
            System.out.println("ERROR with connection");
            e.printStackTrace();
        }
    }

    /////--------TEACHERS-----------------////////////////

    public static ArrayList<Teacher> getAllTeachers(){
        ArrayList<Teacher> teachers = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.teacher \n"+
                    "ORDER BY teach_id ASC");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("teach_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                String subject = resultSet.getString("subject");
                String degree = resultSet.getString(("degree"));
                teachers.add(new Teacher(id, password,name,surname,phone,subject,degree));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return teachers;
    }

    public static Teacher getTeacher(Long id){
        Teacher teacher = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" + "" +
                    "SELECT * FROM public.teacher WHERE teach_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                Long idTeacher = resultSet.getLong("teach_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                String subject = resultSet.getString("subject");
                String degree = resultSet.getString(("degree"));
                teacher = new Teacher(idTeacher, password,name,surname,phone,subject,degree);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return teacher;
    }

    public static void addTeacher(Teacher teacher){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO public.teacher \n" +
                    "(password, name, surname, phone, subject, degree) \n" +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, teacher.getPassword());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getSurname());
            statement.setString(4, teacher.getPhoneNumber());
            statement.setString(5, teacher.getSubject());
            statement.setString(6, teacher.getDegree());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateTeacher(Teacher teacher){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE public.teacher SET\n" +
                    "password = ?, name = ?, surname = ?, phone = ?, subject = ?, degree = ? \n" +
                    "WHERE teach_id = ?");
            statement.setString(1, teacher.getPassword());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getSurname());
            statement.setString(4, teacher.getPhoneNumber());
            statement.setString(5, teacher.getSubject());
            statement.setString(6, teacher.getDegree());
            statement.setLong(7, teacher.getId());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteTeacher(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM public.teacher\n" +
                    "WHERE teach_id = ?");
            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /////--------STUDENTS-----------------////////////////


    public static ArrayList<Student> getAllStudents(){
        ArrayList<Student> students = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.student \n"+
                    "ORDER BY stud_id ASC");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("stud_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                String group = resultSet.getString("groupname");
                students.add(new Student(id, password,name,surname,phone,group));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    public static Student getStudent(Long id){
        Student student = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.student WHERE stud_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                Long idStudent = resultSet.getLong("stud_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                String group = resultSet.getString("groupname");
                student = new Student(idStudent, password,name,surname,phone,group);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }

    public static void addStudent(Student student){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO public.student \n" +
                    "(password, name, surname, phone, groupname) \n" +
                    "VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, student.getPassword());
            statement.setString(2, student.getName());
            statement.setString(3, student.getSurname());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getGroup());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateStudent(Student student){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE public.student SET\n" +
                    "password = ?, name = ?, surname = ?, phone = ?, groupname = ? \n" +
                    "WHERE stud_id = ?;");
            statement.setString(1, student.getPassword());
            statement.setString(2, student.getName());
            statement.setString(3, student.getSurname());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getGroup());
            statement.setLong(6, student.getId());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteStudent(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM public.student\n" +
                    "WHERE stud_id = ?");
            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /////--------MODERATORS-----------------////////////////

    public static ArrayList<Moderator> getAllModerators(){
        ArrayList<Moderator> moderators = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.moderator \n"+
                    "ORDER BY mod_id ASC");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("mod_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                moderators.add(new Moderator(id, password,name,surname,phone));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return moderators;
    }

    public static Moderator getModerator(Long id){
        Moderator moderator = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.moderator WHERE mod_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                Long idStudent = resultSet.getLong("mod_id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String phone = resultSet.getString("phone");
                moderator = new Moderator(idStudent, password,name,surname,phone);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return moderator;
    }

    public static void addModerator(Moderator moderator){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO public.moderator \n" +
                    "(password, name, surname, phone) \n" +
                    "VALUES (?, ?, ?, ?)");
            statement.setString(1, moderator.getPassword());
            statement.setString(2, moderator.getName());
            statement.setString(3, moderator.getSurname());
            statement.setString(4, moderator.getPhoneNumber());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateModerator(Moderator moderator){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE public.moderator SET\n" +
                    "password = ?, name = ?, surname = ?, phone = ? \n" +
                    "WHERE mod_id = ?;");
            statement.setString(1, moderator.getPassword());
            statement.setString(2, moderator.getName());
            statement.setString(3, moderator.getSurname());
            statement.setString(4, moderator.getPhoneNumber());
            statement.setLong(5, moderator.getId());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteModerator(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM public.moderator\n" +
                    "WHERE mod_id = ?");
            statement.setLong(1, id);

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /////--------GRADES-----------------/////////////////

    public static ArrayList<Grades> showAllGrades(){
        ArrayList<Double> grades = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Boolean> attendances = new ArrayList<>();
        ArrayList<Grades> allGrades = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.grades" +
                    "ORDER BY subject, date ASC");
            ResultSet resultSet = statement.executeQuery();

            String prevSubject = null;
            boolean flagPrevSubject = true;

            while(resultSet.next()){
                if(flagPrevSubject){//Одноразовый участок кода, в котором переменная принимает название текущего предмета
                    prevSubject = resultSet.getString("subject");
                    flagPrevSubject = false;
                }

                String subject = resultSet.getString("subject");

                if(!prevSubject.equals(subject)){
                    Grades subjectGrades = new Grades(prevSubject, grades, dates, attendances);
                    allGrades.add(subjectGrades);
//                    date.clear();
//                    grades.clear();
                    grades = new ArrayList<>();
                    dates = new ArrayList<>();
                }

                double tempGrade = resultSet.getDouble("grade");
                grades.add(tempGrade);

                String tempDate = resultSet.getString("date");
                dates.add(tempDate);

                boolean attendance = resultSet.getBoolean("attendance");
                attendances.add(attendance);

                prevSubject = subject;

                if(resultSet.isLast()){
                    Grades subjectGrades = new Grades(prevSubject, grades, dates, attendances);
                    allGrades.add(subjectGrades);
//                    date.clear();
//                    grades.clear();
                    grades = new ArrayList<>();
                    dates = new ArrayList<>();
                }

            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return allGrades;
    }

    public static ArrayList<Grades> showGrades(Long id){
        ArrayList<Double> grades = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<Boolean> attendances = new ArrayList<>();
        ArrayList<Grades> allGrades = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.grades WHERE stud_id = ?" +
                    "ORDER BY subject, date ASC");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();


            String prevSubject = null;
            boolean flagPrevSubject = true;

            while(resultSet.next()){
                if(flagPrevSubject){//Одноразовый участок кода, в котором переменная принимает название текущего предмета
                    prevSubject = resultSet.getString("subject");
                    flagPrevSubject = false;
                }

                String subject = resultSet.getString("subject");

                if(!prevSubject.equals(subject)){
                    Grades subjectGrades = new Grades(prevSubject, grades, dates, attendances);
                    allGrades.add(subjectGrades);
//                    date.clear();
//                    grades.clear();
                    grades = new ArrayList<>();
                    dates = new ArrayList<>();
                }

                double tempGrade = resultSet.getDouble("grade");
                grades.add(tempGrade);

                String tempDate = resultSet.getString("date");
                dates.add(tempDate);

                boolean attendance = resultSet.getBoolean("attendance");
                attendances.add(attendance);

                prevSubject = subject;

                if(resultSet.isLast()){
                    Grades subjectGrades = new Grades(prevSubject, grades, dates, attendances);
                    allGrades.add(subjectGrades);
//                    date.clear();
//                    grades.clear();
                    grades = new ArrayList<>();
                    dates = new ArrayList<>();
                }

            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return allGrades;
    }


    public static  void addGrade(Long id, Grades grade){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO public.grades \n" +
                    "(stud_id, subject, grade, date, attendance) \n" +
                    "VALUES (?, ?, ?, ?, ?)");
            statement.setLong(1, id);
            statement.setString(2, grade.getSubjectName());
            statement.setDouble(3, grade.getGrade());
            statement.setDate(4, grade.getDate());
            statement.setBoolean(5, grade.isAttendance());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateGrades(Long id, Date updDate, String subject, Double grade){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE public.grades SET\n" +
                    "grade = ?\n" +
                    "WHERE stud_id = ? AND date = ? AND subject = ?;");

            statement.setDouble(1, grade);
            statement.setLong(2, id);
            statement.setDate(3, updDate);
            statement.setString(4,subject);

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateAttendance(Long id, Date updDate, String subject, boolean attendance){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE public.grades SET\n" +
                    "attendance = ?\n" +
                    "WHERE stud_id = ? AND date = ? AND subject = ?;");

            statement.setBoolean(1, attendance);
            statement.setLong(2, id);
            statement.setDate(3, updDate);
            statement.setString(4,subject);

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteGrade(Long id, String subject, Date date){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM public.grades\n" +
                    "WHERE stud_id = ? AND subject = ? AND date = ?");
            statement.setLong(1, id);
            statement.setString(2, subject);
            statement.setDate(3, date);

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /////--------HOMEWORK-----------------////////////////

    public static ArrayList<Homework> getAllHomework(){
        ArrayList<Homework> homeworks = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.homeworks \n"+
                    "ORDER BY groupname, deadline ASC");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String subject = resultSet.getString("subject");
                String groupname = resultSet.getString("groupname");
                String homework = resultSet.getString("homework");
                Date deadline = resultSet.getDate("deadline");
                homeworks.add(new Homework(subject, groupname, homework, deadline));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return homeworks;
    }

    public static ArrayList<Homework> getHomeworkByGroup(String groupName){
        ArrayList<Homework> homeworks = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM public.homeworks \n"+
                    "WHERE groupname = ? \n" +
                    "ORDER BY groupname, deadline ASC");
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                String subject = resultSet.getString("subject");
                String groupname = resultSet.getString("groupname");
                String homework = resultSet.getString("homework");
                Date deadline = resultSet.getDate("deadline");
                homeworks.add(new Homework(subject, groupname, homework, deadline));
            }
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return homeworks;
    }


    public static  void addHomework(Homework homework){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO public.homeworks \n" +
                    "(subject, groupname, homework, deadline) \n" +
                    "VALUES (?, ?, ?, ?)");
            statement.setString(1, homework.getSubject());
            statement.setString(2, homework.getGroup());
            statement.setString(3, homework.getHomeworkText());
            statement.setDate(4, homework.getDeadline());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateHomework(Homework homework){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE public.homeworks SET\n" +
                    "homework = ?\n" +
                    "WHERE subject = ? AND groupname = ? AND deadline = ?;");

            statement.setString(1, homework.getHomeworkText());
            statement.setString(2, homework.getSubject());
            statement.setString(3, homework.getGroup());
            statement.setDate(4,homework.getDeadline());

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void deleteHomework(String groupname, Date deadline){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM public.homeworks\n" +
                    "WHERE groupname = ? AND deadline = ?");
            statement.setString(1, groupname);
            statement.setDate(2, deadline);

            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
