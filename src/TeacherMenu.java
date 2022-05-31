import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMenu extends Container {
    public static JTextArea findStudent = new JTextArea();

    public TeacherMenu(){
        setSize(500, 400);
        setLayout(null);

        JLabel modName = new JLabel("Teacher: " + Main.teachers.get(MainMenu.TeachID).getName() + " " + Main.teachers.get(MainMenu.TeachID).getSurname()+ " | ID: " + Main.teachers.get(MainMenu.TeachID).getId());
        modName.setBounds(150, 50, 300, 30);
        add(modName);

        JButton addGrades = new JButton("ADD GRADES");
        addGrades.setBounds(40, 80, 200, 30);
        add(addGrades);

        addGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(false);
                Main.frame.teacherMenuAddGrades.setVisible(true);
                Main.frame.teacherMenuFindGrade.setVisible(true);
                TeacherMenuFindGrade.findStudent.setText("");
            }
        });

        JButton changeGrades = new JButton("CHANGE GRADES");
        changeGrades.setBounds(40, 120, 200, 30);
        add(changeGrades);

        changeGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(false);
                Main.frame.teacherMenuChangeGrades.setVisible(true);
                Main.frame.teacherMenuFindGrade.setVisible(true);
                TeacherMenuFindGrade.findStudent.setText("");
            }
        });

        JButton deleteGrades = new JButton("DELETE GRADES");
        deleteGrades.setBounds(40, 160, 200, 30);
        add(deleteGrades);

        deleteGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(false);
                Main.frame.teacherMenuDeleteGrades.setVisible(true);
                Main.frame.teacherMenuFindGrade.setVisible(true);
                TeacherMenuFindGrade.findStudent.setText("");
            }
        });

        JButton setHomework = new JButton("SET HOMEWORK");
        setHomework.setBounds(250, 80, 200, 30);
        add(setHomework);

        setHomework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(false);
                Main.frame.teacherMenuSetHomework.setVisible(true);
                Main.frame.teacherMenuFindHomework.setVisible(true);
                TeacherMenuFindHomework.findHomework.setText("");
            }
        });

        JButton changeHomework = new JButton("CHANGE HOMEWORK");
        changeHomework.setBounds(250, 120, 200, 30);
        add(changeHomework);

        changeHomework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(false);
                Main.frame.teacherMenuChangeHomework.setVisible(true);
                Main.frame.teacherMenuFindHomework.setVisible(true);
                TeacherMenuFindHomework.findHomework.setText("");
            }
        });

        JButton deleteHomework = new JButton("DELETE HOMEWORK");
        deleteHomework.setBounds(250, 160, 200, 30);
        add(deleteHomework);

        deleteHomework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(false);
                Main.frame.teacherMenuDeleteHomework.setVisible(true);
                Main.frame.teacherMenuFindHomework.setVisible(true);
                TeacherMenuFindHomework.findHomework.setText("");
            }
        });

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(150, 290, 200, 30);
        add(exitButton);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


    }
}
