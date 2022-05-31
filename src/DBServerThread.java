import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class DBServerThread extends Thread{
    private Socket socket;

    public DBServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try{
            DBManager manager = new DBManager();
            manager.connect();

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            PackageData packageData = null;
            while ((packageData=(PackageData) inputStream.readObject()) != null){
                if(packageData.getOperationType().equals("ADD STUDENT")){
                    Student studentFromClient = packageData.getStudent();
                    manager.addStudent(studentFromClient);
                }else if(packageData.getOperationType().equals("LIST ONE STUDENT")){
                    Student studentInfoForClient = manager.getStudent(packageData.getId());
                    PackageData toClient = new PackageData(studentInfoForClient);
                    outputStream.writeObject(toClient);
                }else if(packageData.getOperationType().equals("LIST ALL STUDENTS")){
                    ArrayList<Student> studentsInfoForClient = manager.getAllStudents();
                    PackageData toClient = new PackageData();
                    toClient.setStudents(studentsInfoForClient);
                    outputStream.writeObject(toClient);
                }else if(packageData.getOperationType().equals("UPDATE STUDENT")){
                    Student updateStudentFromClient = packageData.getStudent();
                    manager.updateStudent(updateStudentFromClient);
                }else if(packageData.getOperationType().equals("DELETE STUDENT")){
                    manager.deleteStudent(packageData.getId());
                }

                //////////////////-----TEACHER----------/////////////////////////

                else if(packageData.getOperationType().equals("ADD TEACHER")){
                    Teacher teacherFromClient = packageData.getTeacher();
                    manager.addTeacher(teacherFromClient);
                }else if(packageData.getOperationType().equals("LIST ONE TEACHER")){
                    Teacher teacherInfoForClient = manager.getTeacher(packageData.getId());
                    PackageData toClient = new PackageData(teacherInfoForClient);
                    outputStream.writeObject(toClient);
                }else if(packageData.getOperationType().equals("LIST ALL TEACHERS")){
                    ArrayList<Teacher> teachersInfoForClient = manager.getAllTeachers();
                    PackageData toClient = new PackageData();
                    toClient.setTeachers(teachersInfoForClient);
                    outputStream.writeObject(toClient);
                }else if(packageData.getOperationType().equals("UPDATE TEACHER")){
                    Teacher updateTeacherFromClient = packageData.getTeacher();
                    manager.updateTeacher(updateTeacherFromClient);
                }else if(packageData.getOperationType().equals("DELETE TEACHER")){
                    manager.deleteTeacher(packageData.getId());
                }

                //////////////////-----GRADES----------/////////////////////////

                else if(packageData.getOperationType().equals("LIST GRADES")){
                    ArrayList<Grades> gradesInfoForClient = manager.showGrades(packageData.getId());
                    PackageData toClient = new PackageData();
                    toClient.setGrades(gradesInfoForClient);
                    outputStream.writeObject(toClient);
                }else if(packageData.getOperationType().equals("ADD GRADES")){
                    Grades gradesFromClient = packageData.getGrade();
                    manager.addGrade(packageData.getId(), gradesFromClient);
                }else if(packageData.getOperationType().equals("UPDATE GRADES")){
                    Grades updateGradeFromClient = packageData.getGrade();
                    manager.updateGrades(packageData.getId(), updateGradeFromClient.getDate(), updateGradeFromClient.getSubjectName(), updateGradeFromClient.getGrade());
                }else if(packageData.getOperationType().equals("DELETE GRADES")){
                    manager.deleteGrade(packageData.getId(), packageData.getGrade().getSubjectName(), packageData.getGrade().getDate());
                }

                /////////---------HOMEWORK---------------//////////////////////////
                else if(packageData.getOperationType().equals("LIST HOMEWORK")){
                    ArrayList<Homework> homeworkInfoForClient = manager.getHomeworkByGroup(packageData.getGroupName());
                    PackageData toClient = new PackageData();
                    toClient.setHomeworks(homeworkInfoForClient);
                    outputStream.writeObject(toClient);
                }else if(packageData.getOperationType().equals("ADD HOMEWORK")){
                    Homework homeworksFromClient = packageData.getHomework();
                    manager.addHomework(homeworksFromClient);
                }else if(packageData.getOperationType().equals("UPDATE HOMEWORK")){
                    Homework updateHomeworkFromClient = packageData.getHomework();
                    manager.updateHomework(updateHomeworkFromClient);
                }else if(packageData.getOperationType().equals("DELETE HOMEWORK")){
                    manager.deleteHomework(packageData.getHomework().getGroup(),packageData.getHomework().getDeadline());
                }
            }
            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
