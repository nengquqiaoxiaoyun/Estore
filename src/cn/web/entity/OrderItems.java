package cn.web.entity;

public class OrderItems {
	private String oid;
	private int gid;
	private int buynum;

	/*
	 * ������ϸ�в�������Ʒ����Ϣ������ҳ������Ҫ��ʾ�� �ڶ�����ϸ�������Ʒ�Ķ���
	 * ����֮��һһ��Ӧ�Ĺ�ϵ�����ƹ��ﳵ����
	 */
	private Good good;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	@Override
	public String toString() {
		return "OrderItems [oid=" + oid + ", gid=" + gid + ", buynum=" + buynum + ", good=" + good
				+ "]";
	}
}
