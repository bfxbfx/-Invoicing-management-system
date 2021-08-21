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
import Main.*;
import dao.Dao;
import tbdao.*;

public class InQueryDetail extends JFrame {// ���۵��ڲ�����

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
	

	public InQueryDetail() {// ���۵��ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ�����
//		setMaximizable(true);// �������
//		setIconifiable(true);// ����ͼ�껯
//		setClosable(true);// ���Թر����۵��ڲ�����
		getContentPane().setLayout(new GridBagLayout());// �������۵��ڲ�����Ĳ���Ϊ���񲼾�
		getContentPane().setBackground(new Color(255,245,238));
		
		setTitle("��ⵥ������");// �������۵��ڲ�����ı���
		setBounds(0, 0, 700, 350);// �������۵��ڲ������λ�úͿ��
		Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth/2-700/2, screenHeight/2-350/2);//���ô��ھ�����ʾ
		// ������Ʊ�š���ǩ�͡�����Ʊ�š��ı���


		setupComponet(new JLabel("��ⵥ�ţ�"), 0,0,1,0,false);
		
		setupComponet(new JLabel(Global.xs_number), 1, 0, 1, 80, true);
		// ���ͻ�����ǩ�͡��ͻ��������б�
		setupComponet(new JLabel("               ��Ӧ�̣�"), 2, 0, 1, 0, false);
		setupComponet(new JLabel(Global.xs_KHname), 3, 0, 1, 80, true);
		
		
		// �����㷽ʽ����ǩ�͡����㷽ʽ�������б�
		setupComponet(new JLabel("���㷽ʽ��"), 0, 1, 1, 0, false);
		setupComponet(new JLabel(Global.xs_jsfs), 1, 1, 1, 1, true);

		// ������ʱ�䡱��ǩ�͡�����ʱ�䡱�ı���
		setupComponet(new JLabel("               ���ʱ�䣺"), 2, 1, 1, 0, false);
		setupComponet(new JLabel(Global.xs_date), 3, 1, 1, 1, true);
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
		
		// ���ϼƽ���ǩ�͡��ϼƽ��ı���
		setupComponet(new JLabel("�ϼƽ�"), 0, 4, 1, 0, false);
		setupComponet(new JLabel(Global.xs_jr), 1, 4, 1, 1, true);
		
		// ��������Ա����ǩ�͡�������Ա���ı���
		setupComponet(new JLabel("               ������Ա��"), 2, 4, 1, 0, false);
		
		setupComponet(new JLabel(Global.xs_czy), 3, 4, 1, 1, true);

		

		//JOptionPane.showMessageDialog(null, "������ʾ"); 
		// ��Ӵ������������ɳ�ʼ��
		//addInternalFrameListener(new initTasks());
		

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
	private final class initTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			
		}

		

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
			ypInfo = Dao.getRKInfo(item,Global.xs_number);
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
	String sql = "select * from tb_in_detail where ";
	
			
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
