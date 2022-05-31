import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentMenu extends Container {
    public StudentMenu(){
        setSize(500, 400);
        setLayout(null);

        JLabel modName = new JLabel("Student: " + Main.students.get(MainMenu.StudID).getName() + " " + Main.students.get(MainMenu.StudID).getSurname() + " | ID: " + Main.students.get(MainMenu.StudID).getId());
        modName.setBounds(150, 30, 200, 30);
        add(modName);

        JButton showGrades = new JButton("SHOW GRADES");
        showGrades.setBounds(150, 70, 200, 30);
        add(showGrades);

        showGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.studentMenu.setVisible(false);
                Main.frame.studentMenuShowGrades.setVisible(true);
                MainMenu.checkStudentMode = true;
            }
        });

        JButton showHomework = new JButton("SHOW HOMEWORK");
        showHomework.setBounds(150, 110, 200, 30);
        add(showHomework);

        showHomework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.studentMenu.setVisible(false);
                Main.frame.studentMenuShowHomework.setVisible(true);
                MainMenu.checkStudentMode = true;
            }
        });

        JButton averageGrade = new JButton("AVERAGE GRADE");
        averageGrade.setBounds(150, 150, 200, 30);
        add(averageGrade);

        averageGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.studentMenu.setVisible(false);
                Main.frame.studentMenuAverageGrade.setVisible(true);
            }
        });

        JButton rankingByGpa = new JButton("RANKING BY GPA");
        rankingByGpa.setBounds(150, 190, 200, 30);
        add(rankingByGpa);

        rankingByGpa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.studentMenu.setVisible(false);
                Main.frame.studentMenuRankingByGPA.setVisible(true);
            }
        });

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(150, 250, 200, 30);
        add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
