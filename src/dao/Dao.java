package dao;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import Main.Global;
import Main.Item;
//import com.lzw.Item;
//import Dao.model.TbGysinfo;
//import com.lzw.dao.model.TbJsr;
//import com.lzw.dao.model.TbKhinfo;
//import com.lzw.dao.model.TbKucun;
//import com.lzw.dao.model.TbRkthDetail;
//import com.lzw.dao.model.TbRkthMain;
//import com.lzw.dao.model.TbRukuDetail;
//import com.lzw.dao.model.TbRukuMain;
//import com.lzw.dao.model.TbSellDetail;
//import com.lzw.dao.model.TbSellMain;
import tbdao.TbYpinfo;
import tbdao.TbKhinfo;
import tbdao.TbSale;
import tbdao.TbSaleDetail;
//import com.lzw.dao.model.TbXsthDetail;
//import com.lzw.dao.model.TbXsthMain;
import Main.MenuBar;


public class Dao {

	protected static String dbClassName = "com.mysql.jdbc.Driver";// MySQL���ݿ������������
	protected static String dbUrl = "jdbc:mysql://localhost:3306/bfxmis?useUnicode=true&characterEncoding=UTF-8";// ����MySQL���ݿ��·��
	protected static String dbUser = "root";// ����MySQL���ݿ���û���
	protected static String dbPwd = "150018";// ����MySQL���ݿ������
	protected static String dbName = "bfxmis";// ����MySQL���ݿ��е�ʵ��(db_database28)
	protected static String second = null;//
	public static Connection conn = null;// MySQL���ݿ�����Ӷ���

