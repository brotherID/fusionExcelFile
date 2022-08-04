package dev.procheck.excel.fusion.fusion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ViewFusion extends JFrame {

	private JPanel contentPane;

	private JTextField textField;
	
	private  boolean isExecuted = false;
	
	public static void main(String[] args) {
		SpringApplication.run(ViewFusion.class, args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFusion frame = new ViewFusion();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setBounds(100, 100, 450, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.getContentPane().setLayout(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public ViewFusion () {
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Path in :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(23, 111, 64, 21);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(83, 111, 236, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("start processus");
		lblNewLabel_2.setBounds(160, 205, 111, 14);
		//lblNewLabel_2.setText("start processus");
		lblNewLabel_2.setVisible(true);
		getContentPane().add(lblNewLabel_2);
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isExecuted = true;
				String path =textField.getText();
				try {
					FusionExcelWithHSSF.fusionExcel(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				lblNewLabel_2.setText("end processus");
				lblNewLabel_2.setVisible(true);
				
			}
		});
		
		
		btnNewButton.setBounds(160, 162, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Fusion Excel");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(142, 51, 129, 29);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choose = new JFileChooser();
				int res = choose.showOpenDialog(null);

				if (res == JFileChooser.APPROVE_OPTION) {

					textField.setText("" + choose.getSelectedFile());
					File file = choose.getSelectedFile();
					System.out.println(file.getAbsolutePath());
				}
			}
			
		});
		btnNewButton_1.setBounds(335, 110, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		
	}
}