import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Project01 {
    public static void main(String[] args) {
        

        Scanner scanner = new Scanner(System.in);
        //char choice;

        do {
            //For patient
            System.out.print("Enter patient's ID: ");
            int patientID = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter patient's first name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter patient's last name: ");
            String lastName = scanner.nextLine();

            //Patient Bday
            System.out.println("Enter Patient Birth Date (day/month/year): ");
            Date BirthDate = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());


            //For doctor
            System.out.print("Enter doctor's first name: ");
            String docFirstName = scanner.nextLine();

            System.out.print("Enter doctor's last name: ");
            String docLastName = scanner.nextLine();

            System.out.print("Enter doctor's specialty: ");
            String specialty = scanner.nextLine();

            Doctor doctor = new Doctor(docFirstName, docLastName, specialty);

            //For patient dates

            System.out.print("Enter Admit Date (day/month/year): ");
            Date admitDate = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

            System.out.print("Enter Discharge Date (day/month/year): ");
            Date dischargeDate = new Date(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());



            //Creating object and putting in all given values
            Patient patient = new Patient(firstName, lastName, patientID, BirthDate, admitDate, dischargeDate, doctor);

            //Bill object
            System.out.print("Enter pharmacy Charges, room Rent, and doctor Fee: ");
            Bill bill = new Bill(patient, scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());


            //Printing out 
            System.out.println(patient);
            System.out.println(bill);


            //Writing to file
            String fileName = firstName + lastName + ".txt";

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(patient.toString() + "\n" + bill.toString());
            } 
            catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }


            System.out.print("Do you want to continue? (y/n): ");

        } 
        while (scanner.next().charAt(0) == 'y');

        scanner.close();
    }
}


class Person { //Extends Object (default)
    //PRIVATE   
    private String First_Name;
    private String Last_Name;


    //PUBLIC
    public void setFirstName(String First_Name) {
        this.First_Name = First_Name;
    }

    public String getFirstName() {
        return First_Name;
    }


    public void setLastname(String Last_Name) {
        this.Last_Name = Last_Name;
    }

    public String getLastName() {
        return Last_Name;
    }


    @Override
    public String toString() {
        return "First Name: " + getFirstName() + " | " + "Last Name: " + getLastName();
    }


    //CONSTRUCTORS  
    public Person() {

    }

    public Person(String First_Name, String Last_Name) {
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
    }
}


class Doctor extends Person {
    //PRIVATE
    String Doctor_Specialty;

    //PUBLIC
    public void setDoctorSpecialty(String Doctor_Specialty) {
        this.Doctor_Specialty = Doctor_Specialty;
    }

    public String getDoctorSpecialty() {
        return Doctor_Specialty;
    }


    @Override
    public String toString() {
        return super.toString() + " | " + "Specialty: " + getDoctorSpecialty();
    }


    //CONSTRUCTORS
    public Doctor() {

    }

    public Doctor(String First_Name, String Last_Name, String Doctor_Specialty) {
        super(First_Name, Last_Name);
        this.Doctor_Specialty = Doctor_Specialty;
    }
}


class Date {
    //PRIVATE
    private int day;
    private int month;
    private int year;

    //PUBLIC    
    public Date (int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}


class Patient extends Person {
    //PRIVATE
    private int Patient_ID;
    private Date Birth_Date;
    private Date Admit_Date;
    private Date Discharge_Date;
    private Doctor Doctor;

    //PUBLIC
    public Patient(String First_Name, String Last_Name, int Patient_ID, Date Birth_Date, Date Admit_Date, Date Discharge_Date, Doctor Doctor) {
        super(First_Name, Last_Name);
        this.Patient_ID = Patient_ID;
        this.Birth_Date = Birth_Date;
        this.Admit_Date = Admit_Date;
        this.Discharge_Date = Discharge_Date;
        this.Doctor = Doctor;
    }


    public void setPatientID(int Patient_ID) { 
        this.Patient_ID = Patient_ID; 
    }
    public int getPatientID() {
        return Patient_ID; 
    }

    public void setBirthDate(Date Birth_Date) { 
        this.Birth_Date = Birth_Date; 
    }
    public Date getBirthDate() { 
        return Birth_Date; 
    }

    public void setDoctor(Doctor Doctor) { 
        this.Doctor = Doctor; 
    }

    public Doctor getDoctor() { 
        return Doctor; 
    }

    public void setAdmitDate(Date Admit_Date) { 
        this.Admit_Date = Admit_Date; 
    }
    public Date getAdmitDate() { 
        return Admit_Date; 
    }

    public void setDischargeDate(Date Discharge_Date) { 
        this.Discharge_Date = Discharge_Date; 
    }
    public Date getDischargeDate() { 
        return Discharge_Date; 
    }


    @Override
    public String toString() {
        return "Patient: " + super.toString() + "\n" +
               "ID: " + getPatientID() + "\n" +
               "Attending Physician: " + getDoctor() + "\n" +
               "Admit Date: " + getAdmitDate() + "\n" +
               "Discharge Date: " + getDischargeDate() + "\n";
    }

}


class Bill {
    //PRIVATE
    private Patient Patient;
    private double Pharmacy_Charges;
    private double Room_Charges;
    private double Doctor_Fees;

    //PUBLIC
    public Bill(Patient Patient, double Pharmacy_Charges, double Room_Charges, double Doctor_Fees) {
        this.Patient = Patient;
        this.Pharmacy_Charges = Pharmacy_Charges;
        this.Room_Charges = Room_Charges;
        this. Doctor_Fees = Doctor_Fees;
    }


    public double getTotalCharges() {
        return this.Pharmacy_Charges + this.Room_Charges + this.Doctor_Fees;
    }

    public Patient getPatientID() {
        return Patient;
    }

    public void setPharmacyCharges(double Pharmacy_Charges) {
        this.Pharmacy_Charges = Pharmacy_Charges;
    }

    public double getPharmacyCharges() {
        return Pharmacy_Charges;
    }

    public void setRoomCharges(double Room_Charges) {
        this.Room_Charges = Room_Charges;
    }

    public double getRoomCharges() {
        return Room_Charges;
    }

    public void setDoctorFee(double Doctor_Fees) {
        this.Doctor_Fees = Doctor_Fees;
    }

    public double getDoctorFee() {
        return Doctor_Fees;
    }


    @Override
    public String toString() {
        return 
                "Pharmacy Charges: $ " + getPharmacyCharges() + "\n" +
               "Room Charges:        $ " + getRoomCharges() + "\n" +
               "Doctor's Fees:       $ " + getDoctorFee() + "\n" +
               "______________________________\n" +
               "Total Charges:       $ " + getTotalCharges();
    }
}