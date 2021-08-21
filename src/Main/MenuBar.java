package Main;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import jframe.*;

public class MenuBar extends JMenuBar {// �˵���

	/**
	 * �����������˵�
	 */
	private JMenu jinhuo_Menu = null;

	/**
	 * �����������˵��λ�ڣ����������˵���
	 */
	private JMenuItem jinhuoItem = null;

	/**
	 * �������˻����˵��λ�ڣ����������˵���
	 */
	private JMenuItem jinhuo_tuihuoItem = null;

	/**
	 * �����۹����˵�
	 */
	private JMenu xiaoshou_Menu = null;

	/**
	 * ���������˵�
	 */
	private JMenu kucun_Menu = null;

	/**
	 * ����Ϣ��ѯ���˵�
	 */
	private JMenu xinxi_chaxunMenu = null;

	/**
	 * ���������ϣ��˵�
	 */
	private JMenu jiben_ziliaoMenu = null;



	/**
	 * �����ڣ��˵�
	 */
	private JMenu chuang_kouMenu = null;



	
	/**
	 * �����۵����˵��λ�ڣ����۹����˵���
	 */
	private JMenuItem xiaoshou_danItem = null;

	/**
	 * �������˻����˵��λ�ڣ����۹����˵���
	 */
	private JMenuItem xiaoshou_tuihuoItem = null;

	/**
	 * ������̵㣩�˵��λ�ڣ��������˵���
	 */
	private JMenuItem kucun_pandianItem = null;

	/**
	 * ���۸�������˵��λ�ڣ��������˵���
	 */
	private JMenuItem chukuItem = null;

	/**
	 * �����۲�ѯ���˵��λ�ڣ���Ϣ��ѯ���˵���
	 */
	private JMenuItem xiaoshou_chaxunItem = null;

	/**
	 * ����Ʒ��ѯ���˵��λ�ڣ���Ϣ��ѯ���˵���
	 */
	private JMenuItem shangpin_chaxunItem = null;

	
	/**
	 * ����Ʒ�����˵��λ�ڣ��������ϣ��˵���
	 */
	private JMenuItem shangpin_guanliItem = null;

	/**
	 * ���ͻ������˵��λ�ڣ��������ϣ��˵���
	 */
	private JMenuItem kehu_guanliItem = null;

	/**
	 * ����Ӧ�̹����˵��λ�ڣ��������ϣ��˵���
	 */
	private JMenuItem gys_guanliItem = null;
	
	//�����˵�
	private JMenu Send_Menu = null;

	private JMenuItem SendItem = null;
	
	private JMenuItem SendQueryItem = null;
	
	//�ɹ��˵� 
	private JMenu Buy_Menu = null;

	private JMenuItem BuyItem = null;
	
	private JMenuItem BuyQueryItem = null;
	
	private JMenuItem PlanBuyItem = null;
	
	//���ͳ���
	private JMenu In_Menu = null;
	private JMenuItem InItem = null;
	private JMenuItem InQueryItem = null;
	private JMenuItem OutItem = null;
	private JMenuItem OutQueryItem = null;
	
	private JMenu Out_Menu = null;


	
	/**
	 * ���˳����˵��λ�ڣ�ϵͳά�����˵���
	 */
	private JMenuItem exitItem = null;

	/**
	 * ������ƽ�̣��˵��λ�ڣ����ڣ��˵���
	 */
	private JMenuItem pingpuItem = null;

	/**
	 * �����ڲ�������������
	 */
	private JDesktopPane desktopPanel = null;

	/**
	 * �ڲ�����ļ���
	 */
	private Map<JMenuItem, JInternalFrame> iFrames = null;

	/**
	 * �ڲ������λ������
	 */
	private int nextFrameX, nextFrameY;

	
	/**
	 * ״̬�����ڲ�������ʾ��ǩ
	 */
	private JLabel stateLabel = null;

	/**
	 * Ĭ�ϵĹ��췽��
	 * 
	 */
	private MenuBar() {
	}

	public MenuBar(JDesktopPane desktopPanel, JLabel label) {
		super();
		iFrames = new HashMap<JMenuItem, JInternalFrame>();
		this.desktopPanel = desktopPanel;
		this.stateLabel = label;
		initialize();
	}

	/**
	 * ��ʼ���˵�������ķ���
	 * 
	 */
	private void initialize() {
		this.setSize(new Dimension(600, 24));
		add(getBuy_Menu());
		add(getIn_Menu());
		add(getOut_Menu());
		add(getXiaoshou_Menu());
		add(getSend_Menu());
		add(getKucun_Menu());
		add(getXinxi_chaxunMenu());
		//add(getJiben_ziliaoMenu());
		
		
	}


