import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;
import java.awt.event.ActionEvent;

public class EqualTotalPayments extends JFrame implements ActionListener {

	private JPanel EqualTotalPayments;
	private JButton Calculate;
	private JButton Clear;
	JTextField Rate, Amount, NoOfMonths, Results;
	JTable table;
	JScrollPane sp;
	DefaultTableModel tm;
	TableColumnModel tcm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EqualTotalPayments frame = new EqualTotalPayments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EqualTotalPayments() {
		setTitle("LOAN AMORTIZATION SCHEDDULE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 350);
		EqualTotalPayments = new JPanel();
		EqualTotalPayments.setForeground(Color.WHITE);
		EqualTotalPayments.setBackground(new Color(248, 248, 255));
		EqualTotalPayments.setBorder(new EmptyBorder(5, 5, 5, 5));
		EqualTotalPayments.setLayout(null);
		setContentPane(EqualTotalPayments);

		JPanel PR = new JPanel();
		PR.add(new JLabel("Enter the Interest Rate:"));
		PR.setBounds(20,20, 200, 40);
		JPanel PA = new JPanel();
		PA.add(new JLabel("Enter the Loan Amount:"));
		PA.setBounds(20, 100, 200, 40);
		JPanel PN = new JPanel();
		PN.add(new JLabel("Enter the Loan Period (In Months):"));
		PN.setBounds(20, 180, 200, 40);
		PR.setBackground(new Color(248, 248, 255));
		PN.setBackground(new Color(248, 248, 255));
		PA.setBackground(new Color(248, 248, 255));

		EqualTotalPayments.add(PA);
		EqualTotalPayments.add(PN);
		EqualTotalPayments.add(PR);

		Rate = new JTextField();
		Amount = new JTextField();
		NoOfMonths = new JTextField();

		tm = new DefaultTableModel();
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		Rectangle Rect = table.getCellRect(-1, 5, false);
		table.scrollRectToVisible(Rect);
		sp = new JScrollPane(table);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setBorder(BorderFactory.createEmptyBorder());
		sp.getViewport().setBackground(Color.WHITE);
		sp.setBounds(420, 0, 365, 310);
		Rate.setBounds(250, 20, 80, 40);
		Amount.setBounds(250, 100, 150, 40);
		NoOfMonths.setBounds(250, 180, 80, 40);

		EqualTotalPayments.add(NoOfMonths);
		EqualTotalPayments.add(Amount);
		EqualTotalPayments.add(Rate);

		Calculate = new JButton("Calculate");
		Clear = new JButton("Clear");
		Calculate.addActionListener(this);
		Clear.addActionListener(this);

		Calculate.setBounds(250, 250, 150, 40);
		Clear.setBounds(100, 250, 80, 40);

		EqualTotalPayments.add(Clear);
		EqualTotalPayments.add(Calculate);
		EqualTotalPayments.add(sp);
	}

 
		public void actionPerformed(ActionEvent e) {

			double RA = Double.parseDouble(Rate.getText());
			double A = Double.parseDouble(Amount.getText());
			double N = Double.parseDouble(NoOfMonths.getText());
			double n = N - (N - 1);
			double R = (RA / 100) / 12.0;

			tm.addColumn("Period");
			tm.addColumn("Paymnt Amt");
			tm.addColumn("PP Amnt");
			tm.addColumn("Int Amnt");
			tm.addColumn("Outs. Balance");

			for (n = 1; n <= N; n++) {

				double P = ((R * A) / (1 - Math.pow(1 + R, -N)));
				double PP = P * (Math.pow(1 + R, -(1 + N - n)));
				double INT = P - PP;
				double OB = (INT / R) - PP;
				
				

				tm.addRow(new Object[] { n, P, PP, INT, OB });

				while (OB <= 0) {
				}

			}

			if (e.getSource() == Calculate) {

				table.setModel(tm);
				table.getModel();
			}
			if (e.getSource() == Clear) {

				Rate.setText("00");
				NoOfMonths.setText("00");
				Amount.setText("00");
				tm.setRowCount(0);
				tm.setColumnCount(0);
			}

		}

}
