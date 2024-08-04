import java.util.Scanner;

interface GPA{
double calculateGPA();
}

class Student {
private String name;
private int[] marks;

public Student (String name,int[] marks){
this.name = name;
this.marks = marks;
}


public double calculateGPA(){
int totalMarks = 0;
for(int mark : marks){
totalMarks = totalMarks + mark;
}

double gpa = totalMarks/marks.length;
return gpa;
}

public void printResult(){
System.out.println("Student Name : " + name);
System.out.println("Marks : "+java.util.Arrays.toString(marks));
System.out.printf("GPA : %.2f\n",calculateGPA());
}
}



public class StudentResult{
public static void main(String[] args){
Scanner scanner = new Scanner(System.in);

Student[] students = new Student[5];

for(int i = 0;i<5;i++){
System.out.println("Enter details for student" + (i+1));
System.out.print("Name : ");
String name = scanner.nextLine();

int[] marks = new int[5];
for(int j=0;j<5;j++){
System.out.print("Enter marks for subject " + (j+1) + " : ");
marks[j] = scanner.nextInt();
}

scanner.nextLine();
students[i] = new Student(name,marks);
}

System.out.println("Result:");
for(Student student : students){
student.printResult();
}

scanner.close();
}
}














