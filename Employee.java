public class Employee 
{
    int id;
    String firstname ;
    String lastname ;
    int salary;
    public Employee (int i , String f , String l , int s)
    {
        id=i;
        firstname=f;
        lastname=l;
        salary=s;
    }
    public int getid()
    {
        return id;
    }
    public String getfirstname()
    {
        return firstname;
    }
    public String getlastname()
    {
        return lastname;
    }
    public String getname()
    {
        return firstname + lastname;
    }
    public int getsalary()
    {
        return salary;
    }
    public void setsalary(int s)
    {
        salary=s ;
    }
    public int getannualsalary()
    {
        return salary*12;
    }
    public int raisesalary(int percent)
    {
        salary+=salary*percent/100;
        return salary;
    }
    public String toString()
    {
        return "Employee[id="+id+",name="+firstname+" "+lastname+",salary="+salary+"]";
    }
}

