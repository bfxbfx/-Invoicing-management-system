package Login;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import dao.*;
import Main.MainFrame;
import Main.Global;

public class LoginInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	private LoginPanel loginPanel = null;// ��¼���
	private JLabel jLabel = null;// ���û�������ǩ
	private JTextField userField = null;// ���û������ı���
	private JLabel jLabel1 = null;// �����롱��ǩ
	private JPasswordField passwordField = null;// �����롱�ı���
	private JButton loginButton = null;// ����¼����ť
	private JButton exitButton = null;// ���˳�����ť
	private static String userStr;// ���û������ı����е�����
	private MainFrame mainFrame;// ������

	public LoginInterface() {// ��¼����Ĺ��췽��
		try { 
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private LoginPanel getLoginPanel() {
		if (loginPanel == null) {// ��¼�����û�����ʱ
			jLabel1 = new JLabel();// �����롱��ǩ
			jLabel1.setBounds(new Rectangle(86, 71, 55, 18));
			jLabel1.setText("�ܡ��룺");
			jLabel = new JLabel();
			jLabel.setText("�û�����");
			jLabel.setBounds(new Rectangle(85, 41, 56, 18));
			loginPanel = new LoginPanel();
			loginPanel.setLayout(null);
			loginPanel.setBackground(new Color(0xD8DDC7));
			loginPanel.add(jLabel, null);
			loginPanel.add(getUserField(), null);
			loginPanel.add(jLabel1, null);
			loginPanel.add(getPasswordField(), null);
			loginPanel.add(getLoginButton(), null);
			loginPanel.add(getExitButton(), null);
		}
		return loginPanel;// ���ص�¼���
	}

	private JTextField getUserField() {// ��ʼ�����û������ı���
		if (userField == null) {
			userField = new JTextField();
			userField.setBounds(new Rectangle(142, 39, 127, 22));
		}
		return userField;
	}

	private JPasswordField getPasswordField() {// ��ʼ�������롱�ı���
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(new Rectangle(143, 69, 125, 22));
			passwordField.addKeyListener(new KeyAdapter() {// Ϊ�����롱�ı�����Ӽ���ʱ��ļ���
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == '\n')
						loginButton.doClick();
				}
			});
		}
		return passwordField;
	}

	private JButton getLoginButton() {// ��ʼ������¼����ť
		if (loginButton == null) {
			loginButton = new JButton();
			loginButton.setBounds(new Rectangle(109, 114, 48, 20));
			loginButton.setIcon(new ImageIcon(getClass().getResource("/res/loginbutton.jpg")));
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						userStr = userField.getText();
						String passStr = new String(passwordField.getPassword());
						//userStr="a";passStr="a";
						if (!Dao.checkLogin(userStr, passStr)) {// ��֤�û���������ʧ��
							JOptionPane.showMessageDialog(LoginInterface.this, "�û����������޷���¼", "��¼ʧ��",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					mainFrame = new MainFrame();
					mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					mainFrame.setVisible(true);
					String str=null;
					if(Global.userID.equals("sale"))str="����Ա�� ";
					else if(Global.userID.equals("stock"))str="�ֿ����Ա�� ";
					else if(Global.userID.equals("buy"))str="�ɹ�Ա�� ";
					MainFrame.getCzyStateLabel().setText(str+userStr);
					setVisible(false);
				}
			});
		}
		return loginButton;
	}

	private JButton getExitButton() {// ���˳�����ť
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setBounds(new Rectangle(181, 114, 48, 20));
			exitButton.setIcon(new ImageIcon(getClass().getResource("/res/exitbutton.jpg")));
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);// �˳�
				}
			});
		}
		return exitButton;
	}

	private void initialize() {// ��ʼ����¼����
		Dimension size = getToolkit().getScreenSize();
		setLocation((size.width - 296) / 2, (size.height - 188) / 2);
		setSize(296, 188);
		this.setTitle("ϵͳ��¼");
		setContentPane(getLoginPanel());
	}

	public String getUserStr() {
		
		return userStr;
	}
}
