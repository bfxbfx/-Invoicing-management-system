package tbdao;


public class TbYpinfo implements java.io.Serializable {// ��Ʒ��Ϣ

	private String ypid;// ҩƷ���
	private String ypname;// ҩƷ����
	private String place;// ����
	private String unit;// ��λ
	private String spec;// ���
	private String saleprice;// ���۵���
	private String quantity;// ����
	private String supplyname;// ��Ӧ������
	private String waterid;//��ˮ��
	
	// ˽�����Է�װ����
	public TbYpinfo() {
	}

	public TbYpinfo(String ypid) {
		this.ypid = ypid;
	}

	public String getYpid() {
		return this.ypid;
	}

	public void setYpid(String ypid) {
		this.ypid = ypid;
	}

	public String getYpname() {
		return this.ypname;
	}

	public void setYpname(String ypname) {
		this.ypname = ypname;
	}

	public String getSaleprice() {
		return this.saleprice;
	}

	public void setSaleprice(String saleprice) {
		this.saleprice = saleprice;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	

	public String getSupplyname() {
		return this.supplyname;
	}

	public void setSupplyname(String supplyname) {
		this.supplyname = supplyname;
	}
	
	public String getWaterid() {
		return this.waterid;
	}

	public void setWaterid(String waterid) {
		this.waterid = waterid;
	}

	public String toString() {
		return getYpname();
	}



	@Override
	public boolean equals(Object obj) {// ��дequals����
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TbYpinfo other = (TbYpinfo) obj;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		if (supplyname == null) {
			if (other.supplyname != null)
				return false;
		} else if (!supplyname.equals(other.supplyname))
			return false;
		if (ypid == null) {
			if (other.ypid != null)
				return false;
		} else if (!ypid.equals(other.ypid))
			return false;
		if (saleprice == null) {
			if (other.saleprice != null)
				return false;
		} else if (!saleprice.equals(other.saleprice))
			return false;
		
		if (ypname == null) {
			if (other.ypname != null)
				return false;
		} else if (!ypname.equals(other.ypname))
			return false;
		return true;
	}

}