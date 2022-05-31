import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class TeacherMenuChangeGrades extends Container {
    public static JButton showStudentGrades;
    public static JTextArea conditionTextArea;
    public TeacherMenuChangeGrades(){
        setSize(500, 400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

//        JScrollPane scrollPane = new JScrollPane(TeacherMenu.findStudent ,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setBounds(100,50,300,100);
//        TeacherMenu.findStudent .setEditable(false);
//        add(scrollPane);

        JLabel changeGrade = new JLabel("CHANGE GRADE:");
        changeGrade.setBounds(100, 150, 100, 30);
        add(changeGrade);

        JTextField changeGradeTextField = new JTextField();
        changeGradeTextField.setBounds(250, 150, 135, 30);
        add(changeGradeTextField);

        JLabel dateLabel = new JLabel("ADD DATE:");
        dateLabel.setBounds(100, 180, 100, 30);
        add(dateLabel);

        JTextField addDateTextField = new JTextField();
        addDateTextField.setBounds(250, 180, 135, 30);
        add(addDateTextField);


        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 210, 200, 30);
        add(conditionLabel);

        conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        showStudentGrades = new JButton("SHOW");
        showStudentGrades.setBounds(100, 280, 140, 30);
        add(showStudentGrades);

        showStudentGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherMenuFindGrade.findStudent.setText("");
                Long id = Long.valueOf(idTextField.getText());

                PackageData pd = new PackageData("LIST GRADES", id);
                Main.connect(pd);
            }
        });

        JButton changeGrades = new JButton("CHANGE");
        changeGrades.setBounds(100, 250, 140, 30);
        add(changeGrades);

        changeGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());
                Double newGrade = Double.valueOf(changeGradeTextField.getText());
                String dateForChangeGrade = addDateTextField.getText();
                String subjectName = Main.teachers.get(MainMenu.TeachID).getSubject();

                if (newGrade >= 0 && newGrade <= 5) {
                    if (newGrade.equals("") || dateForChangeGrade.equals("")) {
                        conditionTextArea.setText("Null date or grade");
                    } else {
                        ArrayList<Grades> tempStudentGrades = DBManager.showGrades(id);
                        int subjectIndex = Main.getSubjectIndex(tempStudentGrades, subjectName);
                        int dateIndex = Main.getDataIndex(tempStudentGrades, subjectIndex, dateForChangeGrade);//Поиск индекса даты за которую была выставлена оценка
                        if (dateIndex == -1) {
                            conditionTextArea.setText("Incorrect date");
                        } else {
                            Grades updateGrade = new Grades(subjectName, newGrade, Date.valueOf(dateForChangeGrade), true);
                            PackageData pd = new PackageData("UPDATE GRADES", id, updateGrade);
                            Main.connect(pd);
                            showStudentGrades.doClick();
                        }
                    }

                    changeGradeTextField.setText("");
                    addDateTextField.setText("");
                } else {
                    conditionTextArea.setText("Incorrect grade");
                }
            }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBounds(260, 280, 140, 30);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(true);
                Main.frame.teacherMenuChangeGrades.setVisible(false);
                Main.frame.teacherMenuFindGrade.setVisible(false);
                changeGradeTextField.setText("");
                conditionTextArea.setText("");
                addDateTextField.setText("");
                idTextField.setText("");
                TeacherMenu.findStudent.setText("");
            }
        });

    }
}
