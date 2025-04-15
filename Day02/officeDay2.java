import java.util.jar.*;

public class Student{
    public String name;
    public int age;

}

public class officeDay2 {

   public static void main(String[] args) {
    Student s= new Student();
    s.age=-5;
   }
    
    
}
System.out.println("===============");
public class Student {
    private String name;
    private int age;

    public void setAge(int age){
        if(age>0) this.age=age;
        else System.out.println("Invalid age");

    }
    public int getAge(){
        return age;
    }

    
}
System.out.println("===============");
