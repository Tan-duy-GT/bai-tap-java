public class TestEmployee {
   public static void main(String[] args) {
      // Test constructor and toString()
      Employee e1 = new Employee(8,"Peter","Tan", 2500);
      System.out.println(e1);  // toString();

      // Test Setters and Getters
      e1.setsalary(999);
      System.out.println(e1);  // toString();
      System.out.println("id is: " + e1.getid());
      System.out.println("firstname is: " + e1.getfirstname());
      System.out.println("lastname is: " + e1.getlastname());
      System.out.println("salary is: " + e1.getsalary());

      System.out.println("name is: " + e1.getname());
      System.out.println("annual salary is: " + e1.getannualsalary());
      System.out.println(e1.raisesalary(10));
      System.out.println(e1);
   }
}