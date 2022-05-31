import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModTeachEditDelete extends Container {
    public ModTeachEditDelete(){
        setSize(500, 400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

        JTextArea findTeacher = new JTextArea();
        findTeacher.setBounds(100, 80, 300, 100);
        findTeacher.setEditable(false);
        add(findTeacher);

        JButton findTeacherButton = new JButton("FIND");
        findTeacherButton.setBounds(100, 280, 140, 30);
        add(findTeacherButton);

        JButton deleteTeacher = new JButton("DELETE");
        deleteTeacher.setBounds(100, 180, 140, 30);
        add(deleteTeacher);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(260, 280, 140, 30);
        add(backButton);

        findTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());
                Main.teachers = DBManager.getAllTeachers();
                int TeachID = Main.getTeacherIndex(Main.teachers, id);

                if(TeachID != -1){

                    findTeacher.setText(Main.teachers.get(TeachID).toStringForModerator());
                }
                else
                    findTeacher.setText("There is no teacher with ID: " + id);
            }
        });


        deleteTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());

                int teachID = Main.getTeacherIndex(Main.teachers, id);
                Main.teachers.remove(teachID);

                PackageData pd = new PackageData("DELETE TEACHER", id);
                Main.connect(pd);

//                DBManager.deleteTeacher(id);
                findTeacher.setText("User deleted");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modTeachEdit.setVisible(true);
                Main.frame.modTeachEditDelete.setVisible(false);
                findTeacher.setText("");
                idTextField.setText("");
            }
        });

    }
}
