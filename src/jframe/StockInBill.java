package jframe;

import java.awt.*;
import java.awt.desktop.AboutEvent;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Main.*;
import dao.Dao;
import jframe.StockOutMain;
import tbdao.*;

public class StockInBill extends JFrame {// ���۵��ڲ�����

	private final JTable table;// ���ģ��
	private final JTextField sellDate = new JTextField();// ������ʱ�䡱�ı���
	private JComboBox jsr = null;// �������ˡ������б�
	private final JComboBox jsfs = new JComboBox();// �����㷽ʽ�������б�
	
	private final JComboBox kehu = new JComboBox();// ���ͻ��������б�
	private final JTextField piaoHao = new JTextField();// ������Ʊ�š��ı���
	
	private final JTextField hjje = new JTextField("0");// ���ϼƽ��ı���
	private final JTextField czy = new JTextField();// ������Ա���ı���
	private Date xssjDate;// ���������ڡ�
	private JComboBox sp;// ����Ʒ�������б�
	

	public StockInBill() {// ���۵��ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ�����
//		setMaximizable(true);// �������
//		setIconifiable(true);// ����ͼ�껯
//		setClosable(true);// ���Թر����۵��ڲ�����
		getContentPane().setLayout(new GridBagLayout());// �������۵��ڲ�����Ĳ���Ϊ���񲼾�
		getContentPane().setBackground(new Color(255,245,238));
		setTitle("��ⵥ");// �������۵��ڲ�����ı���
		setBounds(0, 0, 700, 350);// �������۵��ڲ������λ�úͿ��
		Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth/2-700/2, screenHeight/2-350/2);//���ô��ھ�����ʾ
		// ������Ʊ�š���ǩ�͡�����Ʊ�š��ı���


		setupComponet(new JLabel("��ⵥ�ţ�"), 0,0,1,0,false);
		
		setupComponet(piaoHao, 1, 0, 1, 80, true);
		// ���ͻ�����ǩ�͡��ͻ��������б�
		setupComponet(new JLabel("               ��Ӧ�̣�"), 2, 0, 1, 0, false);
		setupComponet(new JLabel(Global.xs_KHname), 3, 0, 1, 80, true);
		
		
		// �����㷽ʽ����ǩ�͡����㷽ʽ�������б�
		setupComponet(new JLabel("���㷽ʽ��"), 0, 1, 1, 0, false);
		setupComponet(new JLabel(Global.xs_jsfs), 1, 1, 1, 1, true);

		// ������ʱ�䡱��ǩ�͡�����ʱ�䡱�ı���
		setupComponet(new JLabel("               ���ʱ�䣺"), 2, 1, 1, 0, false);
		sellDate.setFocusable(false);
		setupComponet(sellDate, 3, 1, 1, 1, true);
		// �������ˡ���ǩ�͡������ˡ������б�
		setupComponet(new JLabel("�����ˣ�"+Global.xs_jsr), 4, 1, 1, 0, false);
		
		// ���ģ��
		table = new JTable();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		initTable();
		// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);
		

		
		// ��������Ա����ǩ�͡�������Ա���ı���
		setupComponet(new JLabel("������Ա��"), 0, 4, 1, 0, false);
		
		czy.setFocusable(false);
		czy.setText(MainFrame.getCzyStateLabel().getText());
		setupComponet(czy, 1, 4, 1, 1, true);

		
		// �����⡱��ť
		final JButton tjButton = new JButton("   ���   ");
		
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String jeStr = Global.xs_jr;// �ϼƽ��
				String jsfsStr = Global.xs_jsfs;// ���㷽ʽ
				String jsrStr = Global.username;// ������
				String czyStr = czy.getText();// ����Ա
				DateFormat ddtf = DateFormat.getDateTimeInstance();  
				xssjDate = new Date();
				//String rkDate = ddtf.format(xssjDae);// ����ʱ��
				
				DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //����һ����ʽ�����ڶ���
				String punchTime = simpleDateFormat.format(xssjDate);  

