import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Course extends JFrame {
    private String courseCode;
    private String title;
    private Lecturer lecturer;
    private List<Student> students;
    private List<Score> scores;
    private int numCoursesEnrolled = 0;
    private final int maxCoursesEnrolled = 5;


    public Course(String courseCode, String title){
        this.courseCode = courseCode;
        this.title = title;
        this.students = new ArrayList<>();
        this.scores = new ArrayList<>();
        lecturer.allocateCourse(this);
        this.jdbc = jdbc;

    }
    private JPanel viewCoursePanel;
    private  JLabel addCourseLabel;
    private JTextField addCourseField;
    private JButton addCourseButton;
    private JLabel coursesEnrolledLabel;
    private JLabel numCoursesEnrolledLabel;
    private JDBC jdbc;


    public Course() {
        setTitle("Courses");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        viewCoursePanel = new JPanel();
        viewCoursePanel.setLayout(new BoxLayout(viewCoursePanel, BoxLayout.Y_AXIS));

        addCourseLabel = new JLabel("Add Course");
        viewCoursePanel.add(addCourseLabel);

        addCourseField = new JTextField();
        viewCoursePanel.add(addCourseField);

        addCourseButton = new JButton("Add Course");
        viewCoursePanel.add(addCourseButton);
        addCourseButton.addActionListener(e -> {
            if (numCoursesEnrolled >= maxCoursesEnrolled){
                JOptionPane.showMessageDialog(viewCoursePanel, "You have already enrolled the max numer of courses");
            }else {
                String courseToAdd = addCourseField.getText().trim();
                if (courseToAdd.isEmpty()){
                    JOptionPane.showMessageDialog(viewCoursePanel, "Enter a course to add");
                }else {
                    try{
                        String sql = "INSERT INTO courses (courseCode, title) VALUES (?, ?)";
                        PreparedStatement stmt = jdbc.connection().prepareStatement(sql);
                        stmt.setString(1, courseToAdd);
                        stmt.setString(2, title);
                        int rows = stmt.executeUpdate();
                        if (rows > 0){
                            JOptionPane.showMessageDialog(viewCoursePanel, "Course Added");
                            numCoursesEnrolled++;
                            numCoursesEnrolledLabel.setText(Integer.toString(numCoursesEnrolled));
                        }else {
                            JOptionPane.showMessageDialog(viewCoursePanel, "Unable to add course");
                        }
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(viewCoursePanel, "Error" + ex.getMessage());
                    }
                }
            }
        });



        add(viewCoursePanel);
        pack();
        setVisible(true);

    }

    public void allocateLecturer(Lecturer lecturer){
        this.lecturer = lecturer;
    }
    public void addStudent(Student student){
        if (students.size() < 30) {
            students.add(student);
        } else {
            System.out.println("This course is already full.");
        }
    }
    public void addScore(Score score) {
        if (scores.size() < students.size()) {
            scores.add(score);
        } else {
            System.out.println("All students in this course already have scores.");
        }
    }
    public List<Student> getStudent(){
        return students;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getTitle(){
        return title;
    }
    public Lecturer getLecturer() {
        return lecturer;
    }

    public List<Score> getScores() {
        return scores;
    }


}
