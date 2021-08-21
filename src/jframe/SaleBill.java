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

import Main.Item;
import tbdao.TbKhinfo;

import Main.*;
import dao.Dao;
import tbdao.*;

public class SaleBill extends JInternalFrame {// ���۵��ڲ�����

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

	public SaleBill() {// ���۵��ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ�����
		setMaximizable(true);// �������
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թر����۵��ڲ�����
		getContentPane().setLayout(new GridBagLayout());// �������۵��ڲ�����Ĳ���Ϊ���񲼾� 
		getContentPane().setBackground(new Color(212,234,252));
		setTitle("���۵�");// �������۵��ڲ�����ı���
		setBounds(0, 0, 800, 600);// �������۵��ڲ������λ�úͿ��
		// ������Ʊ�š���ǩ�͡�����Ʊ�š��ı���


		setupComponet(new JLabel("����Ʊ�ţ�"), 0,0,1,0,false);
		
		piaoHao.setFocusable(false);
		setupComponet(piaoHao, 1, 0, 1, 80, true);
		// ���ͻ�����ǩ�͡��ͻ��������б�
		setupComponet(new JLabel("               �ͻ���"), 2, 0, 1, 0, false);
		kehu.setPreferredSize(new Dimension(100, 21));
		kehu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doKhSelectAction();// ���ͻ��������б��ѡ���¼�
			}
		});
		setupComponet(kehu, 3, 0, 1, 80, true);
		
		// �����㷽ʽ����ǩ�͡����㷽ʽ�������б�
		setupComponet(new JLabel("���㷽ʽ��"), 0, 1, 1, 0, false);
		jsfs.addItem("�ֽ�");
		jsfs.addItem("֧Ʊ");
		jsfs.addItem("�ƶ�֧��");
		jsfs.setEditable(true);
		setupComponet(jsfs, 1, 1, 1, 1, true);
		// ������ʱ�䡱��ǩ�͡�����ʱ�䡱�ı���
		setupComponet(new JLabel("               ����ʱ�䣺"), 2, 1, 1, 0, false);
		sellDate.setFocusable(false);
		setupComponet(sellDate, 3, 1, 1, 1, true);
		// �������ˡ���ǩ�͡������ˡ������б�
		setupComponet(new JLabel("�����ˣ�"+Global.username), 4, 1, 1, 0, false);
		// ����Ʒ�������б�
		sp = new JComboBox();
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TbYpinfo info = (TbYpinfo) sp.getSelectedItem();
				if (info != null && info.getYpid() != null) {
					updateTable();// ���ѡ����Ч�͸��±��
				}
			}
		});
		// ���ģ��
		table = new JTable();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		initTable();
		// ����¼����Ʒ����������Ʒ�������ϼƽ��ļ���
		table.addContainerListener(new computeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);
		
		// ���ϼƽ���ǩ�͡��ϼƽ��ı���
		setupComponet(new JLabel("�ϼƽ�"), 0, 4, 1, 0, false);
		hjje.setFocusable(false);
		setupComponet(hjje, 1, 4, 1, 1, true);
		
		// ��������Ա����ǩ�͡�������Ա���ı���
		setupComponet(new JLabel("               ������Ա��"), 2, 4, 1, 0, false);
		czy.setFocusable(false);
		czy.setText(MainFrame.getCzyStateLabel().getText());
		setupComponet(czy, 3, 4, 1, 1, true);

		// ����ӡ���ť
		JButton tjButton = new JButton("���ҩƷ");
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ʼ��Ʊ��
				initPiaoHao();
				// ���������û�б�д�ĵ�Ԫ
				stopTableCellEditing();
				// �������л��������У������������
				for (int i = 0; i < table.getRowCount(); i++) {
					
					if (table.getValueAt(i, 0) == null)
						return;
				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Vector());
			}
		});
		setupComponet(tjButton, 2, 3, 1, 1, false);

		// �����ۡ���ť
		JButton sellButton = new JButton("����");
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTableCellEditing();// ���������û�б�д�ĵ�Ԫ
				clearEmptyRow();// �������

				String jeStr = hjje.getText();// �ϼƽ��
				String jsfsStr = jsfs.getSelectedItem().toString();// ���㷽ʽ
				String jsrStr = Global.username;// ������
				String czyStr = czy.getText();// ����Ա
				DateFormat ddtf = DateFormat.getDateTimeInstance();  
				xssjDate = new Date();
				//String rkDate = ddtf.format(xssjDae);// ����ʱ��
				
				DateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //����һ����ʽ�����ڶ���
				String punchTime = simpleDateFormat.format(xssjDate);  

				
				String id = piaoHao.getText();// Ʊ��
				String kehuName = kehu.getSelectedItem().toString();// ��Ӧ������

				if (table.getRowCount() <= 0) {
					JOptionPane.showMessageDialog(SaleBill.this, "�������ҩƷ");
					return;
				}
				TbSale sellMain = new TbSale(id, jeStr,  kehuName, punchTime, czyStr, jsrStr, jsfsStr);// ��������
				Set<TbSaleDetail> set = sellMain.getTbSaleDetails();// ���������ϸ�ļ���
				int rows = table.getRowCount();
				for (int i = 0; i < rows; i++) {
					//TbYpinfo ypinfo = (TbYpinfo) table.getValueAt(i, 1);
					String ypname=(String)table.getValueAt(i, 0);
					String ypid=(String)table.getValueAt(i, 1);
					String ypplace=(String)table.getValueAt(i, 2);
					String ypunit=(String)table.getValueAt(i, 3);
					String ypspec=(String)table.getValueAt(i, 4);
					String djStr = (String) table.getValueAt(i, 5);
					String slStr = (String) table.getValueAt(i, 6);
					 
					Double dj = Double.valueOf(djStr);
					Integer sl = Integer.valueOf(slStr);
					TbSaleDetail detail = new TbSaleDetail();// ������ϸ
					detail.setYpid(ypid);// ��ˮ��
					detail.setTbSellMain(sellMain.getSellId());// ��������
					detail.setDj(dj);// ���۵���
					detail.setSl(sl);// ��������
					detail.setName(ypname);
					detail.setPlace(ypplace);
					detail.setUnit(ypunit);
					detail.setSpec(ypspec);
					//System.out.print(sl);
					
					set.add(detail);// ��������ϸ��ӵ�������ϸ�ļ�����
				}
				boolean rs = Dao.insertSellInfo(sellMain);// ���������Ϣ
				if (rs) {
					JOptionPane.showMessageDialog(SaleBill.this, "�������");
					DefaultTableModel dftm = new DefaultTableModel();
					table.setModel(dftm);
					initTable();

					hjje.setText("0");
				}
			}
		});
		setupComponet(sellButton, 2, 5, 1, 100, false);
		// ��Ӵ������������ɳ�ʼ��
		addInternalFrameListener(new initTasks());
		

	}
	// ��ʼ�����
	private void initTable() {
		String[] columnNames = { "ҩƷ����", "ҩƷ���", "��������", "��λ", "���", "����", "����" };
		((DefaultTableModel) table.getModel()).setColumnIdentifiers(columnNames);
		TableColumn column = table.getColumnModel().getColumn(0);
		final DefaultCellEditor editor = new DefaultCellEditor(sp);
		//editor.setClickCountToStart(2);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		
		r.setHorizontalAlignment(JLabel.CENTER);   //������ݾ���
		table.setDefaultRenderer(Object.class, r);
		column.setCellEditor(editor);
	}

	// ��ʼ����Ʒ�����б�
	private void initYpBox() {
		List list = new ArrayList();
		ResultSet set = Dao.query(" select * from tb_ypinfo" + " where id in (select id from tb_ypinfo where quantity>0)");
		sp.removeAllItems();
		sp.addItem(new TbYpinfo());
		for (int i = 0; table != null && i < table.getRowCount(); i++) {
			TbYpinfo tmpInfo = (TbYpinfo) table.getValueAt(i, 0);
			if (tmpInfo != null && tmpInfo.getYpid() != null)
				list.add(tmpInfo.getYpid());
		}
		try {
			while (set.next()) {
				TbYpinfo ypinfo = new TbYpinfo();
				ypinfo.setYpid(set.getString("ypid").trim());
				// ���������Դ���ͬ����Ʒ����Ʒ�������оͲ��ٰ�������Ʒ
				if (list.contains(ypinfo.getYpid()))
					continue;
				ypinfo.setYpname(set.getString("ypname").trim());
				ypinfo.setPlace(set.getString("place").trim());
				ypinfo.setUnit(set.getString("unit").trim());
				ypinfo.setSpec(set.getString("spec").trim());
				ypinfo.setSaleprice(set.getString("saleprice").trim());
				ypinfo.setQuantity(set.getString("quantity").trim());
				ypinfo.setSupplyname(set.getString("supplyname").trim());
				
				sp.addItem(ypinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.weightx=2;
		gridBagConstrains.weighty=2;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5,5, 5, 5);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}



	// ����Ʒ����������Ʒ�������ϼƽ��
	private final class computeInfo implements ContainerListener {
		public void componentRemoved(ContainerEvent e) {

			// �������
			int rows = table.getRowCount();
			int count = 0;
			double money = 0.0;
			
			
			// �����Ʒ�����ͽ��
			for (int i = 0; i < rows; i++) {
				String column6 = (String) table.getValueAt(i, 6);
				String column5 = (String) table.getValueAt(i, 5);
				int c6 = (column6 == null || column6.isEmpty()) ? 0 : Integer.valueOf(column6);
				Double c5 = (column5 == null || column5.isEmpty()) ? 0 : Double.valueOf(column5);
				count += c6;
				money += c5 * c6;
			}
			DecimalFormat df = new DecimalFormat("0.00");
			 df.setRoundingMode(RoundingMode.HALF_UP);
			hjje.setText(df.format(money));
		}

		public void componentAdded(ContainerEvent e) {
		}
	}

	// ����ĳ�ʼ������
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initTimeField();
			initKehuField();
			initPiaoHao();
			initYpBox();
		}
		// ��ʼ���ͻ��ֶ�
		private void initKehuField() {
			List gysInfos = Dao.getKhInfos();
			for (Iterator iter = gysInfos.iterator(); iter.hasNext();) {
				List list = (List) iter.next();
				Item item = new Item();
				item.setId(list.get(0).toString().trim());
				item.setName(list.get(1).toString().trim());
				kehu.addItem(item);
			}
			doKhSelectAction();
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
	}
	// ��ʼ��������Ʊ�š�
	private void initPiaoHao() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		String maxId = Dao.getSellMainMaxId(date);
		piaoHao.setText(maxId);
	}

	// ������Ʒ�����б����±��ǰ�е�����
	private synchronized void updateTable() {
		TbYpinfo ypinfo = (TbYpinfo) sp.getSelectedItem();
		Item item = new Item();
		item.setId(ypinfo.getYpid());
		TbYpinfo kucun = Dao.getYpInfo(item);
		int row = table.getSelectedRow();
		if (row >= 0 && ypinfo != null) {
			table.setValueAt(ypinfo.getYpid(), row, 1);
			table.setValueAt(ypinfo.getYpname(), row, 0);
			table.setValueAt(ypinfo.getUnit(), row, 3);
			table.setValueAt(ypinfo.getSpec(), row, 4);
			table.setValueAt(ypinfo.getSaleprice() + "", row, 5);
			table.setValueAt(ypinfo.getQuantity() + "", row, 6);
			table.setValueAt(ypinfo.getSupplyname(), row, 2);

			table.editCellAt(row, 6);
		}
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
