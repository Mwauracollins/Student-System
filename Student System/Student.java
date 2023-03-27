import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public String getName;
    private Person person;
    private String name;
    private String program;
    private String regNo;
    private List<Course> courses;
    private Map<Course, Score> scores;
    private JPanel studentPanel;
    private JLabel studentNameLabel;
    private JLabel regNoLabel;
    private JLabel programLabel;
    private JDBC jdbc;
    private JButton studentfinacialButton;
    private JButton scoresButton;


    public Student() {
        setTitle("Your Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        jdbc = new JDBC();

        studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentNameLabel = new JLabel("Name:" + name);
        studentPanel.add(studentNameLabel);

        regNoLabel = new JLabel("Reg Number:" + regNo);
        studentPanel.add(regNoLabel);

        programLabel = new JLabel("Your Program:" + program);
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
            stmt.setInt(1, 123);


            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                studentNameLabel.setText("Name: " + rs.getString("name"));
                regNoLabel.setText("Reg Number: " + rs.getString("regNo"));
                programLabel.setText("Your Program: " + rs.getString("program"));
            }


            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        scoresButton = new JButton("View results");
        studentPanel.add(scoresButton);
        scoresButton.addActionListener(e -> {
            Score scorewindow = new Score();
            scorewindow.setVisible(true);
        });

        add(studentPanel);
        pack();
        setVisible(true);
    }

    public Student(String name, int age, String regNo, String program) {
        this.person = new Person(name, age);
        this.regNo = regNo;
        this.program = program;
        this.courses = new ArrayList<>();
        this.scores = new HashMap<>();
    }


    public String getName(){
        return name;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getProgramme() {
        return program;
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

        if (!courses.contains(course)) {
            throw new IllegalArgumentException("Student not enrolled in course.");
        }

        Score score = scores.get(course);


        if (score == null) {
            score = new Score();
        }

        return score;
    }
    public String[][] getResult(){

        return getResult();
    }

}
