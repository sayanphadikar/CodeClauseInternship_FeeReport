import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private double totalFees;
    private double paidFees;
    private List<Double> paymentHistory;

    public Student(String studentId, String name, double totalFees) {
        this.studentId = studentId;
        this.name = name;
        this.totalFees = totalFees;
        this.paidFees = 0.0;
        this.paymentHistory = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getTotalFees() {
        return totalFees;
    }

    public double getPaidFees() {
        return paidFees;
    }

    public void makePayment(double amount) {
        paidFees += amount;
        paymentHistory.add(amount);
    }

    public double getRemainingFees() {
        return totalFees - paidFees;
    }

    public List<Double> getPaymentHistory() {
        return paymentHistory;
    }
}

public class FeeReport {
    private List<Student> students;

    public FeeReport() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        System.out.println("-------- List of Students --------");
        for (Student student : students) {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Total Fees: $" + student.getTotalFees());
            System.out.println("Paid Fees: $" + student.getPaidFees());
            System.out.println("Remaining Fees: $" + student.getRemainingFees());
            System.out.println();
        }
    }

    public void displayPaymentHistory(String studentId) {
        Student student = findStudent(studentId);
        if (student != null) {
            List<Double> paymentHistory = student.getPaymentHistory();
            System.out.println("-------- Payment Transaction History for Student " + studentId + " --------");
            for (int i = 0; i < paymentHistory.size(); i++) {
                System.out.println("Payment " + (i + 1) + ": $" + paymentHistory.get(i));
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void main(String[] args) {
        FeeReport feeSystem = new FeeReport();

      
        feeSystem.addStudent(new Student("2023001", "Raju", 2000));
        feeSystem.addStudent(new Student("2023002", "Sonu", 2000));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------- Fee Report System --------");
            System.out.println("1. Make Payment");
            System.out.println("2. View Student's Fee Report");
            System.out.println("3. Display All Students");
            System.out.println("4. View Payment Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.next();
                    Student student = feeSystem.findStudent(studentId);
                    if (student != null) {
                        System.out.print("Enter payment amount: ");
                        double paymentAmount = scanner.nextDouble();
                        student.makePayment(paymentAmount);
                        System.out.println("Payment successful.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.next();
                    student = feeSystem.findStudent(studentId);
                    if (student != null) {
                        System.out.println("-------- Fee Report for Student " + student.getStudentId() + " --------");
                        System.out.println("Name: " + student.getName());
                        System.out.println("Total Fees: $" + student.getTotalFees());
                        System.out.println("Paid Fees: $" + student.getPaidFees());
                        System.out.println("Remaining Fees: $" + student.getRemainingFees());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3:
                    feeSystem.displayAllStudents();
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.next();
                    feeSystem.displayPaymentHistory(studentId);
                    break;

                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
