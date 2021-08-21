package jframe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Main.Global;
import Main.Item;
import dao.Dao;
import tbdao.TbYpinfo;

public class YpQuery extends JInternalFrame {// ��Ʒ��ѯ�ڲ�����
	
	private JTable table;// ���ģ��
	private JTextField conditionContent;// ���������ݡ��ı���
	private JComboBox conditionOperation;// ���������㡱�����б�
	private JComboBox conditionName;// ���������ơ������б�
	
	public YpQuery() {// ��Ʒ��ѯ�ڲ�����Ĺ��췽��
		super();// ���ø���JInternalFrame�Ĺ��췽��
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թر�
		setTitle("ҩƷ��Ϣ��ѯ");// ������Ʒ��ѯ�ڲ�����ı��� 
		getContentPane().setLayout(new GridBagLayout());// ������Ʒ��ѯ�ڲ�����Ĳ���Ϊ���񲼾�
		setBounds(200, 200, 800, 600);// ������Ʒ��ѯ�ڲ������λ�úͿ��
		// ���ģ��
		table = new JTable();
		table.setEnabled(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ���Զ������еĿ�ȣ�ʹ�ù�����
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[]{ "ҩƷ���","ҩƷ����", "���","��λ", "��������","�������","����","���۵���"};
		if(Global.flag==2)tableHeads = new String[]{ "ҩƷ���","ҩƷ����", "���","��λ", "��������","�������","����","����"};
		dftm.setColumnIdentifiers(tableHeads);
		// �������
		final JScrollPane scrollPane = new JScrollPane(table);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		
		r.setHorizontalAlignment(JLabel.CENTER);   //������ݾ���
		table.setDefaultRenderer(Object.class, r);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 0, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
		// ��ѡ���ѯ��������ǩ�͡��������ơ������б�
		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"ҩƷ����" ,"ҩƷ���","��������", "����"}));
		setupComponet(conditionName, 1, 0, 1, 80, true);
//		// ���������㡱�����б�
//		conditionOperation = new JComboBox();
//		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"����", "����"}));
//		setupComponet(conditionOperation, 2, 0, 1, 30, true);
		// ���������ݡ��ı���
		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 200, true);
		// ����ѯ����ť
		final JButton queryButton = new JButton();
		queryButton.addActionListener(new QueryAction(dftm));
		setupComponet(queryButton, 4, 0, 1, 60, false);
		queryButton.setText("��ѯ");
		// ����ʾȫ�����ݡ���ť
		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = Dao.getYpInfos();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 50, false);
		showAllButton.setText("��ʾȫ������");
	}
	// �������ʾȫ�����ݡ���ť�󣬸��±������
	private void updateTable(List list, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++)
			dftm.removeRow(0);
		Iterator iterator = list.iterator();
		TbYpinfo ypInfo;// ��Ʒ��Ϣ
		while (iterator.hasNext()) {
			List info = (List) iterator.next();
			Item item = new Item();
			item.setId((String) info.get(0));
			item.setName((String) info.get(1));
			ypInfo = Dao.getYpInfo(item);
			Vector rowData = new Vector();
			//JOptionPane.showMessageDialog(null, "You input is "+ypInfo.getYpname().trim(), ypInfo.getYpname().trim(), JOptionPane.PLAIN_MESSAGE);
			rowData.add(ypInfo.getYpid().trim());// ��Ʒ���
			rowData.add(ypInfo.getYpname().trim());// ��Ʒ��
			rowData.add(ypInfo.getSpec());// ��Ʒ���
			
			rowData.add(ypInfo.getUnit());// ��Ʒ������λ
			
			
			
			rowData.add(ypInfo.getSupplyname());// ��������
			rowData.add(ypInfo.getQuantity());// ����
			rowData.add(ypInfo.getPlace());// ����
			rowData.add(ypInfo.getSaleprice());// ����
			dftm.addRow(rowData);// ���������������ݣ���Ʒ��Ϣ��
		}
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	// ������ѯ
	private final class QueryAction implements ActionListener {
		private final DefaultTableModel dftm;
		private QueryAction(DefaultTableModel dftm) {
			this.dftm = dftm;
		}
		public void actionPerformed(final ActionEvent e) {
			String conName,  content;
			conName = conditionName.getSelectedItem().toString().trim();
			content = conditionContent.getText().trim();
			List list = null;
			list = searchInfo(conName,  content, list);
			updateTable(list, dftm);
		}
		//���ִ��SQL������Ӧ�Ľ����
		private List searchInfo(String conName, String content, List list) {
			String sql = "select * from tb_ypinfo where ";
			
				if (conName.equals("ҩƷ����")) {
					
					list = Dao.findForList(sql + "ypname='" + content + "'");
				
				}
				if (conName.equals("ҩƷ���"))
					list = Dao.findForList(sql + "ypid='" + content + "'");
				if (conName.equals("��������"))
					list = Dao.findForList(sql + "supplyname='" + content + "'");
				if (conName.equals("����"))
					list = Dao.findForList(sql + "place='" + content + "'");
				
			
			return list;
		}
	}
}
