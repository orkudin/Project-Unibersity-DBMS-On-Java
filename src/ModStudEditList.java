import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModStudEditList extends Container {
    public static JTextArea findStudent;

    public ModStudEditList(){
        setSize(500, 400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

        findStudent = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(findStudent,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,155);
        findStudent.setEditable(false);
        add(scrollPane);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 210, 200, 30);
        add(conditionLabel);

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        JButton showStudent = new JButton("SHOW");
        showStudent.setBounds(100, 280, 140, 30);
        add(showStudent);

        showStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strId = idTextField.getText();
                findStudent.setText("");

                if(strId.equals("")){
                    ArrayList<Student>students = DBManager.getAllStudents();

                    if(students==null){
                        conditionTextArea.setText("There is no students");
                    }
                    else{
//                        String allStudents = "";
//                        for(int i = 0;i<students.size();i++){
//                            allStudents += students.get(i).toStringForModerator() + "\n\n";
//                        }

                        PackageData pd = new PackageData("LIST ALL STUDENTS");
                        Main.connect(pd);

//                        findStudent.setText(allStudents);
                        conditionTextArea.setText("Successful!");
                    }
                }else{
                    Long id = Long.valueOf(idTextField.getText());

                    Student student = DBManager.getStudent(id);

                    if(student==null){
                        conditionTextArea.setText("There is no that student");
                    }
                    else{

                        PackageData pd = new PackageData("LIST ONE STUDENT", id);
                        Main.connect(pd);

//                        findStudent.setText(student.toStringForModerator());
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
                Main.frame.modStudEditList.setVisible(false);
                findStudent.setText("");
                idTextField.setText("");
                conditionTextArea.setText("");
            }
        });

    }
}
