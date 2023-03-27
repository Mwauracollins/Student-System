import javax.swing.*;

public class Financial extends JFrame {
    private JPanel financialPanel;
    private JButton feeStructureButton;
    private JButton balanceButton;
    private JButton hostelsButton;

    public Financial() {
        setTitle("Financials");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        financialPanel = new JPanel();
        financialPanel.setLayout(new BoxLayout(financialPanel, BoxLayout.Y_AXIS));

        feeStructureButton = new JButton("Fee Structure");
        //flex code
        financialPanel.add(feeStructureButton);
        feeStructureButton.addActionListener(e -> {
            FeeStructure feeStructurewindow = new FeeStructure();
            feeStructurewindow.setVisible(true);
        });

        balanceButton = new JButton("Fee Balance");
        financialPanel.add(balanceButton);
        balanceButton.addActionListener(e -> {
            FeeBalance feeBalanceWindow = new FeeBalance();
            feeBalanceWindow.setVisible(true);
        });

        hostelsButton = new JButton("Hostels");
        financialPanel.add(hostelsButton);
        hostelsButton.addActionListener(e -> {
            HostelBooking hostelDetailsWindow = new HostelBooking();
            hostelDetailsWindow.setVisible(true);
        });

        add(financialPanel);
        pack();
        setVisible(true);
    }

    // Sample implementation of the FeeStructure class
    private static class FeeStructure extends JFrame {
        public FeeStructure() {
            setTitle("Fee Structure");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(200, 200);
        }
    }

    // Sample implementation of the FeeBalance class
    private static class FeeBalance extends JFrame {
        public FeeBalance() {
            setTitle("Fee Balance");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(200, 200);
        }
    }

    // Sample implementation of the HostelDetails class
    private static class HostelDetails extends JFrame {
        public HostelDetails() {
            setTitle("Hostel Details");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(200, 200);
        }
    }
}
