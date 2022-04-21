package com.fredi.graphique;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Form extends JFrame {

	private JPanel contentPane;
	private JTextField textEmail;
	private JLabel lblKilomtres;
	private JTextField textKm;
	private JLabel lblNewLabel_2;
	private JTextField textPeage;
	private JLabel lblNewLabel_3;
	private JTextField textRepas;
	private JLabel lblNewLabel_4;
	private JTextField textHebergement;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void recherche(String mail) {

		Connection conn = null;
		// on se connecte à la base de données
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/b_fredi?useSSL=false&useLegacyDatetimeCode=false", "root", "");

			// Création de la connexion
			Statement stmt1 = conn.createStatement();

			String requeteSQL = "select * from lignes_frais where ADRESSE_MAIL = ?";

			// On prépare la requête (comme en php)
			PreparedStatement prep1 = conn.prepareStatement(requeteSQL);

			// On assigne aux différents "?" des valeurs
			prep1.setString(1, mail);

			// On execute la requête et ferme la connexion
			ResultSet res = prep1.executeQuery();
			while (res.next()) {
				lblNewLabel_1.setText("Trajet concerné : " + res.getString(4));
				lblNewLabel_5.setText("Date du frais : " + res.getString(2));
				textKm.setText(res.getString(5));
				textPeage.setText(res.getString(6));
				textRepas.setText(res.getString(7));
				textHebergement.setText(res.getString(8));
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void validation() {

		ResultSet res;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/b_fredi?useSSL=false&useLegacyDatetimeCode=false", "root", "");

			Statement stmt1 = conn.createStatement();

			String requeteSQL = "update lignes_frais set KM_VALIDE = ?, PEAGE_VALIDE = ?, REPAS_VALIDE = ?, HERBERGEMENT_VALIDE = ? where ADRESSE_MAIL = ?";

			PreparedStatement prep1 = conn.prepareStatement(requeteSQL);

			prep1.setString(1, textKm.getText());
			prep1.setString(2, textPeage.getText());
			prep1.setString(3, textRepas.getText());
			prep1.setString(4, textHebergement.getText());
			prep1.setString(5, textEmail.getText());
			prep1.executeUpdate();
			prep1.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			textKm.setText("");
			textPeage.setText("");
			textRepas.setText("");
			textHebergement.setText("");
			textHebergement.setText("");
			textEmail.setText("");
			lblNewLabel_1.setText("");
			lblNewLabel_5.setText("");
		}

	}

	public void annuler() {
		textKm.setText("");
		textPeage.setText("");
		textRepas.setText("");
		textHebergement.setText("");
		textHebergement.setText("");
		textEmail.setText("");
		lblNewLabel_1.setText("");
		lblNewLabel_5.setText("");
	}

	/**
	 * Create the frame.
	 */
	public Form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 150, 430, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textEmail = new JTextField();
		textEmail.setBounds(24, 96, 316, 30);
		contentPane.add(textEmail);
		textEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Adresse mail demandeur");
		lblNewLabel.setBounds(24, 80, 149, 14);
		contentPane.add(lblNewLabel);

		// button research
		JButton btnNewButton = new JButton("\uD83D\uDD0E");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Appel de la fonction pour remplir les champs via une recherche par mail
				String emailEntre = textEmail.getText();
				recherche(emailEntre);

			}
		});
		btnNewButton.setBounds(342, 95, 51, 30);
		contentPane.add(btnNewButton);

		lblKilomtres = new JLabel("Kilomètres");
		lblKilomtres.setBounds(25, 206, 149, 14);
		contentPane.add(lblKilomtres);

		textKm = new JTextField();
		textKm.setColumns(10);
		textKm.setBounds(25, 222, 368, 30);
		contentPane.add(textKm);

		lblNewLabel_2 = new JLabel("Péage");
		lblNewLabel_2.setBounds(25, 263, 149, 14);
		contentPane.add(lblNewLabel_2);

		textPeage = new JTextField();
		textPeage.setColumns(10);
		textPeage.setBounds(25, 279, 368, 30);
		contentPane.add(textPeage);

		lblNewLabel_3 = new JLabel("Repas");
		lblNewLabel_3.setBounds(25, 320, 149, 14);
		contentPane.add(lblNewLabel_3);

		textRepas = new JTextField();
		textRepas.setColumns(10);
		textRepas.setBounds(25, 336, 368, 30);
		contentPane.add(textRepas);

		lblNewLabel_4 = new JLabel("Hébergement");
		lblNewLabel_4.setBounds(25, 377, 149, 14);
		contentPane.add(lblNewLabel_4);

		textHebergement = new JTextField();
		textHebergement.setColumns(10);
		textHebergement.setBounds(25, 393, 368, 30);
		contentPane.add(textHebergement);

		lblNewLabel_1 = new JLabel("Trajet concerné :");
		lblNewLabel_1.setBounds(24, 157, 369, 14);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_5 = new JLabel("Date du frais :");
		lblNewLabel_5.setBounds(24, 181, 369, 14);
		contentPane.add(lblNewLabel_5);

		btnNewButton_1 = new JButton("Valider");
		btnNewButton_1.setBounds(231, 458, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				validation();

			}
		});

		btnNewButton_2 = new JButton("Refuser");
		btnNewButton_2.setBounds(98, 458, 89, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				annuler();

			}
		});
	}
}
