package tbdao;

public class TbKhinfo implements java.io.Serializable {// �ͻ���Ϣ��ʵ�����л��ӿڣ�

	private String id;// �ͻ����
	private String khname;// �ͻ�����


	public TbKhinfo() {// ȱʡ���캯��
	}

	public TbKhinfo(String id) {// ��С���캯��(����)
		this.id = id;
	}

	public TbKhinfo(String id, String khname) {// �������캯��
		this.id = id;
		this.khname = khname;

	}
	
	// ʹ��Getters and Setters�������ͻ���Ϣ���˽�����Է�װ����
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKhname() {
		return this.khname;
	}

	public void setKhname(String khname) {
		this.khname = khname;
	}
}

	