import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){

        logs.slf4jLogger.debug("Started");
        StudentRepository S = new StudentRepository();
        int i=0;
        Scanner sc = new Scanner(System.in);
        while (i!=4) {
            System.out.print("Choose : ");
            i = Integer.parseInt(sc.next());

            logs.slf4jLogger.debug(String.format("User chose %d",i));
            if (i == 1) {//add
                System.out.print("ID : ");
                int id = Integer.parseInt(sc.next());
                System.out.print("Name : ");
                String name = sc.next();
                System.out.print("Age : ");
                int age = Integer.parseInt(sc.next());
                System.out.print("Grade : ");
                Character grade = sc.next().charAt(0);
                S.addStudent(id, name, age, grade);
            } else if (i == 2) {//get
                System.out.println("Searchby - ");
                System.out.println("Name : 1");
                System.out.println("ID : 2");
                System.out.println("Grade : 3");
                System.out.print("Choice = ");
                int option = Integer.parseInt(sc.next());
                System.out.print("Value = ");
                ArrayList<Student> res;
                if (option == 1) {
                    String value = sc.next();
                    res = S.retreiveStudent(value);
                } else if (option == 2) {
                    int value = Integer.parseInt(sc.next());
                    res = S.retreiveStudent(value);
                } else {
                    Character value = sc.next().charAt(0);
                    res = S.retreiveStudent(value);
                }
                for (Student student : res) {
                    System.out.println("Details:");
                    System.out.println(student.getId());
                    System.out.println(student.getName());
                    System.out.println(student.getAge());
                    System.out.println(student.getGrade());
                }
            } else if (i == 3) {//update
                System.out.println("Update - ");
                System.out.println("Name : 1");
                System.out.println("Age : 2");
                System.out.println("Grade : 3");
                System.out.print("Choice = ");
                int option = Integer.parseInt(sc.next());
                System.out.print("Enter ID of person to be updated: ");
                int ID = Integer.parseInt(sc.next());
                System.out.print("Enter updated valuet: ");
                if (option == 1) {
                    String Name = sc.next();
                    S.updateStudent(ID, Name);
                } else if (option == 2) {
                    int Age = Integer.parseInt(sc.next());
                    S.updateStudent(ID, Age);
                } else {
                    Character grade = sc.next().charAt(0);
                    S.updateStudent(ID, grade);
                }
            } else {
                i = 4;
            }
        }

        logs.slf4jLogger.debug("Ended");
    }
}
