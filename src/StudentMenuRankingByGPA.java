import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentMenuRankingByGPA extends Container {
    public StudentMenuRankingByGPA(){
        setSize(500, 400);
        setLayout(null);

        JTextArea showInfoArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(showInfoArea,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,100);
        showInfoArea.setEditable(false);
        add(scrollPane);

        JButton showConditionOfGrantButton = new JButton("CONDITION OF GRANT");
        showConditionOfGrantButton.setBounds(100, 160, 140, 30);
        add(showConditionOfGrantButton);

        showConditionOfGrantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Grades> studentGrades = DBManager.showGrades(MainMenu.userLogin);

                double studentGPA = Main.getStudentGPA(studentGrades);
                studentGPA = Math.floor(studentGPA * 100) / 100;
                if(studentGPA > 3.33){
                    showInfoArea.setText("You have a grant, \ncurrent GPA: " +  studentGPA);
                }
                else
                    showInfoArea.setText("You haven't a grant, \ncurrent GPA: " + studentGPA);
            }
        });

        JButton showConditionOfStipendButton = new JButton("CONDITION OF STIPEND");
        showConditionOfStipendButton.setBounds(250, 160, 140, 30);
        add(showConditionOfStipendButton);

        showConditionOfStipendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Grades> studentGrades = DBManager.showGrades(MainMenu.userLogin);

                double studentGPA = Main.getStudentGPA(studentGrades);
                studentGPA = Math.floor(studentGPA * 100) / 100;
                if(studentGPA > 3.67){
                    showInfoArea.setText("You have a stipend, \ncurrent GPA: " +  studentGPA);
                }
                else
                    showInfoArea.setText("You haven't a stipend, \ncurrent GPA: " + studentGPA);
            }
        });

        JButton showRankByGPAButton = new JButton("Rank by GPA");
        showRankByGPAButton.setBounds(100, 200, 140, 30);
        add(showRankByGPAButton);

        showRankByGPAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Grades> allStudentGrades = new ArrayList<>();
                ArrayList<Student> allStudents = DBManager.getAllStudents();
                for(int i = 0;i < allStudents.size();i++){
                    ArrayList<Grades> yourGrades = DBManager.showGrades(allStudents.get(i).getId());
                    allStudentGrades.addAll(yourGrades);
                }

                ArrayList<Grades> yourGrades = DBManager.showGrades(MainMenu.userLogin);

                int placeByGPA = Main.sortByGPA(allStudentGrades, MainMenu.userLogin);

                if(yourGrades.isEmpty()){
                    showInfoArea.setText("You haven't grades");
                }else{
                    showInfoArea.setText("Rank in your specialty: " + placeByGPA + " place");
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
                Main.frame.studentMenuRankingByGPA.setVisible(false);
            }
        });



    }
}