	//�ɹ�
	public JMenu getBuy_Menu() {
		if (Buy_Menu == null) {
			Buy_Menu = new JMenu();
			if(!Global.userID.equals("buy"))Buy_Menu.setVisible(false);
			Buy_Menu.setText("�ɹ�����");
			
			Buy_Menu.add(getBuyItem());
			Buy_Menu.add(getPlanBuyItem());
			Buy_Menu.add(getBuyQueryItem());
			
		}
		return Buy_Menu;
	}

	
	public JMenuItem getBuyItem() {
		if (BuyItem == null) {
			BuyItem = new JMenuItem();
			BuyItem.setText("�ɹ���");
			BuyItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			BuyItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Global.planbuylist=null;
					createIFrame(BuyItem, BuyBill.class);
				}
			});
		}
		return BuyItem;
	}
	
	public JMenuItem getBuyQueryItem() {
		if (BuyQueryItem == null) {
			BuyQueryItem = new JMenuItem();
			BuyQueryItem.setText("�ɹ�����ѯ");
			BuyQueryItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			BuyQueryItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(BuyQueryItem, BuyQuery.class);
				}
			});
		}
		return BuyQueryItem;
	}
	
	public JMenuItem getPlanBuyItem() {
		if (PlanBuyItem == null) {
			PlanBuyItem = new JMenuItem();
			PlanBuyItem.setText("�ɹ��ƻ�����ѯ");
			PlanBuyItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			PlanBuyItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(PlanBuyItem, PlanBuyMain.class);
				}
			});
		}
		return PlanBuyItem;
	}
	
	public JMenu getIn_Menu() {
		if (In_Menu == null) {
			In_Menu = new JMenu();
			if(!Global.userID.equals("stock"))In_Menu.setVisible(false);
			In_Menu.setText("������");
			
			In_Menu.add(getInItem());
			In_Menu.add(getInQueryItem());
			
		}
		return In_Menu;
	}
	
	public JMenu getOut_Menu() {
		if (Out_Menu == null) {
			Out_Menu = new JMenu();
			if(!Global.userID.equals("stock"))Out_Menu.setVisible(false);
			Out_Menu.setText("�������");
			
			Out_Menu.add(getOutItem());
			Out_Menu.add(getOutQueryItem());
			
		}
		return Out_Menu;
	}
	
	/**
	 * ��ʼ�������������˵���ķ��� �÷�������˵���򿪽���������,��ʹ���ڴ��ڱ�ѡ��״̬
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getInItem() {
		if (InItem == null) {
			InItem = new JMenuItem();
			InItem.setText("ҩƷ���");
			InItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			InItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(InItem, StockInMain.class);
				}
			});
		}
		return InItem;
	}
	
	public JMenuItem getInQueryItem() {
		if (InQueryItem == null) {
			InQueryItem = new JMenuItem();
			InQueryItem.setText("��ⵥ��ѯ");
			InQueryItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			InQueryItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(InQueryItem, InQuery.class);
				}
			});
		}
		return InQueryItem;
	}
	
	public JMenuItem getOutQueryItem() {
		if (OutQueryItem == null) {
			OutQueryItem = new JMenuItem();
			OutQueryItem.setText("���ⵥ��ѯ");
			OutQueryItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			OutQueryItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createIFrame(OutQueryItem, OutQuery.class);
				}
			});
		}
		return OutQueryItem;
	}

	

	/**
	 * ��ʼ�������۹����˵��ķ������÷�������˵�����ڲ����壬��ʹ���崦����ѡ��״̬��
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getXiaoshou_Menu() {
		if (xiaoshou_Menu == null) {
			xiaoshou_Menu = new JMenu();
			
			if(!Global.userID.equals("sale"))xiaoshou_Menu.setVisible(false);
			xiaoshou_Menu.setText("���۹���");
			xiaoshou_Menu.add(getXiaoshou_danItem());
			xiaoshou_Menu.add(getXiaoshou_chaxunItem());
			
		}
		return xiaoshou_Menu;
	}
	
	public JMenu getSend_Menu() {
		if (Send_Menu == null) {
			Send_Menu = new JMenu();
			
			if(!Global.userID.equals("sale"))Send_Menu.setVisible(false);
			Send_Menu.setText("��������");
			Send_Menu.add(getSendItem());
			Send_Menu.add(getSendQueryItem());
			
		}
		return Send_Menu;
	}
	


	/**
	 * ��ʼ�����������˵��ķ���
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getKucun_Menu() {
		if (kucun_Menu == null) {
			kucun_Menu = new JMenu();
			if(!Global.userID.equals("stock"))kucun_Menu.setVisible(false);
			kucun_Menu.setText("������");
			
			kucun_Menu.add(getKucun_pandianItem());
		}
		return kucun_Menu;
	}

	/**
	 * ��ʼ������Ϣ��ѯ���˵��ķ���
	 * 
	 * @return javax.swing.JMenu
	 */
	public JMenu getXinxi_chaxunMenu() {
		if (xinxi_chaxunMenu == null) {
			xinxi_chaxunMenu = new JMenu();
			xinxi_chaxunMenu.setText("��Ϣ��ѯ");
			
			xinxi_chaxunMenu.add(getYaopin_chaxunItem());
			xinxi_chaxunMenu.addSeparator();
		}
		return xinxi_chaxunMenu;
	}




	
	
	

	
	/**
	 * ��ʼ�������۵����˵���ķ���
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getXiaoshou_danItem() {
		if (xiaoshou_danItem == null) {
			xiaoshou_danItem = new JMenuItem();
			xiaoshou_danItem.setText("���۵�");
			xiaoshou_danItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			xiaoshou_danItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createIFrame(xiaoshou_danItem, SaleBill.class);
				}
			});
		}
		return xiaoshou_danItem;
	}


	/**
	 * ��ʼ��������̵㣩�˵���ķ���
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getKucun_pandianItem() {
		if (kucun_pandianItem == null) {
			kucun_pandianItem = new JMenuItem();
			kucun_pandianItem.setText("����̵�");
			kucun_pandianItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			kucun_pandianItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Global.flag=2;
					createIFrame(kucun_pandianItem, YpQuery.class);
				}
			});
		}
		return kucun_pandianItem;
	}

	/**
	 * ��ʼ�����۸�������˵���ķ���
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getOutItem() {
		if (OutItem == null) {
			OutItem = new JMenuItem();
			OutItem.setText("ҩƷ����");
			OutItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			OutItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createIFrame(OutItem, StockOutMain.class);
				}
			});
		}
		return OutItem;
	}

	/**
	 * ��ʼ�������۲�ѯ���˵���ķ���
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getXiaoshou_chaxunItem() {
		if (xiaoshou_chaxunItem == null) {
			xiaoshou_chaxunItem = new JMenuItem();
			xiaoshou_chaxunItem.setText("���۲�ѯ");
			xiaoshou_chaxunItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			xiaoshou_chaxunItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createIFrame(xiaoshou_chaxunItem, SaleQuery.class);
				} 
			});
		}
		return xiaoshou_chaxunItem;
	}

	/**
	 * ��ʼ������Ʒ��ѯ���˵���ķ���

	 */
	public JMenuItem getYaopin_chaxunItem() {
		if (shangpin_chaxunItem == null) {
			shangpin_chaxunItem = new JMenuItem();
			shangpin_chaxunItem.setText("ҩƷ��ѯ");
			shangpin_chaxunItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			shangpin_chaxunItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Global.flag=1;
					createIFrame(shangpin_chaxunItem, YpQuery.class);
				}
			});
		}
		return shangpin_chaxunItem;
	}

	

	/**
	 * ��ʼ������Ʒ�����˵���ķ���
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getShangpin_guanliItem() {
		if (shangpin_guanliItem == null) {
			shangpin_guanliItem = new JMenuItem();
			shangpin_guanliItem.setText("��Ʒ���Ϲ���");
			shangpin_guanliItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/shangpin_guanli.png")));
			shangpin_guanliItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(shangpin_guanliItem, ShangPinGuanLi.class);
				}
			});
		}
		return shangpin_guanliItem;
	}

	/**
	 * ��ʼ�����ͻ����Ϲ����˵���ķ���

	 */
	public JMenuItem getKehu_guanliItem() {
		if (kehu_guanliItem == null) {
			kehu_guanliItem = new JMenuItem();
			kehu_guanliItem.setText("�ͻ����Ϲ���");
			//kehu_guanliItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/kehu_guanli.png")));
			kehu_guanliItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//createIFrame(kehu_guanliItem, KeHuGuanLi.class);
				}
			});
		}
		return kehu_guanliItem;
	}

	/**
	 * ��ʼ������Ӧ�̹����˵���ķ���

	 */
	public JMenuItem getGys_guanliItem() {
		if (gys_guanliItem == null) {
			gys_guanliItem = new JMenuItem();
			gys_guanliItem.setText("��Ӧ�����Ϲ���");
			//gys_guanliItem.setIcon(new ImageIcon(getClass().getResource("/res/icon/gys_guanli.png")));
			gys_guanliItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//createIFrame(gys_guanliItem, GysGuanLi.class);
				}
			});
		}
		return gys_guanliItem;
	}

