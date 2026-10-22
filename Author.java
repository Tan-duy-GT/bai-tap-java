public class Author {
    String name ;
    String email;
    char gender;
    public Author (String n,String e)
    {
        name = n;
        email=e ;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String e)
    {
        email=e;
    }
    public char getGender()
    {
        return gender;
    }
    public String toString()
    {
        return "Author[name="+name+",email="+email+"]";
    }
}
