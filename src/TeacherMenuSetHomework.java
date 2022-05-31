import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class TeacherMenuSetHomework extends Container {
    public static JTextArea conditionTextArea;
    public static JButton hwShow;

    public TeacherMenuSetHomework(){
        setSize(500, 400);
        setLayout(null);

        JLabel groupLabel = new JLabel("GROUP:");
        groupLabel.setBounds(100, 20, 100, 30);
        add(groupLabel);

        JTextField groupTextField = new JTextField();
        groupTextField.setBounds(250, 20, 135, 30);
        add(groupTextField);

        JLabel hwtaskLabel = new JLabel("HOMEWORK TASK:");
        hwtaskLabel.setBounds(100, 150, 100, 30);
        add(hwtaskLabel);

        JTextField hwtaskTextField = new JTextField();
        hwtaskTextField.setBounds(250, 150, 135, 30);
        add(hwtaskTextField);

        JLabel deadlineLabel = new JLabel("DEADLINE:");
        deadlineLabel.setBounds(100, 180, 100, 30);
        add(deadlineLabel);

        JTextField deadlineTextField = new JTextField();
        deadlineTextField.setBounds(250, 180, 135, 30);
        add(deadlineTextField);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 210, 200, 30);
        add(conditionLabel);

        conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        hwShow = new JButton("SHOW HW");
        hwShow.setBounds(100, 250, 140, 30);
        add(hwShow);

        JButton addHomework = new JButton("ADD HW");
        addHomework.setBounds(100, 280, 140, 30);
        add(addHomework);



        hwShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherMenuFindHomework.findHomework.setText("");
                String groupName = groupTextField.getText();

                PackageData pd = new PackageData("LIST HOMEWORK", groupName);
                Main.connect(pd);
            }
        });

        addHomework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = groupTextField.getText();
                String homeworkTask = hwtaskTextField.getText();
                Date deadline = Date.valueOf(deadlineTextField.getText());
                ArrayList<Homework> homework = DBManager.getAllHomework();

                boolean repeatedDeadline = false;

                if(groupName.equals("") || homeworkTask.equals("") || deadline.equals("")) {
                    conditionTextArea.setText("NULL VALUES!!");
                }
                else{
                    for(int i = 0;i<homework.size();i++){
                        System.out.println(homework.get(i).getDeadline());
                        if(deadline.equals(homework.get(i).getDeadline())){
                            repeatedDeadline = true;
                        }
                    }
                    if(repeatedDeadline){
                        conditionTextArea.setText("Repeated deadline");
                    }else{
                        Homework tempHomework = new Homework();
                        tempHomework.setSubject(Main.teachers.get(MainMenu.TeachID).getSubject());
                        tempHomework.setGroup(groupName);
                        tempHomework.setHomeworkText(homeworkTask);
                        tempHomework.setDeadline(deadline);

                        PackageData pd = new PackageData("ADD HOMEWORK", tempHomework);
                        Main.connect(pd);
                        Main.homework.add(tempHomework);

                        hwShow.doClick();
                        conditionTextArea.setText("Successful!");
                        hwtaskTextField.setText("");
                        deadlineTextField.setText("");
                    }
                }
            }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBounds(260, 280, 140, 30);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.teacherMenu.setVisible(true);
                Main.frame.teacherMenuSetHomework.setVisible(false);
                Main.frame.teacherMenuFindHomework.setVisible(false);
                groupTextField.setText("");
                deadlineTextField.setText("");
                conditionTextArea.setText("");

            }
        });

    }
}
