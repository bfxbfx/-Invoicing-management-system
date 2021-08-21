package Main;

import static java.awt.BorderLayout.*;
import static javax.swing.border.BevelBorder.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

import Login.LoginInterface;

public class MainFrame extends JFrame {// ������
	private static final long serialVersionUID = 1L;
	private JPanel frameContentPane = null;// �������
	private MenuBar frameMenuBar = null;// �˵���
//	private ToolBar toolBar = null;// ������
	private DesktopPanel desktopPane = null;// �������
	private JPanel statePanel = null;// ״̬���
	private JLabel stateLabel = null;// ѡ������״̬��ǩ
	private JLabel nameLabel = null;// ��ϵͳ���ơ���ǩ
	private JLabel nowDateLabel = null;// ����ǰ���ڡ���ǩ
	private JSeparator jSeparator1 = null;
	private static JLabel czyStateLabel = null;// ������Ա����ǩ
	private JSeparator jSeparator2 = null;
 
	/**
	 * ���������������г�������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SplashScreen splashScreen = SplashScreen.getSplashScreen();// ����������Ļ�Դ���������Ļ����
		JFrame login = new LoginInterface();// ��¼����
		if (splashScreen != null) {// ������Ļ����Ϊ��
			try {
				login.setDefaultCloseOperation(EXIT_ON_CLOSE);// ���õ�¼����Ĺرշ�ʽ
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
		login.setVisible(true);// ʹ��¼����ɼ�
	}

	/**
	 * ��ʼ������˵����ķ���
	 * 
	 * @return JMenuBar
	 */
	protected MenuBar getFrameMenuBar() {
		if (frameMenuBar == null) {// �˵�������Ϊ��
			frameMenuBar = new MenuBar(getDesktopPane(), getStateLabel());// �����˵�������
		}
		return frameMenuBar;
	}

	/**
	 * ��ʼ���������ķ���
	 * 
	 * @return JDesktopPane
	 */
	private DesktopPanel getDesktopPane() {
		if (desktopPane == null) {// ����������Ϊ��
			desktopPane = new DesktopPanel();// ��������������
		}
		return desktopPane;
	}

	/**
	 * ��ʼ��״̬���ķ���
	 * 
	 * @return JPanel
	 */
	private JPanel getStatePanel() {
		if (statePanel == null) {
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.insets = new Insets(0, 5, 0, 5);
			gridBagConstraints6.gridy = 0;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 3;
			gridBagConstraints4.gridy = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 6;
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.insets = new Insets(0, 5, 0, 5);
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 5;
			gridBagConstraints11.insets = new Insets(0, 5, 0, 5);
			gridBagConstraints11.gridy = 0;
			nowDateLabel = new JLabel();// ����ǰ���ڡ���ǩ
			Date now = new Date();// ����Date����
			nowDateLabel.setText(String.format("%tF", now));
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 7;
			gridBagConstraints2.weightx = 0.0;
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.gridy = 0;
			nameLabel = new JLabel("ҩƷ���������ϵͳ   ");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 4;
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.insets = new Insets(0, 5, 0, 5);
			gridBagConstraints1.gridy = 0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridy = 0;
			statePanel = new JPanel();// ״̬���
			statePanel.setLayout(new GridBagLayout());
			statePanel.setBorder(BorderFactory.createBevelBorder(RAISED));
			statePanel.add(getStateLabel(), gridBagConstraints);
			statePanel.add(getJSeparator(), gridBagConstraints1);
			statePanel.add(nameLabel, gridBagConstraints2);
			statePanel.add(getJSeparator1(), gridBagConstraints3);
			statePanel.add(nowDateLabel, gridBagConstraints11);
			statePanel.add(getCzyStateLabel(), gridBagConstraints4);
			statePanel.add(getJSeparator2(), gridBagConstraints6);
		}
		return statePanel;
	}

	public static JLabel getCzyStateLabel() {// ��á�����Ա����ǩ�ķ���
		if (czyStateLabel == null) {
			czyStateLabel = new JLabel("����Ա��");
		}
		return czyStateLabel;
	}

	public JLabel getStateLabel() {// ���ѡ������״̬��ǩ
		if (stateLabel == null) {
			stateLabel = new JLabel();
			
		}
		return stateLabel;
	}

	private JSeparator getJSeparator() {
		JSeparator jSeparator = new JSeparator();
		jSeparator.setOrientation(JSeparator.VERTICAL);
		return jSeparator;
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
			jSeparator1.setOrientation(SwingConstants.VERTICAL);
		}
		return jSeparator1;
	}

	private JSeparator getJSeparator2() {
		if (jSeparator2 == null) {
			jSeparator2 = new JSeparator();
			jSeparator2.setOrientation(SwingConstants.VERTICAL);
		}
		return jSeparator2;
	}

	public MainFrame() {
		super(); 
		initialize();
	}

	private void initialize() {// ��ʼ��������
		this.setSize(1000, 800);
		this.setJMenuBar(getFrameMenuBar());
		this.setContentPane(getFrameContentPane());
		this.setTitle("ҩƷ���������ϵͳ");
		Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
        //���ô��ھ���
        this.setLocation(screenWidth/2-1000/2, screenHeight/2-800/2);//���ô��ھ�����ʾ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private JPanel getFrameContentPane() {// ����������
		if (frameContentPane == null) {
			frameContentPane = new JPanel();// �����������
			frameContentPane.setLayout(new BorderLayout());
			frameContentPane.add(getStatePanel(), SOUTH);
			frameContentPane.add(getDesktopPane(), CENTER);
		}
		return frameContentPane;
	}

}