	static {// ��̬��ʼ��Dao��
		try {
			if (conn == null) {
				Class.forName(dbClassName).getDeclaredConstructor().newInstance();// ʵ����MySQL���ݿ������
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);// ����MySQL���ݿ�
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�뽫MySQL��JDBC���������Ƶ�lib�ļ����С�");// �����쳣�󣬵�����ʾ��
			System.exit(-1);// ϵͳֹͣ����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Dao() {
	}




	// ��ȡҩƷ��Ϣ
	public static TbYpinfo getYpInfo(Item item) {
		String where = "ypname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_ypinfo where "
				+ where);
		TbYpinfo ypInfo = new TbYpinfo();
		try {
			if (rs.next()) {
				ypInfo.setYpid(rs.getString("ypid").trim());
				ypInfo.setQuantity(rs.getString("quantity").trim());
				ypInfo.setPlace(rs.getString("place").trim());
				ypInfo.setUnit(rs.getString("unit").trim());
				ypInfo.setSpec(rs.getString("spec").trim());
				ypInfo.setSupplyname(rs.getString("supplyname").trim());
				ypInfo.setSaleprice(rs.getString("saleprice").trim());
				if(Global.flag==2)ypInfo.setSaleprice(rs.getString("supprice").trim());
				
				ypInfo.setYpname(rs.getString("ypname").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ypInfo;
	}
	
	// ��ȡ����ҩƷ��Ϣ
			public static List getYpInfos() {
				List list = findForList("select * from tb_ypinfo");
				return list;
			}

			
			
	// �ɹ�ʱ��ȡ����ҩƷ��Ϣ
	public static List getBuyYpInfos() {
		List list = findForList("select * from tb_ypinfo");
		return list;
	}
	
	
	// �ɹ�ʱ��ȡҩƷ��Ϣ
		public static TbYpinfo getBuyYpInfo(Item item) {
			String where = "ypname='" + item.getName() + "'";
			if (item.getId() != null)
				where = "id='" + item.getId() + "'";
			ResultSet rs = findForResultSet("select * from tb_ypinfo where "
					+ where);
			TbYpinfo ypInfo = new TbYpinfo();
			try {
				if (rs.next()) {
					ypInfo.setYpid(rs.getString("ypid").trim());
					ypInfo.setQuantity(rs.getString("quantity").trim());
					ypInfo.setPlace(rs.getString("place").trim());
					ypInfo.setUnit(rs.getString("unit").trim());
					ypInfo.setSpec(rs.getString("spec").trim());
					ypInfo.setSupplyname(rs.getString("supplyname").trim());
					ypInfo.setSaleprice(rs.getString("supprice").trim());
					
					ypInfo.setYpname(rs.getString("ypname").trim());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ypInfo;
		}
	//��ȡҩƷ����
	public static int getYpquantity(String str) {
		int num=0;
		ResultSet rs = findForResultSet("select * from tb_ypinfo where ypid = '"
				+ str + " '");
		try {
			if (rs.next()) {
				num=Integer.valueOf(rs.getString("quantity").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
		

	public static ResultSet findForResultSet(String sql) {
		if (conn == null)
			return null;
		long time = System.currentTimeMillis();
		ResultSet rs = null;
		try {
			Statement stmt = null;
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			second = ((System.currentTimeMillis() - time) / 1000d) + "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
//
	// �������
	public static boolean insert(String sql) {
		boolean result = false;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// ��������
	public static int update(String sql) {
		int result = 0;
		try {
			Statement stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
//
	public static List findForList(String sql) {
		List<List> list = new ArrayList<List>();
		ResultSet rs = findForResultSet(sql);
		
	
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();
			while (rs.next()) {
				List<String> row = new ArrayList<String>();
				for (int i = 1; i <= colCount; i++) {
					String str = rs.getString(i);
					if (str != null && !str.isEmpty())
						str = str.trim();
					row.add(str);
				}
				list.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// ��ȡ���пͻ���Ϣ
	public static List getKhInfos() {
		List list = findForList("select id,khname from tb_khinfo");
		return list;
	}

	// ��ȡ���й�Ӧ����Ϣ
	public static List getSupplyInfos() {
		List list = findForList("select id,supplyname from tb_supplyinfo");
		return list;
	}

	// ��ȡ�ͻ���Ϣ
	public static TbKhinfo getKhInfo(Item item) {
		String where = "khname='" + item.getName() + "'";
		if (item.getId() != null)
			where = "id='" + item.getId() + "'";
		TbKhinfo info = new TbKhinfo();
		ResultSet set = findForResultSet("select * from tb_khinfo where "
				+ where);
		try {
			if (set.next()) {
				info.setId(set.getString("id").trim());
				info.setKhname(set.getString("khname").trim());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}

	


	// ִ��ָ����ѯ
	public static ResultSet query(String QueryStr) {
		ResultSet set = findForResultSet(QueryStr);
		return set;
	}

	// ִ��ɾ��
	public static int delete(String sql) {
		return update(sql);
	}





	












	

	// ��ȡ�����������ID
	public static String getSellMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_sale", "XS", "sellID");
	}
	


	// �����������������Ϣ
	public static boolean insertSellInfo(TbSale sellMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// ������������¼
			insert("insert into tb_sale values('" + sellMain.getSellId()
					+ "','" + sellMain.getKhname()
					+ "','" + sellMain.getXsdate()
					+ "','" + sellMain.getJe() 
					+ "','" + sellMain.getJsr()
					+ "','" + sellMain.getJsfs()
					+ "','" + sellMain.getCzy()
					+ "','������"
					+ "')");
			Set<TbSaleDetail> rkDetails = sellMain.getTbSaleDetails();
			for (Iterator<TbSaleDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbSaleDetail details = iter.next();
				// ���������ϸ���¼
				insert("insert into tb_sale_detail values('"
						+ sellMain.getSellId() 
						+ "','" + details.getName()
						+ "','" + details.getYpid()
						+ "','" + details.getPlace()
						+ "','" + details.getUnit()
						+ "','" + details.getSpec()
						+ "'," + details.getDj() 
						+ "," + details.getSl() 
						+ ")");
			
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	// ��ȡ�����������ID
	public static String getOutMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_out", "CK", "sellID");
	}
	
	// ����������ӳ�����Ϣ
		public static boolean insertOutInfo(TbSale sellMain) {
			try {
				boolean autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				// ��ӳ��������¼
				insert("insert into tb_out values('" + sellMain.getSellId()
						+ "','" + sellMain.getKhname()
						+ "','" + sellMain.getXsdate()
						+ "','" + sellMain.getJe() 
						+ "','" + sellMain.getJsr()
						+ "','" + sellMain.getJsfs()
						+ "','" + sellMain.getCzy()
						+ "')");
				update("update tb_sale set state = '�ѳ��⣬������' " 
						+ " where sellID='" + Global.xs_number + "'");
				Set<TbSaleDetail> rkDetails = sellMain.getTbSaleDetails();
				for (Iterator<TbSaleDetail> iter = rkDetails.iterator(); iter
						.hasNext();) {
					TbSaleDetail details = iter.next();
					// ��ӳ�����ϸ���¼
					insert("insert into tb_out_detail values('"
							+ sellMain.getSellId() 
							+ "','" + details.getName()
							+ "','" + details.getYpid()
							+ "','" + details.getPlace()
							+ "','" + details.getUnit()
							+ "','" + details.getSpec()
							+ "'," + details.getDj() 
							+ "," + details.getSl() 
							+ ")");
					update("update tb_ypinfo set quantity = quantity - " + details.getSl()
					+ " where ypid='" + details.getYpid() + "'");
					
					
				
				}
				conn.commit();
				conn.setAutoCommit(autoCommit);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		// ��ȡ�����������ID
		public static String getFHMainMaxId(Date date) {
			return getMainTypeTableMaxId(date, "tb_send", "FH", "sellID");
		}
		
		// ����������ӷ�����Ϣ
			public static boolean insertFHInfo(TbSale sellMain) {
				try {
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					// ��ӷ��������¼
					insert("insert into tb_send values('" + sellMain.getSellId()
							+ "','" + sellMain.getKhname()
							+ "','" + sellMain.getXsdate()
							+ "','" + sellMain.getJe() 
							+ "','" + sellMain.getJsr()
							+ "','" + sellMain.getJsfs()
							+ "','" + sellMain.getCzy()
							+ "')");
					update("update tb_sale set state = '�ѷ���' " 
							+ " where sellID='" + Global.xs_number + "'");
					Set<TbSaleDetail> rkDetails = sellMain.getTbSaleDetails();
					for (Iterator<TbSaleDetail> iter = rkDetails.iterator(); iter
							.hasNext();) {
						TbSaleDetail details = iter.next();
						// ��ӷ�����ϸ���¼
						insert("insert into tb_send_detail values('"
								+ sellMain.getSellId() 
								+ "','" + details.getName()
								+ "','" + details.getYpid()
								+ "','" + details.getPlace()
								+ "','" + details.getUnit()
								+ "','" + details.getSpec()
								+ "'," + details.getDj() 
								+ "," + details.getSl() 
								+ ")");
						
						
					
					}
					conn.commit();
					conn.setAutoCommit(autoCommit);
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
			
			
			// ��ȡ�ɹ��������ID
			public static String getBuyMainMaxId(Date date) {
				return getMainTypeTableMaxId(date, "tb_buy", "CG", "sellID");
			}
			


			// �����������������Ϣ
			public static boolean insertBuyInfo(TbSale sellMain) {
				try {
					boolean autoCommit = conn.getAutoCommit();
					conn.setAutoCommit(false);
					// ������������¼
					insert("insert into tb_buy values('" + sellMain.getSellId()
							+ "','" + sellMain.getKhname()
							+ "','" + sellMain.getXsdate()
							+ "','" + sellMain.getJe() 
							+ "','" + sellMain.getJsr()
							+ "','" + sellMain.getJsfs()
							+ "','" + sellMain.getCzy()
							+ "','�����"
							+ "')");
					Set<TbSaleDetail> rkDetails = sellMain.getTbSaleDetails();
					for (Iterator<TbSaleDetail> iter = rkDetails.iterator(); iter
							.hasNext();) {
						TbSaleDetail details = iter.next();
						// ���������ϸ���¼
						insert("insert into tb_buy_detail values('"
								+ sellMain.getSellId() 
								+ "','" + details.getName()
								+ "','" + details.getYpid()
								+ "','" + details.getPlace()
								+ "','" + details.getUnit()
								+ "','" + details.getSpec()
								+ "'," + details.getDj() 
								+ "," + details.getSl() 
								+ ")");
					
					}
					conn.commit();
					conn.setAutoCommit(autoCommit);
				} catch (SQLException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
			
			
			
			// ��ȡ����������ID
						public static String getInMainMaxId(Date date) {
							return getMainTypeTableMaxId(date, "tb_in", "RK", "sellID");
						}
						


						
						// �����������������Ϣ
						public static boolean insertInInfo(TbSale sellMain) {
							try {
								boolean autoCommit = conn.getAutoCommit();
								conn.setAutoCommit(false);
								// ������������¼
								insert("insert into tb_in values('" + sellMain.getSellId()
										+ "','" + sellMain.getKhname()
										+ "','" + sellMain.getXsdate()
										+ "','" + sellMain.getJe() 
										+ "','" + sellMain.getJsr()
										+ "','" + sellMain.getJsfs()
										+ "','" + sellMain.getCzy()
										+ "')");
								update("update tb_buy set state = '�����' " 
										+ " where sellID='" + Global.xs_number + "'");
								Set<TbSaleDetail> rkDetails = sellMain.getTbSaleDetails();
								for (Iterator<TbSaleDetail> iter = rkDetails.iterator(); iter
										.hasNext();) {
									TbSaleDetail details = iter.next();
									// ���������ϸ���¼
									insert("insert into tb_in_detail values('"
											+ sellMain.getSellId() 
											+ "','" + details.getName()
											+ "','" + details.getYpid()
											+ "','" + details.getPlace()
											+ "','" + details.getUnit()
											+ "','" + details.getSpec()
											+ "'," + details.getDj() 
											+ "," + details.getSl() 
											+ ")");
									update("update tb_ypinfo set quantity = quantity + " + details.getSl()
									+ " where ypid='" + details.getYpid() + "'");
								
								}
								conn.commit();
								conn.setAutoCommit(autoCommit);
							} catch (SQLException e) {
								e.printStackTrace();
								return false;
							}
							return true;
						}						
	// ��ȡ�����������ID
	private static String getMainTypeTableMaxId(Date date, String table,
			String idChar, String idName) {
		String dateStr = date.toString().replace("-", "");
		String id = idChar + dateStr;
		String sql = "select max(" + idName + ") from " + table + " where "
				+ idName + " like '" + id + "%'";
		ResultSet set = query(sql);
		String baseId = null;
		try {
			if (set.next())
				baseId = set.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		baseId = baseId == null ? "000" : baseId.substring(baseId.length() - 3);
		int idNum = Integer.parseInt(baseId) + 1;
		id += String.format("%03d", idNum);
		return id;
	}
	// ��ȡ�ɹ��ƻ��������ID
				public static String getPlanBuyMainMaxId(Date date) {
					return getMainTypeTableMaxId(date, "tb_planbuy", "PCG", "sellID");
				}
	// ����������Ӳɹ��ƻ�����Ϣ
	public static boolean insertPlanBuyInfo(TbSale sellMain) {
		try {
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			// ��ӳ��������¼
			insert("insert into tb_planbuy values('" + sellMain.getSellId()
					
					+ "','" + sellMain.getXsdate()
					+ "','" + sellMain.getJe() 
					+ "','" + sellMain.getJsr()
					+ "','" + sellMain.getJsfs()
					+ "','" + sellMain.getCzy()
					+"','������"
					+ "')");
			update("update tb_sale set state = '�ֿ�ȱ����������' " 
					+ " where sellID='" + Global.xs_number + "'");
			Set<TbSaleDetail> rkDetails = sellMain.getTbSaleDetails();
			for (Iterator<TbSaleDetail> iter = rkDetails.iterator(); iter
					.hasNext();) {
				TbSaleDetail details = iter.next();
				// ��ӳ�����ϸ���¼
				insert("insert into tb_planbuy_detail values('"
						+ sellMain.getSellId() 
						+ "','" + details.getName()
						+ "','" + details.getYpid()
						+ "','" + details.getPlace()
						+ "','" + details.getUnit()
						+ "','" + details.getSpec()
						+ "'," + details.getDj() 
						+ "," + details.getSl() 
						+ ")");
				
				
			
			}
			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}





	// ��ȡ��ⵥ�����ID����������Ʊ��
	public static String getRuKuMainMaxId(Date date) {
		return getMainTypeTableMaxId(date, "tb_ruku_main", "RK", "rkid");
	}


	// ��ȡ���۵�������Ϣ
	public static TbYpinfo getXSInfo(Item item,String str) {
		String where = null;
		if (item.getId() != null)
			where = "ypid='" + item.getId() + "'";
		ResultSet rs = findForResultSet("select * from tb_sale_detail where "
				+ "sellID = '"+str+"' and "+where);
		TbYpinfo ypInfo = new TbYpinfo();
		try {
			if (rs.next()) {
				ypInfo.setYpid(rs.getString("ypid").trim());
				ypInfo.setQuantity(rs.getString("sl").trim());
				
				ypInfo.setPlace(rs.getString("place").trim());
				ypInfo.setUnit(rs.getString("unit").trim());
				ypInfo.setSpec(rs.getString("spec").trim());
				ypInfo.setSaleprice(rs.getString("dj").trim());
				ypInfo.setYpname(rs.getString("ypname").trim());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ypInfo;
	}
	
	public static List getXSInfos() {
		List list = findForList("select * from tb_sale_detail");
		return list;
	}
	
	// ��ȡ������������Ϣ
		public static TbYpinfo getFHInfo(Item item,String str) {
			String where = null;
			if (item.getId() != null)
				where = "ypid='" + item.getId() + "'";
			ResultSet rs = findForResultSet("select * from tb_send_detail where "
					+ "sellID = '"+str+"' and "+where);
			TbYpinfo ypInfo = new TbYpinfo();
			try {
				if (rs.next()) {
					ypInfo.setYpid(rs.getString("ypid").trim());
					ypInfo.setQuantity(rs.getString("sl").trim());
					
					ypInfo.setPlace(rs.getString("place").trim());
					ypInfo.setUnit(rs.getString("unit").trim());
					ypInfo.setSpec(rs.getString("spec").trim());
					ypInfo.setSaleprice(rs.getString("dj").trim());
					ypInfo.setYpname(rs.getString("ypname").trim());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ypInfo;
		}
		
		public static List getFHInfos() {
			List list = findForList("select * from tb_send_detail");
			return list;
		}
	
	
		
		// ��ȡ���ⵥ������Ϣ
				public static TbYpinfo getCKInfo(Item item,String str) {
					String where = null;
					if (item.getId() != null)
						where = "ypid='" + item.getId() + "'";
					ResultSet rs = findForResultSet("select * from tb_out_detail where "
							+ "sellID = '"+str+"' and "+where);
					TbYpinfo ypInfo = new TbYpinfo();
					try {
						if (rs.next()) {
							ypInfo.setYpid(rs.getString("ypid").trim());
							ypInfo.setQuantity(rs.getString("sl").trim());
							
							ypInfo.setPlace(rs.getString("place").trim());
							ypInfo.setUnit(rs.getString("unit").trim());
							ypInfo.setSpec(rs.getString("spec").trim());
							ypInfo.setSaleprice(rs.getString("dj").trim());
							ypInfo.setYpname(rs.getString("ypname").trim());
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return ypInfo;
				}
				
				public static List getCKInfos() {
					List list = findForList("select * from tb_out_detail");
					return list;
				}
		// ��ȡ��ⵥ������Ϣ
				public static TbYpinfo getRKInfo(Item item,String str) {
					String where = null;
					if (item.getId() != null)
						where = "ypid='" + item.getId() + "'";
					ResultSet rs = findForResultSet("select * from tb_in_detail where "
							+ "sellID = '"+str+"' and "+where);
					TbYpinfo ypInfo = new TbYpinfo();
					try {
						if (rs.next()) {
							ypInfo.setYpid(rs.getString("ypid").trim());
							ypInfo.setQuantity(rs.getString("sl").trim());
							
							ypInfo.setPlace(rs.getString("place").trim());
							ypInfo.setUnit(rs.getString("unit").trim());
							ypInfo.setSpec(rs.getString("spec").trim());
							ypInfo.setSaleprice(rs.getString("dj").trim());
							ypInfo.setYpname(rs.getString("ypname").trim());
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return ypInfo;
				}
				
				public static List getRKInfos() {
					List list = findForList("select * from tb_in_detail");
					return list;
				}
	// ��ȡ�ɹ��ƻ���������Ϣ
		public static TbYpinfo getPlanBuyInfo(Item item,String str) {
			String where = "ypname='" + item.getName() + "'";
			if (item.getId() != null)
				where = "ypid='" + item.getId() + "'";
			ResultSet rs = findForResultSet("select * from tb_planbuy_detail where "
					+ "sellID = '"+str+"' and "+where);
			TbYpinfo ypInfo = new TbYpinfo();
			try {
				if (rs.next()) {
					ypInfo.setYpid(rs.getString("ypid").trim());
					ypInfo.setQuantity(rs.getString("sl").trim());
					ypInfo.setPlace(rs.getString("place").trim());
					ypInfo.setUnit(rs.getString("unit").trim());
					ypInfo.setSpec(rs.getString("spec").trim());
					ypInfo.setSaleprice(rs.getString("dj").trim());
					ypInfo.setYpname(rs.getString("ypname").trim());
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return ypInfo;
		}
		
		public static List getBuyInfos() {
			List list = findForList("select * from tb_buy_detail");
			return list;
		}
		
		//��ȡ�ɹ�������
		public static TbYpinfo getBuyInfo(Item item,String str) {
			String where = "ypname='" + item.getName() + "'";
			if (item.getId() != null)
				where = "ypid='" + item.getId() + "'";
			ResultSet rs = findForResultSet("select * from tb_buy_detail where "
					+ "sellID = '"+str+"' and "+where);
			TbYpinfo ypInfo = new TbYpinfo();
			try {
				if (rs.next()) {
					ypInfo.setYpid(rs.getString("ypid").trim());
					ypInfo.setQuantity(rs.getString("sl").trim());
					ypInfo.setPlace(rs.getString("place").trim());
					ypInfo.setUnit(rs.getString("unit").trim());
					ypInfo.setSpec(rs.getString("spec").trim());
					ypInfo.setSaleprice(rs.getString("dj").trim());
					ypInfo.setYpname(rs.getString("ypname").trim());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ypInfo;
		}
		
		public static List getPlanBuyInfos() {
			List list = findForList("select * from tb_planbuy_detail");
			return list;
		}
	

	// ��֤��¼
	public static boolean checkLogin(String userStr, String passStr)
			throws SQLException {
		ResultSet rs = findForResultSet("select * from tb_userinfo where user='"
				+ userStr + "'");
		
		
		try {
			if(rs.next()) {
				Global.userID=rs.getString("identity").trim();
				Global.username=rs.getString("user").trim();
			}
				
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//JOptionPane.showMessageDialog(null, "You input is "+Global.userID, Global.userID, JOptionPane.PLAIN_MESSAGE);
		
		rs = findForResultSet("select * from tb_userinfo where user='"
				+ userStr + "' and password='" + passStr + "'");
		if (rs == null)
			return false;
		
		return rs.next();
	}

}
