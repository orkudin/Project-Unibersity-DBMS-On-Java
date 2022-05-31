import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModTeachEdit extends Container {
    public ModTeachEdit(){
        setSize(500, 400);
        setLayout(null);

        JButton addStudent = new JButton("ADD TEACHER");
        addStudent.setBounds(150, 50, 200, 30);
        add(addStudent);

        JButton listTeacher = new JButton("LIST TEACHERS");
        listTeacher.setBounds(150, 100, 200, 30);
        add(listTeacher);

        JButton changeInfoStudent = new JButton("CHANGE INFORMATION ABOUT TEACHER");
        changeInfoStudent.setBounds(150, 150, 200, 30);
        add(changeInfoStudent);

        JButton deleteStudent = new JButton("DELETE TEACHER");
        deleteStudent.setBounds(150, 200, 200, 30);
        add(deleteStudent);

        JButton exitStudentMode = new JButton("BACK TO MENU");
        exitStudentMode.setBounds(150, 250, 200, 30);
        add(exitStudentMode);

        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modTeachEdit.setVisible(false);
                Main.frame.modTeachEditAdd.setVisible(true);
            }
        });
        listTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modTeachEdit.setVisible(false);
                Main.frame.modTeachEditList.setVisible(true);
            }
        });
        changeInfoStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modTeachEdit.setVisible(false);
                Main.frame.modTeachEditChange.setVisible(true);
            }
        });
        deleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modTeachEdit.setVisible(false);
                Main.frame.modTeachEditDelete.setVisible(true);
            }
        });
        exitStudentMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.moderatorMenu.setVisible(true);
                Main.frame.modTeachEdit.setVisible(false);
            }
        });


    }
}
