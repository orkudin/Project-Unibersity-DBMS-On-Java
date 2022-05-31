import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherMenuDeleteGrades extends Container {
    public TeacherMenuDeleteGrades(){
        setSize(500, 400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

//        JTextArea findStudent = new JTextArea();
//        JScrollPane scrollPane = new JScrollPane(findStudent,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPane.setBounds(100,50,300,100);
//        findStudent.setEditable(false);
//        add(scrollPane);


        JLabel dateLabel = new JLabel("ADD DATE [DELETE]:");
        dateLabel.setBounds(100, 180, 130, 30);
        add(dateLabel);

        JTextField deleteDateTextField = new JTextField();
        deleteDateTextField.setBounds(250, 180, 135, 30);
        add(deleteDateTextField);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 210, 200, 30);
        add(conditionLabel);

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        JButton showStudentGrades = new JButton("SHOW");
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

        JButton deleteGrades = new JButton("REMOVE");
        deleteGrades.setBounds(100, 250, 140, 30);
        add(deleteGrades);

        deleteGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());
                String date = deleteDateTextField.getText();
                String subjectName = Main.teachers.get(MainMenu.TeachID).getSubject();

                ArrayList<Grades> tempStudentGrades = DBManager.showGrades(id);;
                String subjectForChangeGrade = Main.teachers.get(MainMenu.TeachID).getSubject();
                int subjectIndex = Main.getSubjectIndex(tempStudentGrades, subjectForChangeGrade);//Поиск индекса предмета (Math, Physics, English)

                int dateIndex = Main.getDataIndex(tempStudentGrades, subjectIndex, date);//Поиск индекса даты оценку котороый вы хотите удалить
                    if(subjectIndex == -1 || dateIndex == -1){
                        conditionTextArea.setText("No grade for this date");
                        deleteDateTextField.setText("");
                    }else if(date.equals("")){
                        conditionTextArea.setText("Enter date");
                    }
                    else{
                        Grades deleteGrades = new Grades(subjectName, java.sql.Date.valueOf(date));
                        PackageData pd = new PackageData("DELETE GRADES", id, deleteGrades);
                        Main.connect(pd);

                        showStudentGrades.doClick();

//                        DBManager.deleteGrade(id, subjectName, java.sql.Date.valueOf(date));
                        deleteDateTextField.setText("");
                        conditionTextArea.setText("Successful!");
                        showStudentGrades.doClick();
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
                Main.frame.teacherMenuDeleteGrades.setVisible(false);
                Main.frame.teacherMenuFindGrade.setVisible(false);
                conditionTextArea.setText("");
                deleteDateTextField.setText("");
                idTextField.setText("");
            }
        });
    }
}
