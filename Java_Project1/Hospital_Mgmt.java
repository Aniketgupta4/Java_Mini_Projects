package Java_Project1;
import java.util.*;

// Patient class
class Patient{
    int id;
    String name;
    int age;

    public Patient(int id,String name,int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void displayPatientInfo(){
        System.out.println("Patient ID: "+ id);
        System.out.println("Name: "+ name);
        System.out.println("Age: "+ age);
    }
}

//Doctor class
class Doctor{
    int id;
    String name;
    String specialization;

    public Doctor(int id,String name,String specialization){
       this.id = id;
       this.name = name;
       this.specialization = specialization;
    }

    public void displayDoctorInfo(){
        System.out.println("Doctor ID: "+id);
        System.out.println("Name: "+name);
        System.out.println("Specialization: "+specialization);
    }
}

//Appointment class

class Appointment{
    Patient patient;
    Doctor doctor;
    String date;

    public Appointment(Patient patient,Doctor doctor,String date){
       this.patient = patient;
       this.doctor = doctor;
       this.date = date;
    }

    public void displayAppointment(){
        System.out.println("Appointment Date: "+ date);
        System.out.println("------Patient Info------");
        patient.displayPatientInfo();
        System.out.println("------Doctor Info------");
        doctor.displayDoctorInfo();
    }
}


public class Hospital_Mgmt {
    
    public static void main(String args[]){
        
        Scanner sc = new Scanner(System.in);

        // sample doctor and Patient
        Doctor doctor1 = new Doctor(1, "Dr. Aniket", "Cardiology");
        Patient patient1 = new Patient(101, "subham", 24);

        System.out.println("Enter appointment date (2025-09-11): ");

        String date = sc.nextLine();

        Appointment appointment = new Appointment(patient1, doctor1, date);

        System.out.println("\n--- Appointment Details ---");
        appointment.displayAppointment();

    }

}
