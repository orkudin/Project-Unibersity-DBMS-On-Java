import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentMenuShowGrades extends Container implements Runnable{
    JTextArea listGrades;

    public StudentMenuShowGrades(){
        setSize(500, 400);
        setLayout(null);

        JButton gradesShow = new JButton("SHOW GRADES");
        gradesShow.setBounds(100, 250, 140, 30);
        add(gradesShow);

        listGrades = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(listGrades,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,200);
        listGrades.setEditable(false);
        add(scrollPane);


        gradesShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Grades> studentGrades = DBManager.showGrades(MainMenu.userLogin);
                System.out.println(MainMenu.checkStudentMode);
                if(studentGrades.isEmpty()){
                    listGrades.setText("There is no grades");
                }
                else
                    listGrades.setText(String.valueOf(studentGrades));

            }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBounds(260, 280, 140, 30);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.studentMenu.setVisible(true);
                Main.frame.studentMenuShowGrades.setVisible(false);
            }
        });


    }

    @Override
    public void run() {
        try{
//            System.out.println("Check run!");
            while(true){
//                System.out.println("Check loop");
                ArrayList<Grades> studentGrades = DBManager.showGrades(MainMenu.userLogin);
                listGrades.setText(String.valueOf(studentGrades));
                Thread.sleep(5000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
