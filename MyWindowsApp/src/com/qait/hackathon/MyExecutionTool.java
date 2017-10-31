package com.qait.hackathon;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.testng.annotations.Test;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;


public class MyExecutionTool implements ActionListener {

	private JFrame frame;
	private JFrame frameXML;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	//private final Component cardLayout = new CardLayout();
	private JLabel Browser;
	private JComboBox dropDownBrowser;
	private JTextField txtSeleniumHost;
	private JTextField textTier;
	private JTextField driverPath;
	private JRadioButton radioScreenShotOptionYes;
	private JTextField txtScreenshotFolderPath;
	private JLabel Tier;
	private JRadioButton radioScreenShotOptionNo;
	ButtonGroup scrrenShot;
	JButton btnDone;
	Properties configProperties = new Properties();
	String event = "";
	ConfigWriter config = new ConfigWriter();
	public String browserListener;
	public String tierListener;
	public String timeoutListener;
	public String seleniumserverhostListener;
	public String seleniumserverListener;
	public String driverpathListener;
	public String takescreenshotListener;
	public String screenshotpathListener;
	boolean stateLocal = true, stateRemote = false;
	JRadioButton radioLocal = new JRadioButton("Local", stateLocal);
	JRadioButton radioRemote = new JRadioButton("Remote", stateRemote);
	private String[] browser = { "Chrome", "Firefox", "Safari", "IE" };
	private String[] TimeoutRange = { "0", "5", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "120",
			"150", "200" };
	private JTextField textTimeout;
	private JTextField textAuthor;
	JLabel lblAuthor;
	ButtonGroup platform;
	JLabel Timeout;
	JLabel Selenium_Server;
	JLabel Selenium_Server_Host;
	JLabel Driver_Path;
	JLabel Take_Screenshots;
	JLabel Screenshot_Path;
	private JLabel lblMvnCommands;
	private JTextField textMvnCmnd;

	/**
	 * Launch the application.
	 */

	public static void main(String arg[]) {
	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyExecutionTool window = new MyExecutionTool();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	 }


	/**
	 * Create the application.
	 */
	public MyExecutionTool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JPanel initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		tabbedPane.setBounds(0, 0, 1000, 600);
		frame.getContentPane().add( tabbedPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(2147483647, 2147483647));
		panel.setBounds(100, 100, 591, 469);
		panel.setToolTipText("Configurations");
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Configurations", null, panel, null);
		panel.setLayout(null);

		Browser = new JLabel("Browser ");
		Browser.setBounds(5, 11, 70, 20);
		Browser.setHorizontalAlignment(SwingConstants.LEFT);
		Browser.setVerticalAlignment(SwingConstants.TOP);
		panel.add(Browser);

		Tier = new JLabel("Tier");
		Tier.setBounds(202, 11, 35, 20);
		panel.add(Tier);

		Timeout = new JLabel("Timeout");
		Timeout.setBounds(350, 11, 46, 20);
		panel.add(Timeout);

		Selenium_Server = new JLabel("Selenium Server");
		Selenium_Server.setBounds(5, 66, 130, 14);
		panel.add(Selenium_Server);

		Selenium_Server_Host = new JLabel("Selenium Server Host");
		Selenium_Server_Host.setBounds(5, 100, 130, 20);
		panel.add(Selenium_Server_Host);

		Driver_Path = new JLabel("Driver Path");
		Driver_Path.setBounds(5, 160, 94, 20);
		panel.add(Driver_Path);

		Take_Screenshots = new JLabel("Take Screenshots");
		Take_Screenshots.setBounds(5, 130, 110, 20);
		panel.add(Take_Screenshots);

		Screenshot_Path = new JLabel("Screenshot Path");
		Screenshot_Path.setBounds(5, 190, 130, 20);
		panel.add(Screenshot_Path);

		/** browser */
		dropDownBrowser = new JComboBox(browser);
		dropDownBrowser.setBounds(90, 10, 80, 20);
		dropDownBrowser.setSelectedIndex(0);
		panel.add(dropDownBrowser);

		/** tier */
		textTier = new JTextField();
		textTier.setText("Stg");
		textTier.setBounds(240, 10, 50, 20);
		textTier.setColumns(10);
		panel.add(textTier);

		/** Timeout **/
		textTimeout = new JTextField();
		textTimeout.setBounds(400, 10, 59, 20);
		textTimeout.setText("60");
		panel.add(textTimeout);
		textTimeout.setColumns(10);

		/** selenium host */
		txtSeleniumHost = new JTextField();
		txtSeleniumHost.setBounds(150, 100, 249, 20);
		txtSeleniumHost.setColumns(10);
		txtSeleniumHost.setText("http://localhost:4444/wd/hub");
		panel.add(txtSeleniumHost);

		/*** driverpath **/
		driverPath = new JTextField();
		driverPath.setText("./ProjectName/src/test/resources/drivers/chromedriver.exe");
		driverPath.setBounds(150, 160, 250, 20);
		driverPath.setColumns(30);
		panel.add(driverPath);

