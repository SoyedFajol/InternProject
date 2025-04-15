public class Employee {
    private String name;
    private int age;
    private double salary;

    //Create getter setter validation

    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        if(age > 18 && age < 65){
            this.age=age;
        }else{
            System.out.println("Invalid Age");
        }
    }
    public void setSalary(double salary){
        if(salary >= 100000){
            this.salary=salary;   
        }else 
        {
            System.out.println("Salary Must be in rage of 0-100000");

        }
    }
    //Create getter to access
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public double getSalary(){
        return salary;
    }
    
}

public class Problem{
    public static void main(String[] args) {
        Employee emp= new Employee();

        emp.setName("Solaman");
        emp.setAge(22);
        emp.setSalary(15000);
        
        System.out.println("Name: "+emp.getName());
        System.out.println("Age: "+emp.getAge());
        System.out.println("Salary: "+emp.getSalary());

    }
}
