package group187.hotel.ui;

import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.*;
import group187.hotel.business.Hotel;
import group187.hotel.business.*;

import java.awt.event.*;

import java.io.IOException;
import java.util.*;
import java.time.temporal.ChronoUnit;

public class GUIViewController extends JFrame implements Observer {

	private JPanel contentPane;
	private JPanel resultPanel;
	private JTextArea resultText;
	private JPanel getEmailPanel;
	private JTextField email;
	private Hotel model;
	private static final long serialVersionUID = 42031768871L;
	

	public GUIViewController(Hotel model) {
		if (model == null)
			throw new IllegalArgumentException("The model cannot be null"); // Just for validation
		this.model = model;
		this.model.addObserver(this);
		setResizable(false);
		setTitle("Dawson Hotel");


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(getTitlePanel(), BorderLayout.NORTH);
		contentPane.add(getCenterPanel(), BorderLayout.CENTER);
		contentPane.add(getBottomPanel(), BorderLayout.SOUTH);
		this.setVisible(true);
	}

	private JPanel getTitlePanel() {
		JPanel titlePanel = new JPanel();
		JLabel lblDawsonHotelInfo = new JLabel("Dawson Hotel - Information Desk");
		lblDawsonHotelInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		titlePanel.add(lblDawsonHotelInfo);
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		return titlePanel;
	}


	private JPanel getCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel dataEntry = new JPanel();
		centerPanel.add(dataEntry);

		getEmailPanel = new JPanel();
		dataEntry.add(getEmailPanel);
		getEmailPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblEmail = new JLabel("Enter email address: ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		getEmailPanel.add(lblEmail, BorderLayout.WEST);

		email = new JTextField();
		getEmailPanel.add(email);
		email.setColumns(25);

		dataEntry.add(getButtonPanel());

		resultPanel = new JPanel();
		centerPanel.add(resultPanel);
		resultText = new JTextArea();
		resultPanel.add(resultText);

		return centerPanel;
	}

	private JPanel getButtonPanel() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JButton custInfo = new JButton("Customer Info");
		buttonPanel.add(custInfo);
		
		JButton resInfo = new JButton("Reservation Info");
		buttonPanel.add(resInfo);

		custInfo.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				Customer custObj = null;
				try{
					custObj = model.findCustomer(email.getText());
				}
				catch(NonExistingCustomerException a){
					JOptionPane.showMessageDialog(GUIViewController.this, "Error! The customer with the email " + email.getText() + " is not in the database", 
							"Error!", JOptionPane.WARNING_MESSAGE);
				}
				String output = "Customer details:\n" + custObj.getName().getFirstName() + " "
						+ custObj.getName().getLastName() + " at " + custObj.getEmail().toString() + "\n"
						+ "Credit card on file: " + custObj.getCreditCard().get().getType().toString() + " " + custObj.getCreditCard().get().getNumber();
				update(model, output);
			} 
			}
		);
		
		resInfo.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e)
			{
				List<Reservation> resObj = null;
				Customer custObj = null;
				String output = null;
				try{
					custObj = model.findCustomer(email.getText());
					resObj = model.findReservations(custObj);
				}
				catch(NonExistingCustomerException a){
					JOptionPane.showMessageDialog(GUIViewController.this, "Error! The customer with the email " + email.getText() + " is not in the database", 
							"Error!", JOptionPane.WARNING_MESSAGE);
				}
				

				output = "Reservation details:\nReservations for: " + custObj.getName().toString() + "\n";
				for (Reservation res : resObj)
					output += res.getRoom().getRoomNumber() + " checking in on " + res.getCheckInDate() + " for " + ChronoUnit.DAYS.between(res.getCheckInDate(), res.getCheckOutDate()) + " days\n";
				
				update(model, output);
			} 
			}
		);

		return buttonPanel;
	}

	private JPanel getBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		bottomPanel.add(panel);

		JButton exit = new JButton("Exit");
		panel.add(exit);

		return bottomPanel;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		resultPanel.setVisible(true);
		resultText.setText(arg1.toString());
		
	}

}
