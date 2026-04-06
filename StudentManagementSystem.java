import java.util.*;


public class StudentManagementSystem {
    public static void main(String[] args) {
        // Create an array of Student objects
        Student[] students = new Student[3];
        
        // Create and initialize Student objects
        students[0] = new Student("Alice", 101); //creates object
        students[1] = new Student("Bob", 102);
        students[2] = new Student("Charlie", 103);
        
        // Assign courses to each student
        students[0].setCourseAt(0, "Math");
        students[0].setCourseAt(1, "Science");
        
        students[1].setCourseAt(0, "History");
        students[1].setCourseAt(1, "English");
        
        students[2].setCourseAt(0, "Art");
        students[2].setCourseAt(1, "Physical Education");
        
        // Display information for each student
        for (Student stud : students) { //for-each loop displaying each students information
            stud.displayStudentInfo();
        }
        
        // Display the total number of students created
        System.out.println("Total number of students: " + Student.getStudentCount());
    }
}


class Student {
    // Private instance variables
    private String name;
    private int id;
    private String[] courses; //array of courses
    
    // Private static variable to count students
    private static int studentCount = 0; //cannot be accessed directly, it will be updated once an object is created. Only the stuff in the Student class can update it and such.
    //Every student object shares the same studentCount variable
    //Has to be accessed via public methods like the getstudentCount function.

    
    // Constructor | takes name and id, sets course default size to 5 and has no classes
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.courses = new String[5]; // assuming a max of 5 courses
        studentCount++; //each time an object is made, count increments by 1.
    }
    

    // Getter and Setter for name
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    

    // Getter and Setter for id
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    

    // Getter and Setter for courses
    public String[] getCourses() { 
        return courses; 
    }
    public void setCourses(String[] courses) { 
        this.courses = courses; 
    }
    

    // Method to add a course at a specific index
    public void setCourseAt(int index, String course) { //takes index and a string class, puts them in courses array
        if (index >= 0 && index < courses.length) {
            courses[index] = course; //the string along with the index will be put in this class's student variable 'courses'
        }
        //just in case index is OOB 
        else {
            System.out.println("Index out of bounds for courses array.");
        }
    }
    
    
    // Instance method to display student info
    public void displayStudentInfo() {

        System.out.println("Student Name: " + name);
        System.out.println("Student ID: " + id);
        System.out.print("Courses: ");

        for (String course : courses) { //for course in courses...
            if (course != null) {
                System.out.print(course + " ");
            }
        }
        System.out.println("\n");
    }
    
    // Static method to get the number of students created
    public static int getStudentCount() { //an object or the class itself can call it
        return studentCount;
    }

}
