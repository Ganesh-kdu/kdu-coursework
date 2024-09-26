package question1;

import com.ganesh.LogMaster;
import java.util.HashMap;

public class StudentUtil {
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) throws IllegalArgumentException, MissingGradeException{
        if (studentsGrades.length != studentIdList.length){
            throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. studentIdList.length: " + studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
        }
        //make hashmap to eliminate multiple if else later
        HashMap<Character, Double> gradeMapping = new HashMap<>();
        gradeMapping.put('A', 4.0);
        gradeMapping.put('B', 3.0);
        gradeMapping.put('C', 2.0);
        double[] finalResults = new double[studentIdList.length];
        for (int index = 0; index < studentIdList.length; index++) {
            char[] grades = studentsGrades[index];
            int length = grades.length;
            double gpa = 0.0;
            //find sum of all grades
            for (char grade : grades) {
                if (grade == ' '){
                    throw  new MissingGradeException(studentIdList[index]);
                }
                gpa += gradeMapping.get(grade);
            }
            gpa /= length;
            finalResults[index] = gpa;
        }
        return finalResults;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        //data validity check
        if (lower < 0 || higher > 4) {
            return new int[0];
        }
        double[] gpas;
        try {
            gpas = calculateGPA(studentIdList, studentsGrades);
        } catch (MissingGradeException e) {
            throw new InvalidDataException("Missing Grade Exception",e);
        }catch (IllegalArgumentException e){
            throw new InvalidDataException("Missing/extra student Exception", e);
        }
        int count = 0;
        //find count of students in given range to allocate correct memory
        for (double gpa : gpas) {
            if (gpa < higher && gpa > lower)
                count++;
        }
        int[] result = new int[count];
        count = 0;
        for (int index = 0; index < gpas.length; index++) {
            if (gpas[index] < higher && gpas[index] > lower)
                result[count] = studentIdList[index];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] students = StudentUtil.getStudentsByGPA(3.2, 3.5, new int[]{1001}, new char[][]{{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}});
        for (int student : students)
            LogMaster.print(student);
    }
}