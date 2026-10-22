public class InvoiceItem {
    String id;
    String desc;
    int qty;
    double unitPrice;
    public InvoiceItem(String i , String d , int q , double uP )
    {
        id=i;
        desc=d;
        qty=q;
        unitPrice=uP;
    }
    public String getId()
    {
        return id;
    }
    public String getDesc()
    {
        return desc;
    }
    public int getQty()
    {
        return qty;
    }
    public void setQty(int q)
    {
        qty=q;
    }
    public double getUnitPrice()
    {
        return unitPrice;
    }
    public void setUnitPrice(double uP)
    {
        unitPrice=uP;
    }
    public double getTotal()
    {
        return unitPrice*qty;
    }
    public  String toString()
    {
        return "InvoiceItem[id=" + id +",desc=" + desc +",qty="+qty+",unitPrice="+ unitPrice +"]";
    }
}
