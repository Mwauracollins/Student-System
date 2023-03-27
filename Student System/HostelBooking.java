import javax.swing.*;

public class HostelBooking extends JFrame {
    private JPanel bookingPanel;
    private JButton bookButton;
    private JLabel floorLabel;
    private JTextField floorSelectField;
    private JLabel roomSelectLabel;
    private JTextField roomSelectField;

    public HostelBooking(){
        setTitle("Hostels Booking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        bookingPanel = new JPanel();
        bookingPanel.setLayout(new BoxLayout(bookingPanel, BoxLayout.Y_AXIS));

        floorLabel = new JLabel("Select the Floor");
        floorSelectField = new JTextField(20);
        bookingPanel.add(floorLabel);
        bookingPanel.add(floorSelectField);

        roomSelectLabel = new JLabel("Select the room");
        roomSelectField = new JTextField(20);
        bookingPanel.add(roomSelectLabel);
        bookingPanel.add(roomSelectField);

        bookButton = new JButton("Book");
        bookingPanel.add(bookButton);

        add(bookingPanel);
        pack();
        setVisible(true);
    }
}
