package cn.web.entity;

import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private int uid;
    private double totalprice;
    //ʡ���أ���ϸ��ַ���������룬�������绰��ƴ����һ��������ݿ�Ҫ����ĵ�ַ
    private String address;
    private int status; // 1:δ֧�� 2����֧�� 3���ѹ���
    private Date createtime;

    /*
     * ÿ����������Ӧ�Լ���һЩ��ϸ��Ϣ�������ǡ����׵ġ�
     * ��ˣ��ڶ�������������һ��������ϸ�ļ��ϣ�������ҳ������ʾ
     */
    private List<OrderItems> oiList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<OrderItems> getOiList() {
        return oiList;
    }

    public void setOiList(List<OrderItems> oiList) {
        this.oiList = oiList;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", uid=" + uid + ", totalprice=" + totalprice + ", address="
                + address + ", status=" + status + ", createtime=" + createtime + ", oiList="
                + oiList + "]";
    }

}
