import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModStudEditChange extends Container {

    public String[] parameters = {"Password", "Name", "Surname", "PhoneNumber", "Group"};
    public static JButton findStudentButton;

    public ModStudEditChange(){
        setSize(500, 400);
        setLayout(null);


        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

        JTextArea showUser = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(showUser,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,100);
        showUser.setEditable(false);
        add(scrollPane);

        JLabel addGrade = new JLabel("CHANGE:");
        addGrade.setBounds(100, 150, 100, 30);
        add(addGrade);

        JComboBox facultyBox = new JComboBox(parameters);
        facultyBox.setBounds(250, 150, 135, 30);
        add(facultyBox);

        JLabel dataLabel = new JLabel("NEW DATA:");
        dataLabel.setBounds(100, 180, 100, 30);
        add(dataLabel);

        JTextField addDataTextField = new JTextField();
        addDataTextField.setBounds(250, 180, 135, 30);
        add(addDataTextField);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 210, 200, 30);
        add(conditionLabel);

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        findStudentButton = new JButton("FIND");
        findStudentButton.setBounds(100, 280, 140, 30);
        add(findStudentButton);

        JButton changeDataButton = new JButton("CHANGE DATA");
        changeDataButton.setBounds(100, 250, 140, 30);
        add(changeDataButton);

        findStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());
                Main.students = DBManager.getAllStudents();
                int StudID = Main.getStudentIndex(Main.students, id);

                if(StudID != -1){
                    showUser.setText(Main.students.get(StudID).toStringForModerator());
                    addDataTextField.setText("");
                }
                else
                    showUser.setText("There is no student with ID: " + id);

            }
        });

        changeDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.valueOf(idTextField.getText());

                String parameter = (String)facultyBox.getSelectedItem();
                String parameterData = addDataTextField.getText();

                Student updateStudent;
                updateStudent = DBManager.getStudent(id);

                if(parameterData.equals("")){
                    conditionTextArea.setText("\nENTER DATA!!!");
                }else if(idTextField.equals("")){
                    conditionTextArea.setText("\nENTER ID!!!");
                }else{
                    //                "ID", "Password", "Name", "Surname", "PhoneNumber", "Subject", "Group"
                    if(parameter.equals("Password")){
                        updateStudent.setPassword(parameterData);

                        PackageData pd = new PackageData("UPDATE STUDENT", updateStudent);
                        Main.connect(pd);

//                        DBManager.updateStudent(updateStudent);
                        findStudentButton.doClick();
                        conditionTextArea.setText("Successful!");
                    }else if(parameter.equals("Name")){
                        updateStudent.setName(parameterData);

                        PackageData pd = new PackageData("UPDATE STUDENT", updateStudent);
                        Main.connect(pd);

//                        DBManager.updateStudent(updateStudent);
                        findStudentButton.doClick();
                        conditionTextArea.setText("Successful!");
                    }else if(parameter.equals("Surname")){
                        updateStudent.setSurname(parameterData);

                        PackageData pd = new PackageData("UPDATE STUDENT", updateStudent);
                        Main.connect(pd);

//                        DBManager.updateStudent(updateStudent);
                        findStudentButton.doClick();
                        conditionTextArea.setText("Successful!");
                    }else if(parameter.equals("PhoneNumber")){
                        boolean checkPhoneRepeated = false;
                        for(int i = 0; i < Main.allUsers.size();i++){
                            System.out.println(Main.allUsers.get(i).getPhoneNumber());
                            if(parameterData.equals(Main.allUsers.get(i).getPhoneNumber())){
                                checkPhoneRepeated = true;
                            }
                        }
                        if(checkPhoneRepeated) {
                            conditionTextArea.setText("Repeated Phone");
                        }else if(!checkPhoneRepeated){
                        updateStudent.setPhoneNumber(parameterData);

                            PackageData pd = new PackageData("UPDATE STUDENT", updateStudent);
                            Main.connect(pd);

//                        DBManager.updateStudent(updateStudent);
                        findStudentButton.doClick();
                        conditionTextArea.setText("Successful!");}
                    }else if(parameter.equals("Group")){
                        updateStudent.setGroup(parameterData);

                        PackageData pd = new PackageData("UPDATE STUDENT", updateStudent);
                        Main.connect(pd);

//                        DBManager.updateStudent(updateStudent);
                        findStudentButton.doClick();
                        conditionTextArea.setText("Successful!");
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
                Main.frame.modStudEdit.setVisible(true);
                Main.frame.modStudEditChange.setVisible(false);
                showUser.setText("");
                idTextField.setText("");
                conditionTextArea.setText("");
                addDataTextField.setText("");
            }
        });

    }
}
