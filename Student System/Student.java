import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends JFrame {
    private Person person;
    //private String programme;
    //private List<Course> courses;
    //private Map<Course, Score> scores;
    private JPanel studentPanel;
    private JLabel studentNameLabel;
    private JLabel regNoLabel;
    private JLabel programLabel;
    private JDBC jdbc;
    private JButton studentfinacialButton;


    public Student() {
        setTitle("Your Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        jdbc = new JDBC();

        studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentNameLabel = new JLabel("Name:");
        studentPanel.add(studentNameLabel);

        regNoLabel = new JLabel("Reg Number:");
        studentPanel.add(regNoLabel);

        programLabel = new JLabel("Your Program:");
        studentPanel.add(programLabel);

        studentfinacialButton = new JButton("View financials");
        studentPanel.add(studentfinacialButton);
        studentfinacialButton.addActionListener(e -> {
            Financial financialwindow = new Financial();
            financialwindow.setVisible(true);
        });




        try {
            // Connect to the database
            Connection conn = jdbc.connection();

            // Prepare the SQL query
            String sql = "SELECT name, regNo, program FROM student WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 123); // replace 123 with the actual ID of the student

            // Execute the query and retrieve the results
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Set the student details in the labels
                studentNameLabel.setText("Name: " + rs.getString("name"));
                regNoLabel.setText("Reg Number: " + rs.getString("regNo"));
                programLabel.setText("Your Program: " + rs.getString("program"));
            }

            // Close the database resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(studentPanel);
        pack();
        setVisible(true);
    }

    /*public Student(String name, int age, String regNo, String programme) {
        this.person = new Person(name, age);
        this.regNo = regNo;
        this.programme = programme;
        this.courses = new ArrayList<>();
        this.scores = new HashMap<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public String getProgramme() {
        return programme;
    }

    public void allocateCourse(Course course) {
        if (courses.size() < 5) {
            courses.add(course);
            course.addStudent(this);
            scores.put(course, new Score());
        } else {
            System.out.println("You have already enrolled in the maximum number of courses.");
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Score getCourseScore(Course course) {
        // Check if the student is enrolled in the specified course
        if (!courses.contains(course)) {
            throw new IllegalArgumentException("Student not enrolled in course.");
        }

        // Retrieve the score associated with  course
        Score score = scores.get(course);

        // If the score object doesn't exist, create a default one with 0 marks
        if (score == null) {
            score = new Score();
        }

        return score;
    }
    public String[][] getResult(){

        return getResult();
    }*/

}
