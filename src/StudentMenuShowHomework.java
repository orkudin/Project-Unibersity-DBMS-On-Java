import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentMenuShowHomework extends Container implements Runnable{
    JTextArea listHomework;

    public StudentMenuShowHomework(){
        setSize(500, 400);
        setLayout(null);


        JButton homeworkShow = new JButton("SHOW HOMEWORK");
        homeworkShow.setBounds(100, 250, 140, 30);
        add(homeworkShow);

        listHomework = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(listHomework,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100,50,300,200);
        listHomework.setEditable(false);
        add(scrollPane);


        homeworkShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            ArrayList<Homework> homework = DBManager.getAllHomework();
            Student student = DBManager.getStudent(MainMenu.userLogin);

            boolean checkHomework = true;
            String homeworkList = "";
                for(int i = 0;i < homework.size();i++){
                if(homework.get(i).getGroup().equals(student.getGroup())){
                    homeworkList += homework.get(i) +"\n";
                    checkHomework = false;
                }
            }
                if(checkHomework){
                    listHomework.setText("There is no homework");
            }else{
                    listHomework.setText("HOMEWORK: \n" + homeworkList);
            }
        }
        });

        JButton backButton = new JButton("BACK");
        backButton.setBounds(260, 280, 140, 30);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.frame.studentMenu.setVisible(true);
                Main.frame.studentMenuShowHomework.setVisible(false);
            }
        });


    }

    @Override
    public void run() {
        try{
//            System.out.println("Check run hw!");
            while(true){
                listHomework.setText("HOMEWORK:\n");
//                System.out.println("Check loop hw");
                ArrayList<Homework> studentHomeworks = DBManager.getHomeworkByGroup(Main.students.get(MainMenu.StudID).getGroup());
                listHomework.setText(String.valueOf(studentHomeworks));
                Thread.sleep(5000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
