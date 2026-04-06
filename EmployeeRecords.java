import java.io.*;

class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException("Invalid salary for employee: " + name);
        }
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toFileFormat() {
        return id + "," + name + "," + salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}

public class EmployeeRecords {
    private static final String INPUT_FILE = "employees.txt";
    private static final String OUTPUT_FILE = "valid_employees.txt";

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Invalid format in line: " + line);
                    }

                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double salary = Double.parseDouble(parts[2].trim());

                    Employee employee = new Employee(id, name, salary);
                    employee.display();
                    writer.write(employee.toFileFormat());
                    writer.newLine();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid number format in line: " + line);
                } catch (InvalidSalaryException e) {
                    System.out.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("\nValid employee records have been saved to " + OUTPUT_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found.");
        } catch (IOException e) {
            System.out.println("Error: Unable to read/write file.");
        }
    }
}
