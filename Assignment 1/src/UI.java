import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class UI extends JFrame implements ActionListener {

	Statement stmt;
	PreparedStatement pst = null;
	ResultSet rs;
	String sql;
	Connection con;
	int i = 0;

	// Text fields
	JTextField ssnField, dobField, nameField, addressField, salaryField, genderField;

	// label
	JLabel ssnJLabel, dobIDJLabel, nameJLabel, addressJLabel, salaryJLabel, genderJLabel;
	// header
	JLabel header;

	// buttons
	JButton addButton, deleteButton, updateButton, showButton;
	JButton prevButton, nextButton, clearButton;

	public UI() {
		ssnJLabel = new JLabel("SSn");
		dobIDJLabel = new JLabel("DOB");
		nameJLabel = new JLabel("Name");
		addressJLabel = new JLabel("Address");
		salaryJLabel = new JLabel("Salary");
		genderJLabel = new JLabel("Gender");
		header = new JLabel("EMPLOYEE DETAILS");

		ssnField = new JTextField();
		dobField = new JTextField();
		nameField = new JTextField();
		addressField = new JTextField();
		salaryField = new JTextField();
		genderField = new JTextField();

		addButton = new JButton("Add");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		clearButton = new JButton("Clear");
		prevButton = new JButton("Previous");
		nextButton = new JButton("Next");
		showButton = new JButton("Show Employee Details");

		// header
		this.add(header).setBounds(120, 50, 500, 50);
		header.setFont(new Font("Serif", Font.BOLD, 20));

		// create a line border with the specified color and width
		Border border = BorderFactory.createLineBorder(Color.black, 3);

		// set the borders
		ssnField.setBorder(border);
		dobField.setBorder(border);
		nameField.setBorder(border);
		addressField.setBorder(border);
		salaryField.setBorder(border);
		genderField.setBorder(border);

		// Labels and field and positions
		this.add(ssnJLabel).setBounds(50, 120, 50, 50);
		this.add(ssnField).setBounds(125, 135, 200, 25);

		this.add(dobIDJLabel).setBounds(50, 150, 150, 50);
		this.add(dobField).setBounds(125, 165, 200, 25);

		this.add(nameJLabel).setBounds(50, 198, 150, 50);
		this.add(nameField).setBounds(125, 210, 200, 25);

		this.add(addressJLabel).setBounds(50, 225, 150, 50);
		this.add(addressField).setBounds(125, 240, 200, 25);

		this.add(salaryJLabel).setBounds(50, 270, 150, 50);
		this.add(salaryField).setBounds(125, 285, 200, 25);

		this.add(genderJLabel).setBounds(50, 300, 150, 50);
		this.add(genderField).setBounds(125, 315, 200, 25);

		// font size for labels
		ssnJLabel.setFont(new Font("Serif", Font.BOLD, 18));
		dobIDJLabel.setFont(new Font("Serif", Font.BOLD, 18));
		nameJLabel.setFont(new Font("Serif", Font.BOLD, 18));
		addressJLabel.setFont(new Font("Serif", Font.BOLD, 18));
		salaryJLabel.setFont(new Font("Serif", Font.BOLD, 18));
		genderJLabel.setFont(new Font("Serif", Font.BOLD, 18));

		// fields
		this.add(ssnField);
		this.add(dobField);
		this.add(nameField);
		this.add(addressField);
		this.add(salaryField);
		this.add(genderField);

		// buttons
		// add button
		this.add(addButton);
		addButton.setBounds(100, 400, 60, 30);
		addButton.addActionListener(this);
		addButton.setForeground(Color.BLACK);
		addButton.setBackground(Color.green);

		// delete button
		this.add(deleteButton);
		deleteButton.setBounds(170, 400, 80, 30);
		deleteButton.addActionListener(this);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setBackground(Color.RED);

		// update button
		this.add(updateButton);
		updateButton.setBounds(260, 400, 80, 30);
		updateButton.addActionListener(this);
		updateButton.setBackground(Color.yellow);
		updateButton.setForeground(Color.BLACK);

		// show button
		this.add(showButton);
		showButton.setBounds(360, 400, 200, 30);
		showButton.addActionListener(this);
		showButton.setBackground(Color.cyan);
		showButton.setForeground(Color.BLACK);

		// clear button
		this.add(clearButton);
		clearButton.setBounds(400, 315, 100, 30);
		clearButton.addActionListener(this);
		clearButton.setBackground(Color.WHITE);
		clearButton.setForeground(Color.BLACK);

		// prev button
		this.add(prevButton);
		prevButton.setBounds(400, 150, 100, 30);
		prevButton.addActionListener(this);
		prevButton.setBackground(Color.WHITE);
		prevButton.setForeground(Color.BLACK);

		// next button
		this.add(nextButton);
		nextButton.setBounds(400, 200, 100, 30);
		nextButton.addActionListener(this);
		nextButton.setBackground(Color.WHITE);
		nextButton.setForeground(Color.BLACK);

		// size of the layout
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setSize(600, 500);
		this.setResizable(false);
		this.setVisible(true);
	}

	{
		try {
			// String url = "jdbc:mysql://localhost:3308/test";
			// localhost:3308 was not working
			String url = "jdbc:mysql://localhost:3306/test";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			System.out.println("Connected to database");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "error in connection to Database");
		}
	}

	// bind a list with ResultSet
	public ArrayList<Employees> BindList() {
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employees");
			ArrayList<Employees> list = new ArrayList<Employees>();
			while (rs.next()) {
				Employees e = new Employees(rs.getString("ssn"), Integer.parseInt(rs.getString("dob")),
						rs.getString("name"), rs.getString("address"), rs.getString("salary"), rs.getString("gender"));
				list.add(e);
			}return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		}return null;
	}

	// Showing the employee details in jtexfields
	public void ShowInfo(int index) {
		ssnField.setText(BindList().get(index).getSsn());
		dobField.setText(Integer.toString(BindList().get(index).getDob()));
		nameField.setText(BindList().get(index).getName());
		addressField.setText(BindList().get(index).getAddress());
		salaryField.setText(BindList().get(index).getSalary());
		genderField.setText(BindList().get(index).getGender());
	}

	// this method will clear all the fields
	public void clearTextField() {
		ssnField.setText("");
		dobField.setText("");
		nameField.setText("");
		addressField.setText("");
		salaryField.setText("");
		genderField.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent x) {
		// addButton save new employee details
		if (x.getSource() == addButton) {
			try {
				stmt = con.createStatement();
				sql = "insert into employees (ssn,dob,name,address,salary,gender) values('" + ssnField.getText() + "','"
						+ dobField.getText() + "','" + nameField.getText() + "','" + addressField.getText() + "','"
						+ salaryField.getText() + "','" + genderField.getText() + "')";
				stmt.executeUpdate(sql);
				System.out.println("New record added");
				JOptionPane.showMessageDialog(this, "New employee has been added", "Employees",
						JOptionPane.PLAIN_MESSAGE);
				clearTextField();
			} catch (Exception ex) {
				System.out.println(ex);
				JOptionPane.showMessageDialog(this, "Please make sure that all the fields have been entered.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			// deleteButton
		} else if (x.getSource() == deleteButton) {
			try {
				sql = "delete from employees where ssn=?";
				pst = con.prepareStatement(sql);
				pst.setString(1, ssnField.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(this, "delete successfully");
				clearTextField();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"An Error has hapened please make sure you have the correct SSn and try again", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
			
			// update button
		} else if (x.getSource() == updateButton) {
			try {
				stmt = con.createStatement();
//				sql = "update employees set dob = '" + dobField.getText() + "',name ='" + nameField.getText()
//						+ "',address='" + addressField.getText() + "',salary='" + salaryField.getText() + "',gender= '"
//						+ genderField.getText() + "where ssn = " + ssnField.getText();
				sql = "update employees set dob =?, name =? ,address =? ,salary=? ,gender=? where ssn =?";
				pst = con.prepareStatement(sql);
				pst.setString(6, ssnField.getText());
				pst.setString(1, dobField.getText());
				pst.setString(2, nameField.getText());
				pst.setString(3, addressField.getText());
				pst.setString(4, salaryField.getText());
				pst.setString(5, genderField.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(this, "Updated successfully");
				System.out.println();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this,
						"An Error has hapened please make sure you have the correct SSn and try again", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}
			// show button
		} else if (x.getSource() == showButton) {
			i = 0; 
			ShowInfo(i);

			// nextButton
		} else if (x.getSource() == nextButton) {
			i++;
			if (i < BindList().size()) { ShowInfo(i);} 
			else {i = BindList().size() - 1;
				ShowInfo(i);}
			// prevButton
		} else if (x.getSource() == prevButton) {
			try {i--;
				if (i > 0) { ShowInfo(i);} 
				else { i = 0;ShowInfo(i);}
			} catch (Exception e5) {
				System.out.println(e5);
			}
			
			// clear fields
		} else if (x.getSource() == clearButton) {
			try {
				clearTextField();
			} catch (Exception e5) {
				JOptionPane.showMessageDialog(this, "There is no Employee details loaded", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(e5);
			}
		}

	}

	public static void main(String[] args) {
		UI app = new UI();
		app.getContentPane().setBackground(Color.WHITE);
	}
}
