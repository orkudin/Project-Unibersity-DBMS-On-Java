import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ModStudEditAdd extends Container {
    public ModStudEditAdd(){
        setSize(500, 400);
        setLayout(null);

        JLabel passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setBounds(100, 50, 100, 30);
        add(passwordLabel);

        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(230, 50, 135, 30);
        add(passwordTextField);

        JLabel nameLabel = new JLabel("NAME:");
        nameLabel.setBounds(100, 80, 100, 30);
        add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(230, 80, 135, 30);
        add(nameTextField);

        JLabel surnameLabel = new JLabel("SURNAME:");
        surnameLabel.setBounds(100, 110, 100, 30);
        add(surnameLabel);

        JTextField surnameTextField = new JTextField();
        surnameTextField.setBounds(230, 110, 135, 30);
        add(surnameTextField);

        JLabel phoneNumberLabel = new JLabel("PHONE NUMBER:");
        phoneNumberLabel.setBounds(100, 140, 100, 30);
        add(phoneNumberLabel);

        JTextField phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(230, 140, 135, 30);
        add(phoneNumberTextField);

        JLabel groupLabel = new JLabel("GROUP:");
        groupLabel.setBounds(100, 170, 200, 30);
        add(groupLabel);

        JTextField groupTextField = new JTextField();
        groupTextField.setBounds(230, 170, 135, 30);
        add(groupTextField);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 200, 200, 30);
        add(conditionLabel);

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(230, 200, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        JButton addStudent = new JButton("ADD STUDENT");
        addStudent.setBounds(150, 250, 200, 30);
        add(addStudent);

        JButton exitStudEditAdd = new JButton("BACK TO MOD MENU");
        exitStudEditAdd.setBounds(150, 300, 200, 30);
        add(exitStudEditAdd);

        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordTextField.getText();
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String group = groupTextField.getText();

                boolean checkPhoneRepeated = false;

                if(password.equals("") || name.equals("") || surname.equals("") || phoneNumber.equals("") || group.equals("")){
                    conditionTextArea.setText("NULL VALUES!!");
                }else{
                    for(int i = 0; i < Main.allUsers.size();i++){
                        if(phoneNumber.equals(Main.allUsers.get(i).getPhoneNumber())){
                            checkPhoneRepeated = true;
                        }
                    }

                    if(checkPhoneRepeated) {
                        conditionTextArea.setText("Repeated Phone");
                    }else if(!checkPhoneRepeated){

                        Student newStudent = new Student(password,name,surname, phoneNumber,group);
                        PackageData pd = new PackageData("ADD STUDENT", newStudent);
                        Main.connect(pd);

//                        DBManager.addStudent(newStudent);
                        Main.allUsers.add(newStudent);

                        passwordTextField.setText("");
                        nameTextField.setText("");
                        surnameTextField.setText("");
                        phoneNumberTextField.setText("");
                        groupTextField.setText("");
                        conditionTextArea.setText("Successful!");
                    }
                }

            }
        });

        exitStudEditAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modStudEdit.setVisible(true);
                Main.frame.modStudEditAdd.setVisible(false);
                passwordTextField.setText("");
                nameTextField.setText("");
                surnameTextField.setText("");
                phoneNumberTextField.setText("");
                groupTextField.setText("");
                conditionTextArea.setText("");
            }
        });
    }


}
