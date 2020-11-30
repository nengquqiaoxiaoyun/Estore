package cn.web.dto;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public class CartGood {
    private int buynum;
    private String name;
    private double marketprice;
    private double estoreprice;

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(double marketprice) {
        this.marketprice = marketprice;
    }

    public double getEstoreprice() {
        return estoreprice;
    }

    public void setEstoreprice(double estoreprice) {
        this.estoreprice = estoreprice;
    }

    @Override
    public String toString() {
        return "CartGood{" +
                "buynum=" + buynum +
                ", name='" + name + '\'' +
                ", marketprice=" + marketprice +
                ", estoreprice=" + estoreprice +
                '}';
    }
}
