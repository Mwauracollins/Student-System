import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {
    private JPanel homePanel;
    private JButton viewCourses;
    private JButton viewBooks;
    private JButton viewLecturer;
    private JButton studentDetails;
    private JLabel welcomeLabel;

    public Homepage(Student student){
        setTitle("Homepage");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

        welcomeLabel = new JLabel("Welcome," + student.getName);
        studentDetails = new JButton("Your Details");
        homePanel.add(studentDetails);
        studentDetails.addActionListener(e -> {
            Student studentwindow = new Student();
            studentwindow.setVisible(true);
        });

        viewCourses = new JButton("View Courses");
        homePanel.add(viewCourses);
        viewCourses.addActionListener(e -> {
            Course coursewindow = new Course();
            coursewindow.setVisible(true);
        });

        viewBooks = new JButton("View Library");
        homePanel.add(viewBooks);
        viewBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book bookwindow = new Book();
                bookwindow.setVisible(true);
            }
        });

        viewLecturer = new JButton("Lectures");
        homePanel.add(viewLecturer);
        viewLecturer.addActionListener(e -> {
            Lecturer lecturerwindow = new Lecturer();
            lecturerwindow.setVisible(true);
        });

        add(homePanel);
        pack();
        setVisible(true);
    }

}
