import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ModStudEditDelete extends Container {
    public ModStudEditDelete(){
        setSize(500, 400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

        JTextArea findStudent = new JTextArea();
        findStudent.setBounds(100, 80, 300, 100);
        findStudent.setEditable(false);
        add(findStudent);

        JButton findStudentButton = new JButton("LIST");
        findStudentButton.setBounds(100, 280, 140, 30);
        add(findStudentButton);

        JButton deleteStudent = new JButton("DELETE");
        deleteStudent.setBounds(100, 180, 140, 30);
        add(deleteStudent);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(260, 280, 140, 30);
        add(backButton);

        findStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());
                Main.students = DBManager.getAllStudents();
                int StudID = Main.getStudentIndex(Main.students, id);

                if(StudID != -1){
                    findStudent.setText(Main.students.get(StudID).toStringForModerator());
                }
                else
                    findStudent.setText("There is no student with ID: " + id);

            }
        });

        deleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());

                int studID = Main.getStudentIndex(Main.students, id);
                Main.students.remove(studID);

                PackageData pd = new PackageData("DELETE STUDENT", id);
                Main.connect(pd);

//                DBManager.deleteStudent(id);
                findStudent.setText("User deleted");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modStudEdit.setVisible(true);
                Main.frame.modStudEditDelete.setVisible(false);
                findStudent.setText("");
                idTextField.setText("");
            }
        });

    }
}
