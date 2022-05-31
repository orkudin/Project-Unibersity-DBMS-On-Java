import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

public class TeacherMenuChangeHomework extends Container {
    public TeacherMenuChangeHomework(){
        setSize(500, 400);
        setLayout(null);

        JLabel groupLabel = new JLabel("GROUP:");
        groupLabel.setBounds(100, 20, 100, 30);
        add(groupLabel);

        JTextField groupTextField = new JTextField();
        groupTextField.setBounds(250, 20, 135, 30);
        add(groupTextField);

        JLabel hwtaskLabel = new JLabel("NEW HOMEWORK TASK:");
        hwtaskLabel.setBounds(100, 150, 130, 30);
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

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        JButton hwShow = new JButton("SHOW HW");
        hwShow.setBounds(100, 250, 140, 30);
        add(hwShow);

        JButton changeHomework = new JButton("CHANGE HW");
        changeHomework.setBounds(100, 280, 140, 30);
        add(changeHomework);


        hwShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherMenuFindHomework.findHomework.setText("");
                String groupName = groupTextField.getText();

                PackageData pd = new PackageData("LIST HOMEWORK", groupName);
                Main.connect(pd);
            }
        });

        changeHomework.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupName = groupTextField.getText();
                String homeworkTask = hwtaskTextField.getText();
                Date deadline = Date.valueOf(deadlineTextField.getText());

                ArrayList<Homework> homework = DBManager.getAllHomework();

                boolean checkHomeworkRepeat = true;
                for(int i = 0;i < homework.size();i++){
                    if(homework.get(i).getDeadline().equals(deadline)){
                        checkHomeworkRepeat = false;
                    }
                }

                if(groupName.equals("") || deadline.equals("") || groupName.equals(null)){
                    conditionTextArea.setText("NULL VALUES!!!");
                }else if(checkHomeworkRepeat){
                    conditionTextArea.setText("There is no that deadline");
                    deadlineTextField.setText("");
                }else{
                    Homework tempHomework = new Homework();
                    for(int i = 0; i<homework.size();i++){
                        if(homework.get(i).getGroup().equals(groupName) && homework.get(i).getDeadline().equals(deadline)){
                            tempHomework.setGroup(groupName);
                            tempHomework.setHomeworkText(homeworkTask);
                            tempHomework.setDeadline(deadline);
                            tempHomework.setSubject(Main.teachers.get(MainMenu.TeachID).getSubject());;

                            PackageData pd = new PackageData("UPDATE HOMEWORK", tempHomework);
                            Main.connect(pd);

                            Main.homework.set(i, tempHomework);

                            hwShow.doClick();
                            conditionTextArea.setText("Successful!");
                            hwtaskTextField.setText("");
                            deadlineTextField.setText("");
                        }
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
                Main.frame.teacherMenuChangeHomework.setVisible(false);
                Main.frame.teacherMenuFindHomework.setVisible(false);
                groupTextField.setText("");
                deadlineTextField.setText("");
                conditionTextArea.setText("");
            }
        });
    }
}