		/** screenshot- yes **/
		radioScreenShotOptionYes = new JRadioButton("Yes");
		radioScreenShotOptionYes.setSelected(true);
		radioScreenShotOptionYes.setBounds(150, 130, 86, 20);
		panel.add(radioScreenShotOptionYes);

		/** Screenshot- no **/
		radioScreenShotOptionNo = new JRadioButton("No");
		radioScreenShotOptionNo.setSelected(false);
		radioScreenShotOptionNo.setBounds(248, 129, 94, 20);
		panel.add(radioScreenShotOptionNo);

		/** screenshot folder **/
		txtScreenshotFolderPath = new JTextField();
		txtScreenshotFolderPath.setText("./ProjectName/target/screenshots/");
		txtScreenshotFolderPath.setBounds(150, 190, 250, 20);
		txtScreenshotFolderPath.setColumns(30);
		panel.add(txtScreenshotFolderPath);

		/** Environment- local **/
		radioLocal.setSelected(true);
		radioLocal.setBounds(150, 60, 109, 23);
		panel.add(radioLocal);

		/** Environment- remote **/
		radioRemote.setSelected(false);
		radioRemote.setBounds(290, 60, 109, 23);
		panel.add(radioRemote);

		/** to handle radio environment **/
		platform = new ButtonGroup();
		platform.add(radioLocal);
		platform.add(radioRemote);

		/** to handle radio take screenshot **/
		scrrenShot = new ButtonGroup();
		scrrenShot.add(radioScreenShotOptionYes);
		scrrenShot.add(radioScreenShotOptionNo);

		/** button save **/
		btnDone = new JButton("Save");
		btnDone.setBounds(370, 368, 89, 23);
		panel.add(btnDone);

		/** label Author **/
		lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(5, 220, 130, 20);
		panel.add(lblAuthor);

		/** input author name **/
		textAuthor = new JTextField();
		textAuthor.setBounds(150, 220, 250, 20);
		panel.add(textAuthor);
		textAuthor.setColumns(10);
		
		/**label mvn commands**/
		lblMvnCommands = new JLabel("MVN Commands");
		lblMvnCommands.setBounds(5, 250, 130, 20);
		panel.add(lblMvnCommands);
		
		
		textMvnCmnd = new JTextField();
		textMvnCmnd.setBounds(150, 250, 250, 20);
		panel.add(textMvnCmnd);
		textMvnCmnd.setColumns(10);
		btnDone.addActionListener(this);
		
		return panel;
	}
	
	public JPanel testComplete(){
		frameXML = new JFrame();
		frameXML.setBounds(100, 100, 1000, 600);
		frameXML.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameXML.getContentPane().setLayout(null);
		tabbedPane.setBounds(0, 0, 1000, 600);
		frameXML.getContentPane().add(tabbedPane);
		frameXML.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameXML.setVisible(true);

		JPanel panel1 = new JPanel();
		panel1.setMaximumSize(new Dimension(2147483647, 2147483647));
		panel1.setBounds(100, 100, 591, 469);
		panel1.setToolTipText("Configurations");
		panel1.setBackground(Color.WHITE);
		tabbedPane.addTab("Configurations", null, panel1, null);
		panel1.setLayout(null);
		
		Browser = new JLabel("Test complete by "+textAuthor.getText());
		Browser.setBounds(5, 11, 70, 20);
		Browser.setHorizontalAlignment(SwingConstants.LEFT);
		Browser.setVerticalAlignment(SwingConstants.TOP);
		panel1.add(Browser);
		return panel1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		event = e.getActionCommand();

		if (event.equals("Save")) {
			// call function to write into file

			configProperties.put("browser", dropDownBrowser.getSelectedItem().toString());

			configProperties.put("tier", textTier.getText());

			configProperties.put("timeout", textTimeout.getText());

			configProperties.put("seleniumserverhost", txtSeleniumHost.getText());


			if (radioLocal.isSelected()) {
				configProperties.put("seleniumserver", "Local");
			} else {
				configProperties.put("seleniumserver", "Remote");
			}
			configProperties.put("driverpath", driverPath.getText());
			if (radioScreenShotOptionYes.isSelected()) {
				configProperties.put("takescreenshot", "Yes");

			} else {
				configProperties.put("takescreenshot", "No");

			}

			configProperties.put("screenshotpathListener", txtScreenshotFolderPath.getText());
			configProperties.put("Author", textAuthor.getText());
			configProperties.put("MvnCmd", textMvnCmnd.getText());
			config.writeConfigFile(configProperties);
			try {
				Runtime.getRuntime().exec("cmd.exe /c start "+textMvnCmnd);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			try {
//				String ss = null;
//				Process p = Runtime.getRuntime().exec("cmd.exe /c start "+textMvnCmnd);
//				try {
//					p.waitFor();
//				} catch (InterruptedException ie) {
//					// TODO Auto-generated catch block
//					ie.printStackTrace();
//				}
//				if(p.exitValue()=='0'){
//					Runtime.getRuntime().exec("taskkill /IM "+p+" /f");
//				}
//			} catch (IOException ioe) {
//				// TODO Auto-generated catch block
//				ioe.printStackTrace();
//			}
//
//			
		}

	}
}