				int flag=0;//��¼�Ƿ�ȱ��
				String id = piaoHao.getText();// Ʊ��
				java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
				String planid=Dao.getPlanBuyMainMaxId(date);
				String kehuName = Global.xs_KHname;// ��Ӧ������
				TbSale sellMain = new TbSale(id, jeStr,  kehuName, punchTime, czyStr, jsrStr, jsfsStr);// ��������
				Set<TbSaleDetail> set = sellMain.getTbSaleDetails();// ���������ϸ�ļ���
				int rows = table.getRowCount();
				for (int i = 0; i < rows; i++) {
					
					String ypname=(String)table.getValueAt(i, 1);
					String ypid=(String)table.getValueAt(i, 0);
					String ypplace=(String)table.getValueAt(i, 5);
					String ypunit=(String)table.getValueAt(i, 3);
					String ypspec=(String)table.getValueAt(i, 2);
					String djStr = (String) table.getValueAt(i, 6);
					String slStr = (String) table.getValueAt(i, 4);
					 
					Double dj = Double.valueOf(djStr);
					Integer sl = Integer.valueOf(slStr);
					Integer rest=Dao.getYpquantity(ypid);
					
						
					TbSaleDetail detail = new TbSaleDetail();// ������ϸ
					detail.setYpid(ypid);// ��ˮ��
					detail.setTbSellMain(sellMain.getSellId());// ��������
					detail.setDj(dj);// ���۵���
					detail.setSl(sl);// ��������
					detail.setName(ypname);
					detail.setPlace(ypplace);
					detail.setUnit(ypunit);
					detail.setSpec(ypspec);
					set.add(detail);
					
				}
				
					boolean rs = Dao.insertInInfo(sellMain);// ��ӳ�����Ϣ
					if (rs) {
						JOptionPane.showMessageDialog(StockInBill.this, "������");
						dispose();

						hjje.setText("0");
					}
				
				
					
				
			}
		});
		setupComponet(tjButton, 2, 4, 1, 1, false);
		
		//JOptionPane.showMessageDialog(null, "������ʾ"); 
		// ��Ӵ������������ɳ�ʼ��
		//addWindowListener(new initTasks());
		initTasks();
		

	}


	// ��ʼ�����
	private void initTable() {
		String[] columnNames = {  "ҩƷ���", "ҩƷ����",  "���","��λ", "����","��������","����" };
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		dftm.setColumnIdentifiers(columnNames);
		TableColumn column = table.getColumnModel().getColumn(0);
DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		
		r.setHorizontalAlignment(JLabel.CENTER);   //������ݾ���
		table.setDefaultRenderer(Object.class, r);
		List list =null;
		list = searchInfo(list);
		updateTable(list, dftm);

	}



	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;

		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5,5, 5, 5);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}





	// ����ĳ�ʼ������
	private void initTasks()  {
		
			
			initTimeField();
			initPiaoHao();
		


	}

	// ��������ʱ���߳�
	private void initTimeField() {
		new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						 
						DateFormat ddtf = DateFormat.getDateTimeInstance();  
						xssjDate = new Date();
						sellDate.setText(ddtf.format(xssjDate));
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void initPiaoHao() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		String maxId = Dao.getInMainMaxId(date);
		piaoHao.setText(maxId);
	}


	// ������Ʒ�����б����±��ǰ�е�����
	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		TbYpinfo ypInfo;// ��Ʒ��Ϣ
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(2));
			item.setName((String) info.get(1));
			ypInfo = Dao.getBuyInfo(item,Global.xs_number);
			Vector rowData = new Vector();
			//JOptionPane.showMessageDialog(null, "You input is "+ypInfo.getYpname().trim(), ypInfo.getYpname().trim(), JOptionPane.PLAIN_MESSAGE);
			rowData.add(ypInfo.getYpid().trim());// ��Ʒ���
			rowData.add(ypInfo.getYpname().trim());// ��Ʒ��
			rowData.add(ypInfo.getSpec());// ��Ʒ���
			
			rowData.add(ypInfo.getUnit());// ��Ʒ������λ
			
			
			rowData.add(ypInfo.getQuantity());// ����
			rowData.add(ypInfo.getPlace());// ����
			rowData.add(ypInfo.getSaleprice());// ����
			dftm.addRow(rowData);// ���������������ݣ���Ʒ��Ϣ��
		}
	}

//���ִ��SQL������Ӧ�Ľ����
private List searchInfo(List list) {
	String sql = "select * from tb_buy_detail where ";
	
			
			list = Dao.findForList(sql + "sellID='" + Global.xs_number + "'");
	return list;
}
	private void doKhSelectAction() {
		Item item = (Item) kehu.getSelectedItem();
		TbKhinfo khInfo = Dao.getKhInfo(item);
		
	}
	
	// �������
	private synchronized void clearEmptyRow() {
		DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		for (int i = 0; i < table.getRowCount(); i++) {
			String info2 = (String)table.getValueAt(i, 0);
			if (info2 == null || info2 == null || info2.isEmpty()) {
				dftm.removeRow(i);
			}
		}
	}

	// ֹͣ���Ԫ�ı༭
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();
		if (cellEditor != null)
			cellEditor.stopCellEditing();
	}
}
