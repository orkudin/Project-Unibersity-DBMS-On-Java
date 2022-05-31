import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static MainFrame frame;

    public static ArrayList<Student> students = new ArrayList<>();
    public static ArrayList<Teacher> teachers = new ArrayList<>();
    public static ArrayList<Moderator> moderators = new ArrayList<>();
    public static ArrayList<User> allUsers = new ArrayList<>();
    public static ArrayList<Homework> homework = new ArrayList<>();
    public static String userID = new String();

    public static void connect(PackageData pd){
        try{
            Socket socket = new Socket("127.0.0.1",8800);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());


            //////////////////-----STUDENT----------/////////////////////////

            if(pd.getOperationType().equals("ADD STUDENT")){
                outputStream.writeObject(pd);///ADD, Student
            }else if(pd.getOperationType().equals("LIST ONE STUDENT")){
                outputStream.writeObject(pd);
                PackageData infoFromServer = (PackageData) inputStream.readObject();
                Student studentFromServer = infoFromServer.getStudent();

                ModStudEditList.findStudent.append(String.valueOf(studentFromServer.toStringForModerator()));
            }else if(pd.getOperationType().equals("LIST ALL STUDENTS")){
                outputStream.writeObject(pd);
                PackageData infoFromServer = (PackageData) inputStream.readObject();
                ArrayList<Student> arrayStudentsFromServer = infoFromServer.getStudents();
                String s = "";

                for(int i = 0;i<arrayStudentsFromServer.size();i++){
                    s+= arrayStudentsFromServer.get(i).toStringForModerator() + "\n";
                }

                ModStudEditList.findStudent.append(s);
            }else if(pd.getOperationType().equals("UPDATE STUDENT")){
                outputStream.writeObject(pd);//UPDATE, Student

                ModStudEditChange.findStudentButton.doClick();
            }else if(pd.getOperationType().equals("DELETE STUDENT")){
                outputStream.writeObject(pd);
            }

            //////////////////-----TEACHER----------/////////////////////////

            else if(pd.getOperationType().equals("ADD TEACHER")){
                outputStream.writeObject(pd);///ADD, Teacher
            }else if(pd.getOperationType().equals("LIST ONE TEACHER")){
                outputStream.writeObject(pd);
                PackageData infoFromServer = (PackageData) inputStream.readObject();
                Teacher teacherFromServer = infoFromServer.getTeacher();

                ModTeachEditList.findTeacher.append(String.valueOf(teacherFromServer.toStringForModerator()));
            }else if(pd.getOperationType().equals("LIST ALL TEACHERS")){
                outputStream.writeObject(pd);
                PackageData infoFromServer = (PackageData) inputStream.readObject();
                ArrayList<Teacher> arrayTeachersFromServer = infoFromServer.getTeachers();
                String s = "";

                for(int i = 0;i<arrayTeachersFromServer.size();i++){
                    s+= arrayTeachersFromServer.get(i).toStringForModerator() + "\n";
                }

                ModTeachEditList.findTeacher.append(s);
            }else if(pd.getOperationType().equals("UPDATE TEACHER")){
                outputStream.writeObject(pd);//UPDATE, Teacher

                ModTeachEditChange.findTeacherButton.doClick();
            }else if(pd.getOperationType().equals("DELETE TEACHER")){
                outputStream.writeObject(pd);
            }

            //////////////////-----GRADES----------/////////////////////////

            else if(pd.getOperationType().equals("LIST GRADES")){
                outputStream.writeObject(pd);
                PackageData infoFromServer = (PackageData) inputStream.readObject();
                ArrayList<Grades> arrayGradesFromServer = infoFromServer.getGrades();
                String privateSubject = "";

                boolean checkGrades = false;
                for(int i = 0;i<arrayGradesFromServer.size();i++){
                    if(arrayGradesFromServer.get(i).getSubjectName().equals(Main.teachers.get(MainMenu.TeachID).getSubject())){
                        privateSubject += arrayGradesFromServer.get(i) + "\n";
                        checkGrades = true;
                    }
                }

                int StudID = Main.getStudentIndex(Main.students, pd.getId());//Поиск индекса студента на основе айди
                if(StudID == -1){
                    TeacherMenuFindGrade.findStudent.setText("There is no student with that ID");
                }//Вывод, если студент не найден
                else if(!checkGrades){
                    TeacherMenuFindGrade.findStudent.setText("Student hasn't grades");
                }else{
                    TeacherMenuFindGrade.findStudent.append(privateSubject);
                }
            }else if(pd.getOperationType().equals("ADD GRADES")){
                outputStream.writeObject(pd);///ADD, Grades
            }else if(pd.getOperationType().equals("UPDATE GRADES")){
                outputStream.writeObject(pd);//UPDATE, Grades
                TeacherMenuChangeGrades.conditionTextArea.setText("Successful!");
                TeacherMenuChangeGrades.showStudentGrades.doClick();
            }else if(pd.getOperationType().equals("DELETE GRADES")){
                outputStream.writeObject(pd);
            }
            ////////////////////------------HOMEWORKS--------------------////////////////////////////
            else if(pd.getOperationType().equals("LIST HOMEWORK")){
                outputStream.writeObject(pd);
                PackageData infoFromServer = (PackageData) inputStream.readObject();
                ArrayList<Homework> arrayHomeworksFromServer = infoFromServer.getHomeworks();
                String subjectName = Main.teachers.get(MainMenu.TeachID).getSubject();

                boolean checkHomework = true;
                String privateHomework = "";
                for(int i = 0;i < arrayHomeworksFromServer.size();i++){
                    if(arrayHomeworksFromServer.get(i).getGroup().equals(pd.getGroupName()) && arrayHomeworksFromServer.get(i).getSubject().equals(subjectName)){
                        privateHomework += arrayHomeworksFromServer.get(i);
                        checkHomework = false;
                    }
                }
                if(checkHomework){
                    TeacherMenuFindHomework.findHomework.setText("There is no homework");
                }else{
                    TeacherMenuFindHomework.findHomework.setText("HOMEWORK: \n" + privateHomework);
                }
            }else if(pd.getOperationType().equals("ADD HOMEWORK")){
                outputStream.writeObject(pd);///ADD, Homework
            } else if(pd.getOperationType().equals("UPDATE HOMEWORK")){
                outputStream.writeObject(pd);//UPDATE, Homework
                TeacherMenuSetHomework.conditionTextArea.setText("Successful!");
                TeacherMenuSetHomework.hwShow.doClick();
            }else if(pd.getOperationType().equals("DELETE HOMEWORK")){
                outputStream.writeObject(pd);
            }

            inputStream.close();
            outputStream.close();
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBManager.connect();
        students = DBManager.getAllStudents();
        teachers = DBManager.getAllTeachers();
        moderators = DBManager.getAllModerators();
        homework = DBManager.getAllHomework();

        allUsers.addAll(students);
        allUsers.addAll(teachers);
        allUsers.addAll(moderators);

        MainFrame frame = new MainFrame();
        frame.setVisible(true);

        Thread threadGrades = new Thread(MainFrame.studentMenuShowGrades);
        try{
            while(true){
                if(MainMenu.checkStudentMode){
                    threadGrades.start();
                    break;
                }
                Thread.sleep(3000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Thread threadHW = new Thread(MainFrame.studentMenuShowHomework);
        try{
            while(true){
                if(MainMenu.checkStudentMode){
                    threadHW.start();
                    break;
                }
                threadHW.sleep(3000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static int getSubjectIndex(ArrayList<Grades> tempStudentGrades, String subjectForChangeGrade){//Поиск студенческого предмета по названию с переводом в индекс массива
        //Вводим Math, он ищет данный предмет в аррейлисте Grades и выдаёт индекс
        int subjectIndex = - 1;
        for(int subjectCounter = 0;subjectCounter < tempStudentGrades.size();subjectCounter++){
            if(tempStudentGrades.get(subjectCounter).getSubjectName().equals(subjectForChangeGrade)){
                subjectIndex = subjectCounter;
            }
        }
        if(subjectIndex == -1){
            System.out.println("Incorrect subject name, please, try again");
            return -1;
        }
        return subjectIndex;
    }

    public static int getDataIndex(ArrayList<Grades> tempStudentGrades, int subjectIndex, String dateForChangeGrade){//Поиск даты оценки по названию с переводом в индекс массива
        //Вводим 22.05.22, он ищет дату в аррейлисте Grades и выдаёт индекс
        int dateIndex = -1;

        for(int i = 0;i < tempStudentGrades.get(subjectIndex).getDates().size(); i++){
            if(tempStudentGrades.get(subjectIndex).getDates().get(i).equals(dateForChangeGrade)){
                dateIndex = i;
            }
        }
        if(dateIndex == -1){
            System.out.println("Incorrect date, please, try again");
            return -1;
        }
        return dateIndex;
    }

    public static int getStudentIndex(ArrayList<Student> students, Long studentID){//Поиск студента по студенческому айди с переводом в индекс массива
        //Вводим 29220, он ищет данный айди в аррейлисте студентов и выдаёт индекс
        int StudID = -1;

        for(int k = 0; k < students.size(); k++){//Ищет айди студента
            if(students.get(k).getId().equals(studentID)){
                StudID = k;
            }
        }
        if(StudID == -1){
            System.out.println("Incorrect student ID, please, try again");
            return -1;
        }
        return StudID;
    }

    public static int getModeratorIndex(ArrayList<Moderator> moderators, Long moderatorID){//Поиск модератора по айди с переводом в индекс массива
        //Вводим 29220, он ищет данный айди в аррейлисте модераторов и выдаёт индекс
        int ModID = -1;

        for(int k = 0; k < moderators.size(); k++){//Ищет айди модератора
            if(moderators.get(k).getId().equals(ModID)){
                ModID = k;
            }
        }

        if(ModID == -1){
            System.out.println("Incorrect moderator ID, please, try again");
            return -1;
        }
        return ModID;
    }

    public static int getTeacherIndex(ArrayList<Teacher> teachers, Long teacherID){
        //Вводим 29220, он ищет данный айди в аррейлисте учителей и выдаёт индекс
        int TeachID = -1;

        for(int k = 0; k < teachers.size(); k++){//Ищет айди студента
            if(teachers.get(k).getId().equals(teacherID)){
                TeachID = k;
            }
        }

        if(TeachID == -1){
            System.out.println("Incorrect teacher ID, please, try again");
            return -1;
        }
        return TeachID;
    }


    public static double getStudentGPA(ArrayList<Grades> grades){
        double gpaValue = 0;
        for(int i = 0; i < grades.size();i++){
            float averageGrade = 0;
            int counter = 0;
            for(int j = 0; j < grades.get(i).getGrades().size(); j++){
                averageGrade += grades.get(i).getGrades().get(j);
                counter++;
            }
            float one = 1;
            float subjectPercent = one/counter;//subjectPercent - единица деленная на количество оценок
            gpaValue+=(averageGrade*subjectPercent);
        }
        gpaValue /= grades.size();
        return gpaValue;
    }



    public static int sortByGPA(ArrayList<Grades> grades, Long studID){
        ArrayList<Grades> yourGrades = DBManager.showGrades(studID);
        int placeByGPA = 1;
        for(int i = 0; i < students.size(); i++){
            ArrayList<Grades> anotherGrades = DBManager.showGrades(students.get(i).getId());
            if(getStudentGPA(yourGrades) < getStudentGPA(anotherGrades) && students.get(i).getId() != studID){
                placeByGPA++;
            }
        }
        return placeByGPA;

    }
}
