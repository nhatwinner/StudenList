
package assingment9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Student {
    String studentId;
    String fullName;
    Date dateOfBirth;
    String major;
    float gpa;

    // Hàm nhập thông tin sinh viên
    public void enterStudentInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student ID: ");
        studentId = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        fullName = scanner.nextLine();
        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        try {
            String dob = scanner.nextLine();
            dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
        } catch (Exception e) {
            System.out.println("Invalid date format. Setting to null.");
            dateOfBirth = null;
        }
        System.out.print("Enter Major: ");
        major = scanner.nextLine();
        System.out.print("Enter GPA: ");
        gpa = scanner.nextFloat();
    }

    // Hàm hiển thị thông tin sinh viên
    public void displayStudentInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dobString = (dateOfBirth != null) ? sdf.format(dateOfBirth) : "N/A";
        System.out.println("ID: " + studentId + ", Name: " + fullName + ", Date of Birth: " + dobString + ", Major: " + major + ", GPA: " + gpa);
    }
}

public class StudentList {
    ArrayList<Student> studentList = new ArrayList<>();

    // Hiển thị tất cả sinh viên
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }
        for (Student student : studentList) {
            student.displayStudentInfo();
        }
    }

    // Tìm sinh viên theo ID
    public Student findStudentById(String idToFind) {
        for (Student student : studentList) {
            if (student.studentId.equals(idToFind)) {
                return student;
            }
        }
        return null;
    }

    // Xóa sinh viên theo ID
    public boolean deleteStudentById(String idToDelete) {
        Student student = findStudentById(idToDelete);
        if (student != null) {
            studentList.remove(student);
            return true;
        }
        return false;
    }

    // Chỉnh sửa thông tin sinh viên theo ID
    public boolean editStudentById(String idToEdit) {
        Student student = findStudentById(idToEdit);
        if (student != null) {
            System.out.println("Editing student with ID: " + idToEdit);
            student.enterStudentInfo();
            return true;
        }
        return false;
    }

    // Phương thức chính
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Find Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Edit Student by ID");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng còn lại

            switch (choice) {
                case 1:
                    Student newStudent = new Student();
                    newStudent.enterStudentInfo();
                    studentList.studentList.add(newStudent);
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    studentList.displayAllStudents();
                    break;
                case 3:
                    System.out.print("Enter ID to find: ");
                    String idToFind = scanner.nextLine();
                    Student foundStudent = studentList.findStudentById(idToFind);
                    if (foundStudent != null) {
                        System.out.println("Student found:");
                        foundStudent.displayStudentInfo();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    String idToDelete = scanner.nextLine();
                    if (studentList.deleteStudentById(idToDelete)) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter ID to edit: ");
                    String idToEdit = scanner.nextLine();
                    if (studentList.editStudentById(idToEdit)) {
                        System.out.println("Student edited successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}