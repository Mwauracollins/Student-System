import javax.swing.*;
import java.awt.*;

public class Score extends JFrame {
    private int cat;
    private int exam;
    private  String grade;
    private JPanel scorePanel;
    private JLabel catResultsLabel;
    private JLabel examResultsLabel;


    public Score(int exam, int cat){
        this.exam = exam;
        this.cat = cat;
        this.grade = getGrade();
    }

    public Score() {
        setTitle("Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        catResultsLabel = new JLabel("Cat results");
        scorePanel.add(catResultsLabel);

        examResultsLabel = new JLabel("Exam results");
        scorePanel.add(examResultsLabel);

        add(scorePanel);
        pack();
        setVisible(true);

    }

    public int getCat(){

        return cat;
    }

    public int getExam() {

        return exam;
    }
    public int getAverage(){

        return (cat+exam);
    }
    public String getGrade(){
        int total = getAverage();
        if (total>=70){
            return "A";
        }
        else if (total >= 60 && total < 70){
            return "B";

        }
        else if (total >= 50 && total < 60){
            return "C";
        } else if (total >= 40 && total < 50) {
            return "D";
        } else {
            return "Fail";

        }
    }
}
