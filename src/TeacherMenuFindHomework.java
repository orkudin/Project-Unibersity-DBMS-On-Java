import javax.swing.*;
import java.awt.*;

public class TeacherMenuFindHomework extends Container {
    public static JTextArea findHomework;
    public TeacherMenuFindHomework() {
        setSize(500, 400);
        setLayout(null);

        findHomework = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(findHomework,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,100);
        findHomework.setEditable(false);
        add(scrollPane);
    }
}
