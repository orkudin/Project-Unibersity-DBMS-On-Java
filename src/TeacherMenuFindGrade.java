import javax.swing.*;
import java.awt.*;

public class TeacherMenuFindGrade extends Container {
    public static JTextArea findStudent;
    public TeacherMenuFindGrade(){
        setSize(500, 400);
        setLayout(null);

        findStudent = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(findStudent ,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,100);
        findStudent .setEditable(false);
        add(scrollPane);
    }
}
