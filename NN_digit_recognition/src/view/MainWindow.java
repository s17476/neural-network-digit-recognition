package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controler.Activable;
import controler.BinaryStepBi;
import controler.BinaryStepUni;
import controler.DataController;
import controler.SigmoidBi;
import controler.SigmoidUni;
import model.Extension;
import model.NeuralNetwork;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
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
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2917054239129869011L;
	
	private NeuralNetwork currentNetwork;
	private JPanel contentPane;
	private JTextField txtMyNeuralNetwork;
	private JTextField textField_2;
	private Extension extension;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public MainWindow() {
		extension = DataController.load();
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\res\\mainIcon.png"));
		setTitle("Neural Network - digit recognition");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		setMinimumSize(new Dimension(700, 600));
		JFrame frame = this;
		JList<NeuralNetwork> list = new JList<>();
		DefaultListModel<NeuralNetwork> listModel = new DefaultListModel<>();
		JLabel currLabel = new JLabel();
		currLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblNewLabel_3 = new JLabel();
		JLabel lblNewLabel_4 = new JLabel();
		JLabel lblNewLabel_5 = new JLabel();
		JLabel lblNewLabel_6 = new JLabel();
		JLabel lblNewLabel_7 = new JLabel();
		JLabel lblNewLabel_29 = new JLabel();
		JLabel lblNewLabel_30 = new JLabel();
		JLabel lblNewLabel_31 = new JLabel();
		JLabel lblNewLabel_32 = new JLabel();
		JLabel lblNewLabel_34 = new JLabel();
		JLabel lblNewLabel_35 = new JLabel();
		JLabel lblNewLabel_36 = new JLabel();
		JLabel lblNewLabel_37 = new JLabel();
		JLabel lblNewLabel_38 = new JLabel();
		JLabel lblNewLabel_39 = new JLabel();
		
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.setModel(listModel);
		
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
		contentPane.setMinimumSize(new Dimension(700, 600));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1251px]", "[33px][][642px,grow,fill]"));
		
		JLabel nameLabel = new JLabel();
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		JLabel inputLabel = new JLabel();
		JLabel hiddenLayLabel = new JLabel();
		JLabel neuronsLabel = new JLabel();
		JLabel outputLabel = new JLabel();
		JLabel learningLabel = new JLabel();
		JLabel functionLabel = new JLabel();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,growx,aligny top");
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8, BorderLayout.WEST);
		
		JButton btnNew = new JButton("");
		btnNew.setIcon(new ImageIcon(new ImageIcon(".\\res\\newIcon.png").getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
		btnNew.setPreferredSize(new Dimension(55, 55));
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Create");
				
			}
		});
		panel_8.add(btnNew);
		
		JButton btnLoad = new JButton("");
		btnLoad.setIcon(new ImageIcon(new ImageIcon(".\\res\\openIcon.png").getImage().getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING)));
		btnLoad.setPreferredSize(new Dimension(55, 55));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Load");
				System.out.println(extension.getExtension());
			}
		});
		panel_8.add(btnLoad);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataController.save(extension);
				String msg = "<html><center><b>Data saved<br>"+
			             "succesfull!<br></center></html>";
			  ImageIcon ico = new ImageIcon(".\\res\\mainIcon.png");
			  JOptionPane.showMessageDialog(frame,
			                                msg,
			                                "INFO",
			                                JOptionPane.WARNING_MESSAGE,
			                                ico
			                                );
			}
		});
		btnSave.setIcon(new ImageIcon(new ImageIcon(".\\res\\saveIcon.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnSave.setPreferredSize(new Dimension(55, 55));
		panel_8.add(btnSave);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JSeparator separator_2 = new JSeparator();
		contentPane.add(separator_2, "cell 0 1,growx,aligny center");
		
		
		contentPane.add(panel_3, "cell 0 2,grow");
		
		
		
		JPanel Intro = new JPanel();
		panel_3.add(Intro, "Image");
		Intro.setLayout(new MigLayout("", "[][grow][]", "[][grow][]"));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(".\\res\\mainIcon.png"));
		Intro.add(lblNewLabel_1, "cell 1 0 1 3,alignx center,aligny center");
		
		JPanel Load = new JPanel();
		panel_3.add(Load, "Load");
		Load.setLayout(new MigLayout("", "[200px,growprio 40,grow][growprio 60,grow]", "[432px,grow][][41px]"));
		
		extension.getExtension().stream().forEach(x -> listModel.addElement(x));

		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				var tmp = (NeuralNetwork)list.getModel().getElementAt(list.getSelectedIndex());
				nameLabel.setText(list.getSelectedValue().toString());
				inputLabel.setText("Input: " + tmp.getInput().length);
				hiddenLayLabel.setText("Number of hidden layers: " + tmp.getHiddenLayers().length);
				neuronsLabel.setText("Neurons per hidden layer: " + tmp.getHiddenLayersSize());
				outputLabel.setText("Output: " + tmp.getOutputLayer());
				learningLabel.setText("Learning rate: " + tmp.getLearningRate());
				functionLabel.setText("Hidden layer(s) activation function: " + tmp.getFunction());
			}
		});
		
		
		Load.add(list, "cell 0 0,grow");
		
		JPanel infoPanel = new JPanel();
		Load.add(infoPanel, "cell 1 0,grow");
		infoPanel.setLayout(new MigLayout("", "[][]", "[][][][][][][][][][][][][]"));
		
		
		infoPanel.add(nameLabel, "cell 1 0");
		
		
		
		infoPanel.add(inputLabel, "cell 1 2");
		
		infoPanel.add(hiddenLayLabel, "cell 1 4");
		
		infoPanel.add(neuronsLabel, "cell 1 6");
		
		infoPanel.add(outputLabel, "cell 1 8");
		
		infoPanel.add(learningLabel, "cell 1 10");
		
		infoPanel.add(functionLabel, "cell 1 12");
		
		JSeparator separator_3 = new JSeparator();
		Load.add(separator_3, "cell 0 1 2 1,growx,aligny center");
		
		JPanel panel_10 = new JPanel();
		Load.add(panel_10, "cell 0 2 2 1,growx,aligny top");
		
		JButton btnCancel_1 = new JButton("Cancel");
		btnCancel_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Image");
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_10.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = list.getSelectedIndex();
					
					
					System.out.println(index + "   " + extension);//////////////////////////////////////////////////////////////////
					if (index >= 0) {
						
						
						extension.remove(index);
						System.out.println("model   " + (NeuralNetwork)listModel.get(index));////////
						var tmpModel = new DefaultListModel<NeuralNetwork>();
						extension.getExtension().stream().forEach(x -> tmpModel.addElement(x));
						list.setModel(tmpModel);
						
					}
				}catch(Exception exc) {}
			}
		});
		btnCancel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_10.add(btnCancel_1);
		
		JButton btnLoad_1 = new JButton("Load");
		btnLoad_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = list.getSelectedIndex();
					if (index >= 0) {
						currentNetwork =  ((NeuralNetwork)listModel.get(index));
						cl.show(panel_3, "Current");
						currLabel.setText("Current neural network: " + currentNetwork.toString());

						lblNewLabel_3.setText("Input: " + currentNetwork.getInput().length);
						
						lblNewLabel_4.setText("Number of hidden layers: " + currentNetwork.getHiddenLayers().length);
						
						lblNewLabel_5.setText("Neurons per hidden layer: " + currentNetwork.getHiddenLayersSize());
						
						lblNewLabel_6.setText("Learning rate: " + currentNetwork.getLearningRate());
						
						lblNewLabel_7.setText("Activation function: " + currentNetwork.getFunction());
						/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						lblNewLabel_29.setText("" + currentNetwork.getStatistics()[0]);
						lblNewLabel_30.setText("" + currentNetwork.getStatistics()[1]);
						lblNewLabel_31.setText("" + currentNetwork.getStatistics()[2]);
						lblNewLabel_32.setText("" + currentNetwork.getStatistics()[3]);
						lblNewLabel_34.setText("" + currentNetwork.getStatistics()[4]);
						lblNewLabel_35.setText("" + currentNetwork.getStatistics()[5]);
						lblNewLabel_36.setText("" + currentNetwork.getStatistics()[6]);
						lblNewLabel_37.setText("" + currentNetwork.getStatistics()[7]);
						lblNewLabel_38.setText("" + currentNetwork.getStatistics()[8]);
						lblNewLabel_39.setText("" + currentNetwork.getStatistics()[9]);
					}
				}catch(Exception exc) {}
				
			}
		});
		btnLoad_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_10.add(btnLoad_1);
		
		JPanel New = new JPanel();
		panel_3.add(New, "Create");
		New.setLayout(new MigLayout("", "[100px,grow 500]", "[79px][547px]"));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EmptyBorder(20, 0, 20, 0));
		New.add(panel_6, "cell 0 0,alignx center,aligny center");
		panel_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCreateNewNeural = new JLabel("Create new neural network");
		panel_6.add(lblCreateNewNeural);
		lblCreateNewNeural.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EmptyBorder(20, 10, 0, 10));
		New.add(panel_7, "cell 0 1,alignx center,growy");
		panel_7.setLayout(new MigLayout("", "[300px][350px,grow]", "[30px][30px][30px][30px][30px][30px][30px][30px][32px]"));
		
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
		textField_2.setText("0.1");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(textField_2, "cell 1 5,grow");
		textField_2.setColumns(10);
		
		JLabel lblHiddenLayersActivation = new JLabel("Hidden layer(s) activation function:");
		lblHiddenLayersActivation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblHiddenLayersActivation, "cell 0 6,grow");
		
		
		Activable[] functions = { new BinaryStepUni(), new BinaryStepBi(), new SigmoidUni(), new SigmoidBi() };
		
		JComboBox comboBox_2 = new JComboBox(functions);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(comboBox_2, "cell 1 6,grow");
		
		JSeparator separator_1 = new JSeparator();
		panel_7.add(separator_1, "cell 0 7 2 1,growx,aligny center");
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_7.add(panel_11, "cell 0 8 2 1,grow");
		
		JButton btnCancel = new JButton("Cancel");
		panel_11.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(panel_3, "Image");
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnCreate = new JButton("Create");
		panel_11.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("learning rate: "+textField_2.getText());
				currentNetwork = new NeuralNetwork(txtMyNeuralNetwork.getText(),
													Integer.parseInt(comboBox.getSelectedItem().toString()),
													Integer.parseInt(comboBox_1.getSelectedItem().toString()),
													new BigDecimal(Double.parseDouble(textField_2.getText())).round(new MathContext(3)),
													(Activable)comboBox_2.getSelectedItem());
				extension.add(currentNetwork);
				DefaultListModel<NeuralNetwork> model = (DefaultListModel<NeuralNetwork>) list.getModel();
				model.addElement(currentNetwork);
				currLabel.setText(currentNetwork.toString());
				cl.show(panel_3, "Current");
				
				currLabel.setText("Current neural network: " + currentNetwork.toString());
				
				lblNewLabel_3.setText("Input: " + currentNetwork.getInput().length);
				
				lblNewLabel_4.setText("Number of hidden layers: " + currentNetwork.getHiddenLayers().length);
				
				lblNewLabel_5.setText("Neurons per hidden layer: " + currentNetwork.getHiddenLayersSize());
				
				lblNewLabel_6.setText("Learning rate: " + currentNetwork.getLearningRate());
				
				lblNewLabel_7.setText("Activation function: " + currentNetwork.getFunction());
				
				lblNewLabel_29.setText("" + currentNetwork.getStatistics()[0]);
				lblNewLabel_30.setText("" + currentNetwork.getStatistics()[1]);
				lblNewLabel_31.setText("" + currentNetwork.getStatistics()[2]);
				lblNewLabel_32.setText("" + currentNetwork.getStatistics()[3]);
				lblNewLabel_34.setText("" + currentNetwork.getStatistics()[4]);
				lblNewLabel_35.setText("" + currentNetwork.getStatistics()[5]);
				lblNewLabel_36.setText("" + currentNetwork.getStatistics()[6]);
				lblNewLabel_37.setText("" + currentNetwork.getStatistics()[7]);
				lblNewLabel_38.setText("" + currentNetwork.getStatistics()[8]);
				lblNewLabel_39.setText("" + currentNetwork.getStatistics()[9]);
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel currentNet = new JPanel();
		panel_3.add(currentNet, "Current");
		currentNet.setLayout(new MigLayout("", "[121.00,left][][][][grow]", "[][][][][][][][][][][][][][][][][][][][][][][]"));
		
		
		currentNet.add(currLabel, "flowx,cell 0 0");
		
		
		currentNet.add(lblNewLabel_3, "cell 0 1,aligny top");
		
		
		currentNet.add(lblNewLabel_4, "cell 0 2,growx,aligny bottom");
		
		
		currentNet.add(lblNewLabel_5, "cell 0 3");
		
		
		currentNet.add(lblNewLabel_6, "cell 0 4");
		
		
		currentNet.add(lblNewLabel_7, "flowx,cell 0 5");
		
		JSeparator separator_5 = new JSeparator();
		currentNet.add(separator_5, "cell 0 6 5 1,growx");
		
		JLabel lblNewLabel_23 = new JLabel("Statistics:");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.PLAIN, 18));
		currentNet.add(lblNewLabel_23, "cell 0 7 4 1,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Success ratio (last run):");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		currentNet.add(lblNewLabel_2, "cell 0 8");
		
		JLabel lblNewLabel_24 = new JLabel("New label");
		currentNet.add(lblNewLabel_24, "cell 1 8");
		
		JLabel lblNewLabel_18 = new JLabel("New label");
		currentNet.add(lblNewLabel_18, "cell 3 8");
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		currentNet.add(lblNewLabel_13, "cell 3 9");
		
		JLabel lblNewLabel_9 = new JLabel("0 - ");
		currentNet.add(lblNewLabel_9, "cell 0 10");
		

		currentNet.add(lblNewLabel_29, "cell 1 10");
		
		JLabel lblNewLabel_14 = new JLabel("New label");
		currentNet.add(lblNewLabel_14, "cell 3 10");
		
		JLabel lblNewLabel_10 = new JLabel("1 -");
		currentNet.add(lblNewLabel_10, "cell 0 11");
		
		
		currentNet.add(lblNewLabel_30, "cell 1 11");
		
		JLabel lblNewLabel_15 = new JLabel("New label");
		currentNet.add(lblNewLabel_15, "cell 3 11");
		
		JLabel lblNewLabel_11 = new JLabel("2 -");
		currentNet.add(lblNewLabel_11, "cell 0 12");
		
		
		currentNet.add(lblNewLabel_31, "cell 1 12");
		
		JLabel lblNewLabel_16 = new JLabel("New label");
		currentNet.add(lblNewLabel_16, "cell 3 12");
		
		JLabel lblNewLabel_12 = new JLabel("3 -");
		currentNet.add(lblNewLabel_12, "cell 0 13");
		

		currentNet.add(lblNewLabel_32, "cell 1 13");
		
		JLabel lblNewLabel_17 = new JLabel("New label");
		currentNet.add(lblNewLabel_17, "cell 3 13");
		
		JLabel lblNewLabel_33 = new JLabel("4 -");
		currentNet.add(lblNewLabel_33, "cell 0 14");
		
		
		currentNet.add(lblNewLabel_34, "cell 1 14");
		
		JLabel lblNewLabel_19 = new JLabel("New label");
		currentNet.add(lblNewLabel_19, "cell 3 14");
		
		JLabel lblNewLabel_8 = new JLabel("5 -");
		currentNet.add(lblNewLabel_8, "cell 0 15");
		
		
		currentNet.add(lblNewLabel_35, "cell 1 15");
		
		JLabel lblNewLabel_20 = new JLabel("New label");
		currentNet.add(lblNewLabel_20, "cell 3 15");
		
		JLabel lblNewLabel_25 = new JLabel("6 -");
		currentNet.add(lblNewLabel_25, "cell 0 16");
		
		
		currentNet.add(lblNewLabel_36, "cell 1 16");
		
		JLabel lblNewLabel_21 = new JLabel("New label");
		currentNet.add(lblNewLabel_21, "cell 3 16");
		
		JLabel lblNewLabel_26 = new JLabel("7 -");
		currentNet.add(lblNewLabel_26, "cell 0 17");
		
		
		currentNet.add(lblNewLabel_37, "cell 1 17");
		
		JLabel lblNewLabel_22 = new JLabel("New label");
		currentNet.add(lblNewLabel_22, "cell 3 17");
		
		JLabel lblNewLabel_27 = new JLabel("8 -");
		currentNet.add(lblNewLabel_27, "cell 0 18");
		
		
		currentNet.add(lblNewLabel_38, "cell 1 18");
		
		JLabel lblNewLabel_28 = new JLabel("9 -");
		currentNet.add(lblNewLabel_28, "cell 0 19");
		
		
		currentNet.add(lblNewLabel_39, "cell 1 19");
		
		JSeparator separator_4 = new JSeparator();
		currentNet.add(separator_4, "cell 0 21 5 1,growx");
		
		JPanel panel_1 = new JPanel();
		currentNet.add(panel_1, "flowx,cell 4 22,alignx right");
		
		JLabel lblNewLabel_40 = new JLabel("Teaching cycles:");
		panel_1.add(lblNewLabel_40);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Teach");///////////////////////////////////////////////////////////////////////       UCZENIE
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cycles = 0;
				try {
					cycles = Integer.parseInt(textField.getText());
					
				}catch(Exception exc) {
					JOptionPane.showMessageDialog(frame,
                        "Type number of teaching cycles",
                        "Wrong number",
                        JOptionPane.WARNING_MESSAGE,
                        null
                        );}
				if(cycles > 0) currentNetwork.recognize(cycles);
				
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Test");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentNetwork.recognize(true);
			}
		});
		panel_1.add(btnNewButton_1);
		

	}

}
