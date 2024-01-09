public class Student {
    private int id;
    private String name;
    private int age;
    private Character grade;

    public int getId(){
        return id;
    }
    public void setId(int inputId){
        id = inputId;
    }
    public String getName(){
        return name;
    }
    public void setName(String inputName){
        name = inputName;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int inputAge){
        age = inputAge;
    }

    public Character getGrade() {
        return grade;
    }
    public void setGrade(Character inputGrade){
        grade = inputGrade;
    }
    public Student(){
        setId(-1);
        setAge(-1);
        setGrade('U');
        setName("");
    }
    public Student(int id, String name, int age, Character grade){
        setId(id);
        setAge(age);
        setGrade(grade);
        setName(name);
    }
}
