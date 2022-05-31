import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    public static MainMenu mainMenu;



    public static StudentMenu studentMenu;
    public static StudentMenuShowGrades studentMenuShowGrades;
    public static StudentMenuShowHomework studentMenuShowHomework;
    public static StudentMenuAverageGrade studentMenuAverageGrade;
    public static StudentMenuRankingByGPA studentMenuRankingByGPA;

    public static TeacherMenu teacherMenu;

    public static TeacherMenuAddGrades teacherMenuAddGrades;
    public static TeacherMenuChangeGrades teacherMenuChangeGrades;
    public static TeacherMenuDeleteGrades teacherMenuDeleteGrades;
    public static TeacherMenuSetHomework teacherMenuSetHomework;
    public static TeacherMenuChangeHomework teacherMenuChangeHomework;
    public static TeacherMenuDeleteHomework teacherMenuDeleteHomework;
    public static TeacherMenuFindGrade teacherMenuFindGrade;
    public static TeacherMenuFindHomework teacherMenuFindHomework;




    public static ModeratorMenu moderatorMenu;

    public static ModStudEdit modStudEdit;
    public static ModStudEditAdd modStudEditAdd;
    public static ModStudEditList modStudEditList;
    public static ModStudEditChange modStudEditChange;
    public static ModStudEditDelete modStudEditDelete;

    public static ModTeachEdit modTeachEdit;
    public static ModTeachEditAdd modTeachEditAdd;
    public static ModTeachEditList modTeachEditList;
    public static ModTeachEditChange modTeachEditChange;
    public static ModTeachEditDelete modTeachEditDelete;


    public MainFrame(){
        setSize(500, 400);
        setLocation(500,100);
        setTitle("Student APP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        mainMenu = new MainMenu();
        mainMenu.setLocation(0,0);
        add(mainMenu);

        studentMenu = new StudentMenu();
        studentMenu.setLocation(0, 0);
        studentMenu.setVisible(false);
        add(studentMenu);

        studentMenuShowGrades = new StudentMenuShowGrades();
        studentMenuShowGrades.setLocation(0, 0);
        studentMenuShowGrades.setVisible(false);
        add(studentMenuShowGrades);

        studentMenuShowHomework = new StudentMenuShowHomework();
        studentMenuShowHomework.setLocation(0, 0);
        studentMenuShowHomework.setVisible(false);
        add(studentMenuShowHomework);

//        studentMenuSubjectInfo = new StudentMenuSubjectInfo();
//        studentMenuSubjectInfo.setLocation(0, 0);
//        studentMenuSubjectInfo.setVisible(false);
//        add(studentMenuSubjectInfo);

        studentMenuAverageGrade = new StudentMenuAverageGrade();
        studentMenuAverageGrade.setLocation(0, 0);
        studentMenuAverageGrade.setVisible(false);
        add(studentMenuAverageGrade);

        studentMenuRankingByGPA = new StudentMenuRankingByGPA();
        studentMenuRankingByGPA.setLocation(0, 0);
        studentMenuRankingByGPA.setVisible(false);
        add(studentMenuRankingByGPA);


        teacherMenu = new TeacherMenu();
        teacherMenu.setLocation(0, 0);
        teacherMenu.setVisible(false);
        add(teacherMenu);

        teacherMenuAddGrades = new TeacherMenuAddGrades();
        teacherMenuAddGrades.setLocation(0, 0);
        teacherMenuAddGrades.setVisible(false);
        add(teacherMenuAddGrades);

        teacherMenuChangeGrades = new TeacherMenuChangeGrades();
        teacherMenuChangeGrades.setLocation(0, 0);
        teacherMenuChangeGrades.setVisible(false);
        add(teacherMenuChangeGrades);

        teacherMenuDeleteGrades = new TeacherMenuDeleteGrades();
        teacherMenuDeleteGrades.setLocation(0, 0);
        teacherMenuDeleteGrades.setVisible(false);
        add(teacherMenuDeleteGrades);

        teacherMenuSetHomework  = new TeacherMenuSetHomework();
        teacherMenuSetHomework.setLocation(0, 0);
        teacherMenuSetHomework.setVisible(false);
        add(teacherMenuSetHomework);

        teacherMenuChangeHomework  = new TeacherMenuChangeHomework();
        teacherMenuChangeHomework.setLocation(0, 0);
        teacherMenuChangeHomework.setVisible(false);
        add(teacherMenuChangeHomework);

        teacherMenuDeleteHomework  = new TeacherMenuDeleteHomework();
        teacherMenuDeleteHomework.setLocation(0, 0);
        teacherMenuDeleteHomework.setVisible(false);
        add(teacherMenuDeleteHomework);

        teacherMenuFindGrade  = new TeacherMenuFindGrade();
        teacherMenuFindGrade.setLocation(0, 0);
        teacherMenuFindGrade.setVisible(false);
        add(teacherMenuFindGrade);

        teacherMenuFindHomework  = new TeacherMenuFindHomework();
        teacherMenuFindHomework.setLocation(0, 0);
        teacherMenuFindHomework.setVisible(false);
        add(teacherMenuFindHomework);


        moderatorMenu = new ModeratorMenu();
        moderatorMenu.setLocation(0, 0);
        moderatorMenu.setVisible(false);
        add(moderatorMenu);

        modStudEdit = new ModStudEdit();
        modStudEdit.setLocation(0, 0);
        modStudEdit.setVisible(false);
        add(modStudEdit);

        modStudEditAdd = new ModStudEditAdd();
        modStudEditAdd.setLocation(0, 0);
        modStudEditAdd.setVisible(false);
        add(modStudEditAdd);

        modStudEditList = new ModStudEditList();
        modStudEditList.setLocation(0, 0);
        modStudEditList.setVisible(false);
        add(modStudEditList);

        modStudEditChange = new ModStudEditChange();
        modStudEditChange.setLocation(0, 0);
        modStudEditChange.setVisible(false);
        add(modStudEditChange);

        modStudEditDelete = new ModStudEditDelete();
        modStudEditDelete.setLocation(0, 0);
        modStudEditDelete.setVisible(false);
        add(modStudEditDelete);

        modTeachEdit = new ModTeachEdit();
        modTeachEdit.setLocation(0, 0);
        modTeachEdit.setVisible(false);
        add(modTeachEdit);

        modTeachEditAdd = new ModTeachEditAdd();
        modTeachEditAdd.setLocation(0, 0);
        modTeachEditAdd.setVisible(false);
        add(modTeachEditAdd);

        modTeachEditList = new ModTeachEditList();
        modTeachEditList.setLocation(0, 0);
        modTeachEditList.setVisible(false);
        add(modTeachEditList);

        modTeachEditChange = new ModTeachEditChange();
        modTeachEditChange.setLocation(0, 0);
        modTeachEditChange.setVisible(false);
        add(modTeachEditChange);

        modTeachEditDelete = new ModTeachEditDelete();
        modTeachEditDelete.setLocation(0, 0);
        modTeachEditDelete.setVisible(false);
        add(modTeachEditDelete);

    }
}
