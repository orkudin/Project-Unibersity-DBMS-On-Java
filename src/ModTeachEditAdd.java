import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModTeachEditAdd extends Container {
    public String[] subjects = {"Math", "Physics", "English"};
    public String[] degree = {"Bachelor", "Master", "PhD"};

    public ModTeachEditAdd(){
        setSize(500, 400);
        setLayout(null);

        JLabel passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setBounds(100, 40, 100, 30);
        add(passwordLabel);

        JTextField passwordTextField = new JTextField();
        passwordTextField.setBounds(230, 40, 135, 30);
        add(passwordTextField);

        JLabel nameLabel = new JLabel("NAME:");
        nameLabel.setBounds(100, 70, 100, 30);
        add(nameLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(230, 70, 135, 30);
        add(nameTextField);

        JLabel surnameLabel = new JLabel("SURNAME:");
        surnameLabel.setBounds(100, 100, 100, 30);
        add(surnameLabel);

        JTextField surnameTextField = new JTextField();
        surnameTextField.setBounds(230, 100, 135, 30);
        add(surnameTextField);

        JLabel phoneNumberLabel = new JLabel("PHONE NUMBER:");
        phoneNumberLabel.setBounds(100, 130, 100, 30);
        add(phoneNumberLabel);

        JTextField phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(230, 130, 135, 30);
        add(phoneNumberTextField);

        JLabel subjectLabel = new JLabel("SUBJECT:");
        subjectLabel.setBounds(100, 160, 200, 30);
        add(subjectLabel);

        JComboBox subjectBox = new JComboBox(subjects);
        subjectBox.setBounds(230, 160, 135, 30);
        add(subjectBox);

        JLabel degreeLabel = new JLabel("DEGREE:");
        degreeLabel.setBounds(100, 190, 200, 30);
        add(degreeLabel);

        JComboBox degreeBox = new JComboBox(degree);
        degreeBox.setBounds(230, 190, 135, 30);
        add(degreeBox);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 220, 200, 30);
        add(conditionLabel);

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(230, 220, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        JButton addTeacher = new JButton("ADD TEACHER");
        addTeacher.setBounds(150, 270, 200, 30);
        add(addTeacher);

        JButton exitTeachEditAdd = new JButton("BACK");
        exitTeachEditAdd.setBounds(150, 310, 200, 30);
        add(exitTeachEditAdd);

        addTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordTextField.getText();
                String name = nameTextField.getText();
                String surname = surnameTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String subject = (String)subjectBox.getSelectedItem();
                String degree = (String)degreeBox.getSelectedItem();

                boolean checkPhoneRepeated = false;

                if(password.equals("") || name.equals("") || surname.equals("") || phoneNumber.equals("") || subject.equals("") || degree.equals("")){
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

                        Teacher newTeacher = new Teacher(password,name,surname, phoneNumber,subject,degree);
                        PackageData pd = new PackageData("ADD TEACHER", newTeacher);
                        Main.connect(pd);

//                        DBManager.addTeacher(newTeacher);
                        Main.allUsers.add(newTeacher);

                        passwordTextField.setText("");
                        nameTextField.setText("");
                        surnameTextField.setText("");
                        phoneNumberTextField.setText("");
                        conditionTextArea.setText("Successful!");
                    }
                }
            }

        });

        exitTeachEditAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.modTeachEdit.setVisible(true);
                Main.frame.modTeachEditAdd.setVisible(false);
                passwordTextField.setText("");
                nameTextField.setText("");
                surnameTextField.setText("");
                phoneNumberTextField.setText("");
                conditionTextArea.setText("");
            }
        });
    }

}