//�����뷢������ѯ
	public JMenuItem getSendItem() {
		if (SendItem == null) {
			SendItem = new JMenuItem();
			SendItem.setText("����");
			SendItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			SendItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createIFrame(SendItem, SendMain.class);
				}
			});
		}
		return SendItem;
	}
	
	public JMenuItem getSendQueryItem() {
		if (SendQueryItem == null) {
			SendQueryItem = new JMenuItem();
			SendQueryItem.setText("��������ѯ");
			SendQueryItem.setIcon(new ImageIcon(getClass().getResource("/res/itemicon.png")));
			SendQueryItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					createIFrame(SendQueryItem, SendQuery.class);
				}
			});
		}
		return SendQueryItem;
	}

	

	/**
	 * ��ʼ�����˳�ϵͳ���˵���ķ���
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getExitItem() {
		if (exitItem == null) {
			exitItem = new JMenuItem();
			exitItem.setText("�˳�ϵͳ");
			
			exitItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitItem;
	}

	/**
	 * ��ʼ��������ƽ�̣��˵���ķ���
	 * 
	 * @return javax.swing.JMenuItem
	 */
	public JMenuItem getPingpuItem() {
		if (pingpuItem == null) {
			pingpuItem = new JMenuItem();
			pingpuItem.setText("���ڲ��");
			pingpuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JInternalFrame[] allFrames = desktopPanel.getAllFrames();
					int x = 0, y = 0;
					for (JInternalFrame iFrame : allFrames) {
						iFrame.setLocation(x, y);
						try {
							iFrame.setSelected(true);
						} catch (PropertyVetoException e1) {
							e1.printStackTrace();
						}
						int frameH = iFrame.getPreferredSize().height;
						int panelH = iFrame.getContentPane().getPreferredSize().height;
						int fSpacing = frameH - panelH;
						x += fSpacing;
						y += fSpacing;
						if (x + getWidth() / 2 > desktopPanel.getWidth())
							x = 0;
						if (y + getHeight() / 2 > desktopPanel.getHeight())
							y = 0;
					}
				}
			});
		}
		return pingpuItem;
	}

	/**
	 * �����ڲ�����ķ������÷���ʹ�÷��似����ȡ�ڲ�����Ĺ��췽�����Ӷ������ڲ����塣
	 * 
	 * @param item��������ڲ�����Ĳ˵���
	 * @param clazz���ڲ������Class����
	 */
	private JInternalFrame createIFrame(JMenuItem item, Class clazz) {
		Constructor constructor = clazz.getConstructors()[0];
		JInternalFrame iFrame = iFrames.get(item);
		try {
			if (iFrame == null || iFrame.isClosed()) {
				iFrame = (JInternalFrame) constructor.newInstance(new Object[] {});
				iFrames.put(item, iFrame);
				iFrame.setFrameIcon(item.getIcon());
				iFrame.setLocation(nextFrameX, nextFrameY);
				int frameH = iFrame.getPreferredSize().height;
				int panelH = iFrame.getContentPane().getPreferredSize().height;
				int fSpacing = frameH - panelH;
				nextFrameX += fSpacing;
				nextFrameY += fSpacing;
				if (nextFrameX + iFrame.getWidth() > desktopPanel.getWidth())
					nextFrameX = 0;
				if (nextFrameY + iFrame.getHeight() > desktopPanel.getHeight())
					nextFrameY = 0;
				desktopPanel.add(iFrame);
				iFrame.setResizable(false);
				iFrame.setMaximizable(false);
				iFrame.setVisible(true);
			}
			iFrame.setSelected(true); 
			stateLabel.setText(iFrame.getTitle());
			iFrame.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameActivated(InternalFrameEvent e) {
					super.internalFrameActivated(e);
					JInternalFrame frame = e.getInternalFrame();
					stateLabel.setText(frame.getTitle());
				}

				public void internalFrameDeactivated(InternalFrameEvent e) {
					stateLabel.setText(" ");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iFrame;
	}

	
	





}
