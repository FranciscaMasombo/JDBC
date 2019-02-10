package lab2;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class GUIDatabase2 extends JFrame implements ActionListener {
	Statement stmt;
	ResultSet rs;
	String sql;
	Connection con;
	int i = 0;

	JButton BtnNext;
	JButton BtnPrev;
	JTextField TxtAddr;
	JTextField TxtRNo;
	JTextField TxtName;
	JLabel jLabel1;
	JLabel jLabel2;
	JLabel jLabel3;
	
	
	public GUIDatabase2()
 {
 jLabel1 = new JLabel("Roll no");
 jLabel2 = new JLabel("FName");
 jLabel3 = new JLabel("Course");
 TxtRNo = new JTextField();
 TxtName = new JTextField();
 TxtAddr = new JTextField();
 BtnPrev = new JButton("Previous");
 BtnNext = new JButton("Next");
 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 this.setLayout(null);
 this.setSize(500,450);
 this.add(jLabel1);
 jLabel1.setBounds(50, 100, 100, 40);
 jLabel1.setForeground(Color.green);
		 this.add(jLabel2);
 jLabel2.setBounds(50, 150, 100, 40);
 jLabel2.setForeground(Color.green);
 this.add(jLabel3);
 jLabel3.setBounds(50, 200, 100, 40);
 jLabel3.setForeground(Color.green);
 this.add(TxtRNo);
 TxtRNo.setBounds(110, 100, 300, 40);
 this.add(TxtName);
 TxtName.setBounds(110, 150, 300, 40);
 this.add(TxtAddr);
 TxtAddr.setBounds(110, 200, 300, 40);
 this.add(BtnPrev);
 BtnPrev.setBounds(50,320,100,50);
 this.add(BtnNext);
 BtnNext.setBounds(200,320,100,50);
 BtnPrev.addActionListener(this);
 BtnNext.addActionListener(this);
 BtnNext.setBackground(Color.black);
BtnNext.setForeground(Color.white);
BtnPrev.setBackground(Color.black);
BtnPrev.setForeground(Color.white);
this.setVisible(true);
 }

	{
		try {
			String url = "jdbc:mysql://localhost:3308/distribsys2019";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/distribsys2019", "root", "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "error in connection");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == BtnNext) {
			i++;
			System.out.println(i);
			try {
				stmt = con.createStatement();
				sql = "select * from students where rno=" + i;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					TxtRNo.setText(rs.getString(1));
					TxtName.setText(rs.getString(2));
					TxtAddr.setText(rs.getString(3));
				}
			} catch (Exception e4) {
				System.out.println(e4);
			}
		} else if (e.getSource() == BtnPrev) {
			if (i <= 0) {
				i = 0;
			} else {
				i--;
			}
			try {
				sql = "select * from students where rno=" + i;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					TxtRNo.setText(rs.getString(1));
					TxtName.setText(rs.getString(2));
					TxtAddr.setText(rs.getString(3));
				}
			} catch (Exception e5) {
				System.out.println(e5);
			}
		}
	}

	public static void main(String[] args) {
		GUIDatabase2 demo = new GUIDatabase2();
		// .setBackground(Color.black);
		demo.getContentPane().setBackground(Color.BLACK);
	}
}
