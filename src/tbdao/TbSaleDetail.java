package tbdao;

public class TbSaleDetail implements java.io.Serializable {// ������ϸ��ʵ�����л��ӿڣ�

	private Integer id;// ��ˮ��
	private String tbSellMain;// ��������
	private String ypid;// ��Ʒ���
	private Double dj;// ���۵���
	private Integer sl;// ��������
	private String ypname; 
	private String ypplace;
	private String ypunit;
	private String ypspec;

	public TbSaleDetail() {// ȱʡ���캯��
	}

	public TbSaleDetail(String tbSellMain, String spid, Double dj, Integer sl) {// �������캯��
		this.tbSellMain = tbSellMain;
		this.ypid = spid;
		this.dj = dj;
		this.sl = sl;
	}

	// ʹ��Getters and Setters������������ϸ���˽�����Է�װ����
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTbSellMain() {
		return this.tbSellMain;
	}

	public void setTbSellMain(String tbSellMain) {
		this.tbSellMain = tbSellMain;
	}

	public String getYpid() {
		return this.ypid;
	}

	public void setYpid(String ypid) {
		this.ypid = ypid;
	}

	public Double getDj() {
		return this.dj;
	}

	public void setDj(Double dj) {
		this.dj = dj;
	}

	public Integer getSl() {
		return this.sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public String getName() {
		return this.ypname;
	}

	public void setName(String ypname) {
		this.ypname = ypname;
	}
	public String getUnit() {
		return this.ypunit;
	}

	public void setUnit(String ypunit) {
		this.ypunit = ypunit;
	}
	public String getSpec() {
		return this.ypspec;
	}

	public void setSpec(String ypspec) {
		this.ypspec = ypspec;
	}
	public String getPlace() {
		return this.ypplace;
	}

	public void setPlace(String ypplace) {
		this.ypplace = ypplace;
	}
}