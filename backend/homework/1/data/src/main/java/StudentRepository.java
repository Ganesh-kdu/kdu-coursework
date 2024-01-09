import java.util.ArrayList;

public class StudentRepository {
    private ArrayList<Student> Students;
    public void addStudent(int id, String name, int age, Character grade){
        logs.slf4jLogger.debug(String.format("Added new student with - %d, %s, %d, %c",id, name, age, grade));
        Students.add(new Student(id, name, age, grade));
    }
    public ArrayList<Student> retreiveStudent(String Name){
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student : Students) {
            if (student.getName().equals(Name)) {
                result.add(student);
            }
        }
        logs.slf4jLogger.debug(String.format("Fetched students with Name - %s",Name));
        return result;
    }
    public ArrayList<Student> retreiveStudent(int ID){
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student : Students) {
            if (student.getId() == ID) {
                result.add(student);
            }
        }
        logs.slf4jLogger.debug(String.format("Fetched students with ID - %d",ID));
        return result;
    }
    public ArrayList<Student> retreiveStudent(Character Grade){
        ArrayList<Student> result = new ArrayList<Student>();
        for (Student student : Students) {
            if (student.getGrade().equals(Grade)) {
                result.add(student);
            }
        }
        logs.slf4jLogger.debug(String.format("Fetched students with Grade - %c",Grade));
        return result;
    }

    public void updateStudent(int ID, String Name){
        for (int i=0; i<Students.size();i++) {
            if (Students.get(i).getId() == ID) {
                Student temp = Students.get(i);
                temp.setName(Name);
                Students.set(i,temp);
            }
        }
        logs.slf4jLogger.debug(String.format("Updated students with ID - %d, set Name - %s",ID,Name));
    }
    public void updateStudent(int ID, int Age){
        for (int i=0; i<Students.size();i++) {
            if (Students.get(i).getId() == ID) {
                Student temp = Students.get(i);
                temp.setAge(Age);
                Students.set(i,temp);
            }
        }
        logs.slf4jLogger.debug(String.format("Updated students with ID - %d, set Age - %d",ID,Age));
    }
    public void updateStudent(int ID, Character Grade){
        for (int i=0; i<Students.size();i++) {
            if (Students.get(i).getId() == ID) {
                Student temp = Students.get(i);
                temp.setGrade(Grade);
                Students.set(i,temp);
            }
        }
        logs.slf4jLogger.debug(String.format("Updated students with ID - %d, set Grade - %c",ID,Grade));
    }
    public StudentRepository(){
        Students = new ArrayList<Student>();
    }
}
