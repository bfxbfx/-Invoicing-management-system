package jframe;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Main.Global;
import dao.Dao;
import tbdao.TbJsr;


public class SendMain extends JInternalFrame{// ���۲�ѯ�ڲ�����
	
	private JButton queryButton;// ����ѯ����ť
	private JTextField endDate;// ����ֹ���ڡ��ı���
	private JTextField startDate;// ����ʼ���ڡ��ı���
	private JTable table;// ����ģ��
	private JTextField content;// ���������ݡ��ı���
	private JComboBox operation;// ���������㡱�����б�
	private JComboBox condition;// ���������ơ������б�
	private TbJsr user;// ��������Ϣ
	private DefaultTableModel dftm;// ����ģ�͵�ʵ�������� 
	private JCheckBox selectDate;// ��ָ����ѯ���ڡ���ѡ��
	

	
	public SendMain() {// ���۲�ѯ�ڲ�����Ĺ��췽��
		// �����۲�ѯ�ڲ�����ʱ�����á���ʼ���ڡ��͡���ֹ���ڡ�
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameActivated(final InternalFrameEvent e) {
				//Calendar cd = Calendar.getInstance();
				
				java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
				endDate.setText(date.toString());
//				date.setMonth(0);
//				date.setDate(1);
				startDate.setText(date.toString());
			}
		});
		setIconifiable(true);// ����ͼ�껯
		setClosable(true);// ���Թر�
		setTitle("�ѳ������۵���ѯ");// �������۲�ѯ�ڲ�����ı���
		getContentPane().setLayout(new GridBagLayout());// �������۲�ѯ�ڲ�����Ĳ��������񲼾�
		setBounds(0, 0, 800, 600);// �������۲�ѯ�ڲ������λ�úͿ��� 
		// ��ѡ���ѯ��������ǩ�͡��������ơ������б�
		setupComponet(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		condition = new JComboBox();
		condition.setModel(new DefaultComboBoxModel(new String[] {"�ͻ�ȫ��", "����Ʊ��"}));
		setupComponet(condition, 1, 0, 1, 10, true);
		
		// ���������ݡ��ı���
		content = new JTextField();
		content.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				if(e.getKeyCode()==10) {
					queryButton.doClick();
				}
			}
		});
		setupComponet(content, 2, 0, 1, 100, true);
		// ����ѯ����ť
		queryButton = new JButton();
		queryButton.addActionListener(new QueryAction());
		setupComponet(queryButton, 1, 3, 1, 1, false);
		queryButton.setText("  ��ѯ  ");
		// ��ָ����ѯ���ڡ���ѡ��
		selectDate = new JCheckBox();
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.ipadx = 10;
		gridBagConstraints_7.anchor = GridBagConstraints.EAST;
		gridBagConstraints_7.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_7.gridy = 1;
		gridBagConstraints_7.gridx = 0;
		
		getContentPane().add(selectDate, gridBagConstraints_7);
		// ��ָ����ѯ����    �ӡ���ǩ
		final JLabel label_1 = new JLabel();
		label_1.setText("�Ƿ�ָ����ѯ���ڣ�     ��");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();

		gridBagConstraints_8.gridy = 1;
		gridBagConstraints_8.gridx = 1;
		getContentPane().add(label_1, gridBagConstraints_8);
		// ����ʼ���ڡ��ı���
		startDate = new JTextField();
		startDate.setPreferredSize(new Dimension(100,21));
		setupComponet(startDate, 2, 1, 1, 1, true);
		// �������ı���
		final JLabel label_dao = new JLabel();
		label_dao.setText("��      ");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.anchor = GridBagConstraints.EAST;
		gridBagConstraints_9.gridy = 2;
		gridBagConstraints_9.gridx = 1;
		getContentPane().add(label_dao, gridBagConstraints_9);
		// ����ֹ���ڡ��ı���
		endDate = new JTextField();
		endDate.setPreferredSize(new Dimension(100,21));
		setupComponet(endDate, 2, 2, 1, 1, true);
		
		
		// ��Ϊ�����۵Ŀ��ַ��ı���
		final JLabel label_eee = new JLabel();
		label_eee.setText("                ");
		final GridBagConstraints gridBagConstraints_e = new GridBagConstraints();
		gridBagConstraints_e.anchor = GridBagConstraints.EAST;
		gridBagConstraints_e.gridy = 2;
		gridBagConstraints_e.gridx = 4;
		getContentPane().add(label_eee, gridBagConstraints_e);
		
		// ��Ϊ�����۵Ŀ��ַ��ı���
		final JLabel label_qqq = new JLabel();
		label_qqq.setText("                ");
		final GridBagConstraints gridBagConstraints_q = new GridBagConstraints();
		gridBagConstraints_q.anchor = GridBagConstraints.EAST;
		gridBagConstraints_q.gridy = 2;
		gridBagConstraints_q.gridx = 5;
		getContentPane().add(label_qqq, gridBagConstraints_q);
		// ����ʾȫ�����ݡ���ť
		final JButton showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				content.setText("");
				List list=Dao.findForList("select * from tb_sale where state = '�ѳ��⣬������'");
				Iterator iterator=list.iterator();
				updateTable(iterator);
			}
		});
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_5.gridy = 3;
		gridBagConstraints_5.gridx = 2;
		getContentPane().add(showAllButton, gridBagConstraints_5);
		showAllButton.setFont(new Font("", Font.PLAIN, 12));
		showAllButton.setText("��ʾȫ���ѳ������۵�");
		// �������
		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0, 10, 5, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 9;
		gridBagConstraints_6.gridy = 4;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);
		// ����ģ��
		table = new JTable();
		table.setEnabled(false);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[]{"   ����Ʊ��", "   �ͻ�ȫ��", "      ��������","    ���","    ������", "    ���㷽ʽ","   ����Ա",};
		dftm.setColumnIdentifiers(tableHeads);
		TableColumn column = table.getColumnModel().getColumn(2);//0�Ǵ����ĵ�һ��
		column.setPreferredWidth(120);
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
		
		r.setHorizontalAlignment(JLabel.CENTER);   //�������ݾ���
		table.setDefaultRenderer(Object.class, r);
		scrollPane.setViewportView(table);
		
		//˫��������
		table.addMouseListener(new MouseAdapter(){ 

		      public void mouseClicked(MouseEvent e) { 
		    	  if(e.getClickCount() == 2) {
		    		   int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint()); //�����λ�� 
		    	  		//content.setText(column); 
		    	  		Global.xs_number=(String) table.getValueAt(row, 0);
		    	  		Global.xs_KHname=(String) table.getValueAt(row, 1);
		    	  		Global.xs_date=(String) table.getValueAt(row, 2);
		    	  		Global.xs_jr=(String) table.getValueAt(row, 3);
		    	  		Global.xs_jsr=(String) table.getValueAt(row, 4);
		    	  		Global.xs_jsfs=(String) table.getValueAt(row, 5);
		    	  		Global.xs_czy=(String) table.getValueAt(row, 6);
		    	  		
		    	  		JFrame inFrm = new SendDetail(); 
		    	  		inFrm.setVisible(true);
		    	  		
		    	  		
		    	  		
		    	  		//this.add(XiaoShouXiangQin.class);
		    	  		 
		    	  }

		    	  else return; 
		      }} );
	}
	// ���±�������
	private void updateTable(Iterator iterator) {
		int rowCount=dftm.getRowCount();
		for(int i=0;i<rowCount;i++) {
			dftm.removeRow(0);
		}
		while(iterator.hasNext()) {
			Vector vector=new Vector();
			List view=(List) iterator.next();
			vector.addAll(view);
			dftm.addRow(vector);
		}
	}
	
	// �������λ�ò����ӵ�������
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
		gridBagConstrains.insets = new Insets(5, 5, 5,5);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	
	
	// ������ѯ
	private final class QueryAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			boolean selDate = selectDate.isSelected();
			if(content.getText().equals("")) {
				JOptionPane.showMessageDialog(getContentPane(), "�������ѯ���ݣ�");
				return;
			}
			if(selDate) {
				if(startDate.getText()==null||startDate.getText().equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "�������ѯ�Ŀ�ʼ���ڣ�");
					return;
				}
				if(endDate.getText()==null||endDate.getText().equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "�������ѯ�Ľ������ڣ�");
					return;
				}
			}
			List list=null;// �����
			String con = condition.getSelectedIndex() == 0 ? "khname " : "sellID ";
			String opstr = " = " ;
			String cont = content.getText();
			list = Dao.findForList("select * from tb_sale where "
					+ con
					+ opstr
					+("state = '�ѳ��⣬������'")
					+ "'"+cont+"'"
					+ (selDate ? " and xsdate>'" + startDate.getText()
							+ "' and xsdate<='" + endDate.getText()+" 23:59:59'" : ""));// ִ��ƴ�ӵ�SQL�����õĽ����
			Iterator iterator = list.iterator();// ������list��Ӧ�ĵ�����
			updateTable(iterator);
		}
	}

}