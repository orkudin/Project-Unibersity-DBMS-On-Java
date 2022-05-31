import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class TeacherMenuAddGrades extends Container {
    public TeacherMenuAddGrades(){
        setSize(500, 400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

        JLabel addGrade = new JLabel("ADD GRADE:");
        addGrade.setBounds(100, 150, 100, 30);
        add(addGrade);

        JTextField addGradeTextField = new JTextField();
        addGradeTextField.setBounds(250, 150, 135, 30);
        add(addGradeTextField);

        JLabel dateLabel = new JLabel("ADD DATE:");
        dateLabel.setBounds(100, 180, 100, 30);
        add(dateLabel);

        JTextField addDateTextField = new JTextField();
        addDateTextField.setBounds(250, 180, 135, 30);
        add(addDateTextField);

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

        JButton addGrades = new JButton("ADD");
        addGrades.setBounds(100, 250, 140, 30);
        add(addGrades);

        addGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());
                Double grade = Double.valueOf(addGradeTextField.getText());
                String date = addDateTextField.getText();
                String subjectName = Main.teachers.get(MainMenu.TeachID).getSubject();

                boolean dateRepeat = false;
                ArrayList<Grades> studentGrades = DBManager.showGrades(id);
                for(int i = 0; i < studentGrades.size(); i++){
                    for(int j = 0; j < studentGrades.get(i).getDates().size();j++){
                        if(studentGrades.get(i).getDates().get(j).equals(date) && studentGrades.get(i).getSubjectName().equals(subjectName)){
                            dateRepeat = true;
                        }
                    }
                }

                if(String.valueOf(grade).equals("") || String.valueOf(date).equals("")){
                    conditionTextArea.setText("Incorrect date or grade");
                } else if(dateRepeat){
                    conditionTextArea.setText("Date repeated");
                } else if(grade >= 0 && grade <= 5){

                    Grades addGrade = new Grades(subjectName, grade, Date.valueOf(date), true);
                    PackageData pd = new PackageData("ADD GRADES", id, addGrade);
                    Main.connect(pd);

                    addGradeTextField.setText("");
                    addDateTextField.setText("");
                    conditionTextArea.setText("Successful!");
                    showStudentGrades.doClick();
                }else{
                    conditionTextArea.setText("Incorrect grade");
                    addGradeTextField.setText("");
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
                Main.frame.teacherMenuAddGrades.setVisible(false);
                Main.frame.teacherMenuFindGrade.setVisible(false);
                addGradeTextField.setText("");
                addDateTextField.setText("");
                idTextField.setText("");
                TeacherMenu.findStudent.setText("");
                conditionTextArea.setText("");
            }
        });




    }
}
