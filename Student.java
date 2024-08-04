public class Student{
private int student_id;
private String student_name;
private int[] grades;
private int  gradesCount;

public Student(int student_id,String student_name,int Grades){
this.student_id = student_id;
this.student_name = student_name;
this.grades = new int[Grades];
}

//Getter and Setter
public int getStudentId(){
return student_id;
}

public void setStudentId(int student_id){
this.student_id = student_id;
}

public String getStudentName(){
return student_name;
}

public void setStudentName(String student_name){
this.student_name = student_name;
}

//Method to add a grade
public void addGrade(int grade){
if(grades.length>0){
grades[0] = grade;
System.out.println("Grade added");
}
else{
System.out.println("Cannot add grade");
}
}

//main
public static void main(String[] args){
Student student = new Student(1,"Renu",1);
student.addGrade(90);

//print student info and grade
System.out.println("Student ID: "+student.getStudentId());
System.out.println("Student Name: "+student.getStudentName());
System.out.println("Grade: "+student.grades[0]);
}
}

