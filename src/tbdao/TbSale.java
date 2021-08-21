package tbdao;

import java.util.HashSet;
import java.util.Set;

public class TbSale implements java.io.Serializable {// ��������ʵ�����л��ӿڣ�

	private String sellId;// ���۱��
	private String je;// �ܼƽ��
	private String khname;// �ͻ�����
	private String xsdate;// ��������
	private String czy;// ����Ա
	private String jsr;// ������
	private String jsfs;// ���㷽ʽ
	private Set tbSaleDetails = new HashSet(0);// ������ϸ 

	public TbSale() {// ȱʡ���캯��
	}

	public TbSale(String sellId,  String je, String khname, String xsdate, String czy,
			String jsr, String jsfs) {// �������캯��
		this.sellId = sellId;
		this.je = je;
		this.khname = khname;
		this.xsdate = xsdate;
		this.czy = czy;
		this.jsr = jsr;
		this.jsfs = jsfs;
		this.tbSaleDetails = tbSaleDetails;
	}

	// ʹ��Getters and Setters�����������������˽�����Է�װ����
	public String getSellId() {
		return this.sellId;
	}

	public void setSellId(String sellId) {
		this.sellId = sellId;
	}



	public String getJe() {
		return this.je;
	}

	public void setJe(String je) {
		this.je = je;
	}



	public String getKhname() {
		return this.khname;
	}

	public void setKhname(String khname) {
		this.khname = khname;
	}

	public String getXsdate() {
		return this.xsdate;
	}

	public void setXsdate(String xsdate) {
		this.xsdate = xsdate;
	}

	public String getCzy() {
		return this.czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getJsr() {
		return this.jsr;
	}

	public void setJsr(String jsr) {
		this.jsr = jsr;
	}

	public String getJsfs() {
		return this.jsfs;
	}

	public void setJsfs(String jsfs) {
		this.jsfs = jsfs;
	}

	public Set getTbSaleDetails() {
		return this.tbSaleDetails;
	}

	public void setTbSaleDetails(Set tbSaleDetails) {
		this.tbSaleDetails = tbSaleDetails;
	}
}