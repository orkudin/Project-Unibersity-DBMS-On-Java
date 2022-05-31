import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModStudEdit extends Container {
    public ModStudEdit(){
        setSize(500, 400);
        setLayout(null);

        JButton addStudent = new JButton("ADD STUDENT");
        addStudent.setBounds(150, 50, 200, 30);
        add(addStudent);

        JButton listStudent = new JButton("LIST STUDENTS");
        listStudent.setBounds(150, 100, 200, 30);
        add(listStudent);

        JButton changeInfoStudent = new JButton("CHANGE INFORMATION ABOUT STUDENT");
        changeInfoStudent.setBounds(150, 150, 200, 30);
        add(changeInfoStudent);

        JButton deleteStudent = new JButton("DELETE STUDENT");
        deleteStudent.setBounds(150, 200, 200, 30);
        add(deleteStudent);

        JButton exitStudentMode = new JButton("BACK TO MENU");
        exitStudentMode.setBounds(150, 250, 200, 30);
        add(exitStudentMode);

        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modStudEdit.setVisible(false);
                Main.frame.modStudEditAdd.setVisible(true);
            }
        });
        listStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modStudEdit.setVisible(false);
                Main.frame.modStudEditList.setVisible(true);
            }
        });
        changeInfoStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modStudEdit.setVisible(false);
                Main.frame.modStudEditChange.setVisible(true);
            }
        });
        deleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modStudEdit.setVisible(false);
                Main.frame.modStudEditDelete.setVisible(true);
            }
        });
        exitStudentMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.moderatorMenu.setVisible(true);
                Main.frame.modStudEdit.setVisible(false);
            }
        });


    }
}
