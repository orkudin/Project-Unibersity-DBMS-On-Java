import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentMenuAverageGrade extends Container {
    public StudentMenuAverageGrade(){
        setSize(500, 400);
        setLayout(null);

        JLabel subjectLabel = new JLabel("SUBJECT:");
        subjectLabel.setBounds(100, 20, 100, 30);
        add(subjectLabel);

        JTextField subjectTextField = new JTextField();
        subjectTextField.setBounds(250, 20, 135, 30);
        add(subjectTextField);

        JTextArea showAverageGradeArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(showAverageGradeArea,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,100);
        showAverageGradeArea.setEditable(false);
        add(scrollPane);

        JButton showAverageGradeButton = new JButton("SHOW AVERAGE GRADE");
        showAverageGradeButton.setBounds(100, 280, 140, 30);
        add(showAverageGradeButton);

        showAverageGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String subjectName = subjectTextField.getText();

                ArrayList<Grades> tempStudentGrades = DBManager.showGrades(MainMenu.userLogin);
                int subjectIndex = Main.getSubjectIndex(tempStudentGrades, subjectName);//Поиск индекса предмета (Math, Physics, English)
                if(subjectIndex == -1){showAverageGradeArea.setText("There is no grades");}
                else{
                    double averageGrade = 0;
                    int counter = 0;

                    for(int i = 0; i < tempStudentGrades.get(subjectIndex).getGrades().size(); i++){
                        averageGrade += tempStudentGrades.get(subjectIndex).getGrades().get(i);
                        counter++;
                    }
                    averageGrade = Math.floor(averageGrade * 100) / 100;
                    showAverageGradeArea.setText("Average score of " + tempStudentGrades.get(subjectIndex).getSubjectName() + " is " + (averageGrade /= counter));
                }

            }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBounds(260, 280, 140, 30);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.studentMenu.setVisible(true);
                Main.frame.studentMenuAverageGrade.setVisible(false);
            }
        });




    }
}
