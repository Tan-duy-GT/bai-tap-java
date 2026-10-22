public class Date 
{
    int day;
    int month;
    int year;
    public Date(int d,int m,int y)
    {
        day=d;
        month=m;
        year=y;
    }
    public int getDay()
    {
        return day;
    }
    public int getMonth()
    {
        return month;
    }
    public int getYear()
    {
        return year;
    }
    public void setDay(int d)
    {
        day=d;
    }
    public void setMonth(int m)
    {
        month=m;
    }
    public void setYear(int y)
    {
        year=y;
    }
    public void setDate(int d,int m , int y)
    {
        day=d;
        month=m;
        year=y;
    }
    public String toString()
    {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}
