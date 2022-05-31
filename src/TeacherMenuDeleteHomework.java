import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class TeacherMenuDeleteHomework extends Container {
    public TeacherMenuDeleteHomework(){
        setSize(500, 400);
        setLayout(null);

        JLabel groupLabel = new JLabel("GROUP:");
        groupLabel.setBounds(100, 20, 100, 30);
        add(groupLabel);

        JTextField groupTextField = new JTextField();
        groupTextField.setBounds(250, 20, 135, 30);
        add(groupTextField);

        JLabel deadlineLabel = new JLabel("DEADLINE:");
        deadlineLabel.setBounds(100, 180, 100, 30);
        add(deadlineLabel);

        JTextField deadlineTextField = new JTextField();
        deadlineTextField.setBounds(250, 180, 135, 30);
        add(deadlineTextField);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 210, 200, 30);
        add(conditionLabel);

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        JButton hwShow = new JButton("SHOW HW");
        hwShow.setBounds(100, 250, 140, 30);
        add(hwShow);

        JButton deleteHomework = new JButton("DELETE HW");
        deleteHomework.setBounds(100, 280, 140, 30);
        add(deleteHomework);


        hwShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherMenuFindHomework.findHomework.setText("");
                String groupName = groupTextField.getText();

                PackageData pd = new PackageData("LIST HOMEWORK", groupName);
                Main.connect(pd);
            }
        });

        deleteHomework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = groupTextField.getText();
                Date deadline = Date.valueOf(deadlineTextField.getText());
                ArrayList<Homework> homework = DBManager.getAllHomework();

                boolean repeatedDeadline = false;
                if(groupName.equals("") || deadline.equals("")) {
                    conditionTextArea.setText("NULL VALUES!!");
                }

                else{
                    for(int i = 0;i<homework.size();i++){
                        if(deadline.equals(homework.get(i).getDeadline())){
                            repeatedDeadline = true;
                        }
                    }
                    if(!repeatedDeadline){
                        conditionTextArea.setText("There is no that deadline");
                        deadlineTextField.setText("");
                    }else{

                        Homework deleteHomework = new Homework();
                        deleteHomework.setGroup(groupName);
                        deleteHomework.setDeadline(deadline);

                        PackageData pd = new PackageData("DELETE HOMEWORK", deleteHomework);
                        Main.connect(pd);

                        hwShow.doClick();
                        conditionTextArea.setText("Successful!");
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
                Main.frame.teacherMenuDeleteHomework.setVisible(false);
                Main.frame.teacherMenuFindHomework.setVisible(false);
                groupTextField.setText("");
                deadlineTextField.setText("");
                conditionTextArea.setText("");
            }
        });
    }
}
