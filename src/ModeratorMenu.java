import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeratorMenu extends Container {
    public ModeratorMenu(){
        setSize(500, 400);
        setLayout(null);

        JLabel modName = new JLabel("Moderator: " + Main.moderators.get(MainMenu.ModID).getName() + " " + Main.moderators.get(MainMenu.ModID).getSurname()+ " | ID: " + Main.moderators.get(MainMenu.ModID).getId());
        modName.setBounds(150, 50, 300, 30);
        add(modName);

        JButton editStudent = new JButton("STUDENT EDIT MODE");
        editStudent.setBounds(150, 100, 200, 30);
        add(editStudent);

        JButton editTeacher = new JButton("TEACHER EDIT MODE");
        editTeacher.setBounds(150, 150, 200, 30);
        add(editTeacher);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(150, 250, 200, 30);
        add(exitButton);

        editStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.moderatorMenu.setVisible(false);
                Main.frame.modStudEdit.setVisible(true);
            }
        });

        editTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.moderatorMenu.setVisible(false);
                Main.frame.modTeachEdit.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



    }



}
