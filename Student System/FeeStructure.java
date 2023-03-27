import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class FeeStructure extends JFrame {

    private JPanel feeStructurePanel;
    private JTable feeStructureTable;

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    private String[] columnNames = {"Class", "Semester", "Fee Amount"};

    public FeeStructure() {
        setTitle("Fee Structure");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        feeStructurePanel = new JPanel();

        try {
            conn = new JDBC().connection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM feestructure");

            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            while(rs.next()) {
                String cls = rs.getString("class");
                String term = rs.getString("term");
                int amount = rs.getInt("amount");
                Object[] row = {cls, term, amount};
                model.addRow(row);
            }

            feeStructureTable = new JTable(model);


            JTableHeader header = feeStructureTable.getTableHeader();
            header.setOpaque(false);
            header.setBackground(new Color(230, 230, 230));
            header.setForeground(new Color(51, 51, 51));
            header.setBorder(new LineBorder(Color.BLACK));
            feeStructureTable.setTableHeader(header);


            JScrollPane scrollPane = new JScrollPane(feeStructureTable);
            scrollPane.setBorder(new LineBorder(Color.BLACK));

            feeStructurePanel.add(scrollPane);
            add(feeStructurePanel);
            pack();
            setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
