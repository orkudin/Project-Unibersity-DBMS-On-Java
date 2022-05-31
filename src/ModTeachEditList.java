import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ModTeachEditList extends Container {
    public static JTextArea findTeacher;

    public ModTeachEditList(){
        setSize(500, 400);
        setLayout(null);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(100, 20, 100, 30);
        add(idLabel);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(250, 20, 135, 30);
        add(idTextField);

        findTeacher = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(findTeacher,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,155);
        findTeacher.setEditable(false);
        add(scrollPane);

        JLabel conditionLabel = new JLabel("CONDITION:");
        conditionLabel.setBounds(100, 210, 200, 30);
        add(conditionLabel);

        JTextArea conditionTextArea = new JTextArea();
        conditionTextArea.setBounds(250, 210, 135, 30);
        conditionTextArea.setEditable(false);
        add(conditionTextArea);

        JButton showTeacher = new JButton("SHOW");
        showTeacher.setBounds(100, 280, 140, 30);
        add(showTeacher);

        showTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strId = idTextField.getText();
                findTeacher.setText("");
                if(strId.equals("")){
                    ArrayList<Teacher> teachers = DBManager.getAllTeachers();
                    if(teachers==null){
                        conditionTextArea.setText("There is no teachers");
                    }
                    else{
//                        String allTeachers = "";
//                        for(int i = 0;i<teachers.size();i++){
//                            allTeachers += teachers.get(i).toStringForModerator() + "\n\n";
//                        }

                        PackageData pd = new PackageData("LIST ALL TEACHERS");
                        Main.connect(pd);

//                        findTeacher.setText(allTeachers);
                        conditionTextArea.setText("Successful!");
                    }
                }else{
                    Long id = Long.valueOf(idTextField.getText());
                    Teacher teacher = DBManager.getTeacher(id);
                    if(teacher==null){
                        conditionTextArea.setText("There is no that teacher");
                    }
                    else{

                        PackageData pd = new PackageData("LIST ONE TEACHER", id);
                        Main.connect(pd);

//                        findTeacher.setText(teacher.toStringForModerator());
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
                Main.frame.modTeachEdit.setVisible(true);
                Main.frame.modTeachEditList.setVisible(false);
                findTeacher.setText("");
                idTextField.setText("");
                conditionTextArea.setText("");
            }
        });
    }
}
