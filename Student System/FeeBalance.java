import javax.swing.*;

public class FeeBalance extends JFrame {
    private JPanel feeBalancePanel;
    private JLabel feepaidLabel;
    private JLabel expectedpayLabel;
    private JLabel balanceLabel;
    private JLabel totalLabel;

    public FeeBalance(){
        setTitle("Fee Balance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        feeBalancePanel = new JPanel();
        feeBalancePanel.setLayout(new BoxLayout(feeBalancePanel, BoxLayout.Y_AXIS));

        feepaidLabel = new JLabel("Fee Paid:");
        feeBalancePanel.add(feepaidLabel);

        expectedpayLabel = new JLabel("Expected Pay");
        feeBalancePanel.add(expectedpayLabel);

        balanceLabel = new JLabel("Balance");
        feeBalancePanel.add(balanceLabel);

        totalLabel = new JLabel("Total");
        feeBalancePanel.add(totalLabel);

        add(feeBalancePanel);
        pack();
        setVisible(true);

    }
}
