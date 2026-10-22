public class Book 
{
    String isbn;
    String name;
    Author author;
    double price;
    int qty=0;
    public Book(String i,String n,Author a,double p)
    {
        isbn=i;
        name=n;
        author=a;
        price=p;
    }
    public Book(String i,String n,Author a,double p,int q)
    {
        isbn=i;
        name=n;
        author=a;
        price=p;
        qty=q;
    }
    public String getIsbn()
    {
        return isbn;
    }
    public String getName()
    {
        return name;
    }
    public Author getAuthor()
    {
        return author;
    }
    public double getPrice()
    {
        return price;
    }
    public void setPrice(double p)
    {
        price=p;
    }
    public int getQty()
    {
        return qty;
    }
    public void setQty(int q )
    {
        qty=q;
    }
    public String getAuthorName()
    {
        return author.name;
    }
    public String toString()
    {
        return "Book[isbn="+isbn+",name="+name+author.toString()+",price="+price+",qty="+qty+"]";
    }
    
}
