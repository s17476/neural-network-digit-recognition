package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.Activable;
import controler.BinaryStepBi;
import controler.BinaryStepUni;
import controler.SigmoidBi;
import controler.SigmoidUni;
import model.NeuralNetwork;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;
import javax.swing.JInternalFrame;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2917054239129869011L;
	
	private NeuralNetwork currentNetwork;
	private JPanel contentPane;
	private JTextField txtMyNeuralNetwork;
	private JTextField textField_2;


	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\res\\mainIcon.png"));
		setTitle("Neural Network - digit recognition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1273, 748);
		setMinimumSize(new Dimension(500, 400));
		JFrame frame = this;
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnFile);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new CardLayout());
		
		CardLayout cl = (CardLayout)panel_3.getLayout();
		
		JMenuItem mntmCreateNewNeural = new JMenuItem("Create new neural network");
		mntmCreateNewNeural.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Create");
			}
		});
		mnFile.add(mntmCreateNewNeural);
		
		JMenuItem mntmOpen = new JMenuItem("Load");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3,  "Load");
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmClose);
		
		JMenu Help = new JMenu("Help");
		Help.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(Help);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		Help.add(mntmHelp);
		
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String msg = "<html><center><b>Digit recognition<br>"+
				             "<font color=blue>Polish Japanese Academy of Information Technology</font><br>" +
				             "Author: Grzegorz Frączek s17476<br>"
				             + "s17476@pjwstk.edu.pl</b></center></html>";
				  ImageIcon ico = new ImageIcon(".\\res\\mainIcon.png");
				  JOptionPane.showMessageDialog(frame,
				                                msg,
				                                "INFO",
				                                JOptionPane.WARNING_MESSAGE,
				                                ico
				                                );
			}
		});
		Help.add(mntmInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8, BorderLayout.WEST);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Create");
			}
		});
		panel_8.add(btnNew);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Load");
			}
		});
		panel_8.add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		panel_8.add(btnSave);
		
		JButton btnDelete = new JButton("Delete");
		panel_8.add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setResizeWeight(0.5);
		
		splitPane.setLeftComponent(panel_3);
		
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, "Image");
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(".\\res\\mainIcon.png"));
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panel_3.add(panel_9, "Load");
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JList list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_9.add(list, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10, BorderLayout.SOUTH);
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Image");
			}
		});
		btnCancel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_10.add(btnCancel_1);
		
		JButton btnLoad_1 = new JButton("Load");
		btnLoad_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_10.add(btnLoad_1);
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, "Create");
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(20, 0, 20, 0));
		panel_5.add(panel_6, BorderLayout.NORTH);
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCreateNewNeural = new JLabel("Create new neural network");
		panel_6.add(lblCreateNewNeural);
		lblCreateNewNeural.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EmptyBorder(40, 20, 20, 20));
		panel_5.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new MigLayout("", "[426px][426px]", "[62px][62px][62px][62px][62px][62px][62px][62px]"));
		
		JLabel lblNeuralNetworkName = new JLabel("Neural network name:");
		lblNeuralNetworkName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblNeuralNetworkName, "cell 0 0,grow");
		
		txtMyNeuralNetwork = new JTextField();
		txtMyNeuralNetwork.setText("My neural network");
		txtMyNeuralNetwork.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(txtMyNeuralNetwork, "cell 1 0,grow");
		txtMyNeuralNetwork.setColumns(10);
		
		JLabel lblInput = new JLabel("Input:");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblInput, "cell 0 1,grow");
		
		JLabel lblNewLabel = new JLabel("16");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblNewLabel, "cell 1 1,grow");
		
		JLabel lblNumberOfHidden = new JLabel("Number of hidden layers:");
		lblNumberOfHidden.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblNumberOfHidden, "cell 0 2,growx");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		panel_7.add(comboBox, "cell 1 2,grow");
		
		JLabel lblNeuronsPerHidden = new JLabel("Neurons per hidden layer:");
		lblNeuronsPerHidden.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblNeuronsPerHidden, "cell 0 3,grow");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		panel_7.add(comboBox_1, "cell 1 3,grow");
		
		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOutput.setHorizontalAlignment(SwingConstants.LEFT);
		panel_7.add(lblOutput, "cell 0 4,grow");
		
		JLabel label = new JLabel("10");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(label, "cell 1 4,grow");
		
		JLabel lblLearningRate = new JLabel("Learning rate:");
		lblLearningRate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblLearningRate, "cell 0 5,grow");
		
		textField_2 = new JTextField();
		textField_2.setText("0,1");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(textField_2, "cell 1 5,grow");
		textField_2.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Image");
			}
		});
		
		JLabel lblHiddenLayersActivation = new JLabel("Hidden layer(s) activation function:");
		lblHiddenLayersActivation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblHiddenLayersActivation, "cell 0 6,grow");
		
		
		Activable[] functions = { new BinaryStepUni(), new BinaryStepBi(), new SigmoidUni(), new SigmoidBi() };
		
		JComboBox comboBox_2 = new JComboBox(functions);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(comboBox_2, "cell 1 6,grow");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(btnCancel, "cell 0 7,grow");
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentNetwork = new NeuralNetwork(txtMyNeuralNetwork.getText(), Integer.parseInt(comboBox_1.getSelectedItem().toString()));
				System.out.println(Integer.parseInt(comboBox_1.getSelectedItem().toString()));
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(btnCreate, "cell 1 7,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.SOUTH);
		

	}

}
