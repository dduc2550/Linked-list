/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_2;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        LinkedList studentList = new LinkedList();
        Scanner scanner = new Scanner(System.in);
        Set<Integer> idSet = new HashSet<>();  // To keep track of existing IDs
        int id = -1, choice = -1;
        String name = "";
        double marks = -1;

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Sort Students by Marks");
            System.out.println("5. Sort Students by ID");
            System.out.println("6. Search Student by ID (Linear Search)");
            System.out.println("7. Search Student by ID (Binary Search)");
            System.out.println("8. Display All Students");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            // Ensure only numbers are received when the user inputs a choice
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();  // Clear the invalid input
                continue;
            }

            scanner.nextLine();  // Consume newline 

            try {
                switch (choice) {
                    case 1:
                        while (true) {
                            try {
                                System.out.print("Enter ID: ");
                                id = scanner.nextInt();
                                validateId(id, idSet);
                                scanner.nextLine();  // Consume newline
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for ID.");
                                scanner.nextLine();  // Clear the invalid input
                            } catch (InvalidIdException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        while (true) {
                            try {
                                System.out.print("Enter Name: ");
                                name = scanner.nextLine();
                                validateName(name);
                                break;
                            } catch (InvalidNameException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        while (true) {
                            try {
                                System.out.print("Enter Marks: ");
                                marks = scanner.nextDouble();
                                validateMarks(marks);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for Marks.");
                                scanner.nextLine();  // Clear the invalid input
                            } catch (InvalidMarksException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        studentList.addStudent(new Student(id, name, marks));
                        idSet.add(id);
                        break;

                    case 2:
                        while (true) {
                            try {
                                System.out.print("Enter ID of student to edit: ");
                                id = scanner.nextInt();
                                validateIdExist(id, idSet);
                                scanner.nextLine();  // Consume newline
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for ID.");
                                scanner.nextLine();  // Clear the invalid input
                            } catch (InvalidIdException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        System.out.print("Enter new Name (or press Enter to skip): ");
                        name = scanner.nextLine();
                        if (!name.isEmpty()) {
                            try {
                                validateName(name);
                            } catch (InvalidNameException e) {
                                System.out.println("ERROR: " + e.getMessage());
                                break;
                            }
                        }

                        System.out.print("Enter new Marks (or -1 to skip): ");
                        try {
                            marks = scanner.nextDouble();
                            if (marks != -1) {
                                validateMarks(marks);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid number for Marks.");
                            scanner.nextLine();  // Clear the invalid input
                            break;
                        } catch (InvalidMarksException e) {
                            System.out.println("ERROR: " + e.getMessage());
                            break;
                        }

                        if (studentList.editStudent(id, name.isEmpty() ? null : name, marks)) {
                            System.out.println("Student edited successfully.");
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 3:
                        while (true) {
                            try {
                                System.out.print("Enter ID of student to delete: ");
                                id = scanner.nextInt();
                                validateIdExist(id, idSet);
                                scanner.nextLine();  // Consume newline
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for ID.");
                                scanner.nextLine();  // Clear the invalid input
                            } catch (InvalidIdException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        if (studentList.deleteStudent(id)) {
                            System.out.println("Student deleted successfully.");
                            idSet.remove(id);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 4:
                        studentList.sortByMarks();
                        System.out.println("Students sorted by marks.");
                        break;

                    case 5:
                        studentList.sortById();
                        System.out.println("Students sorted by ID.");
                        break;

                    case 6:
                        while (true) {
                            try {
                                System.out.print("Enter ID of student to search: ");
                                id = scanner.nextInt();
                                validateIdExist(id, idSet);
                                scanner.nextLine();  // Consume newline
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a valid number for ID.");
                                scanner.nextLine();  // Clear the invalid input
                            } catch (InvalidIdException e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        }

                        Student student = studentList.searchStudent(id);
                        if (student != null) {
                            System.out.println("Student found: " + student);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;


                    case 7:
                        studentList.sortById();  // Ensure list is sorted by ID before binary search
                        System.out.print("Enter ID of student to search: ");
                        id = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        Student studentBinary = studentList.binarySearchById(id);
                        if (studentBinary != null) {
                            System.out.println("Student found: " + studentBinary);
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 8:
                        studentList.displayStudents();
                        break;

                    case 9:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("WARNING ERROR: " + e.getMessage());
            }
        }
    }

    // Method to validate ID and throw exception if ID is invalid or already exists
    private static void validateId(int id, Set<Integer> idSet) throws InvalidIdException {
        if (id < 1) {
            throw new InvalidIdException("Invalid ID. ID must be a positive number.");
        }
        if (idSet.contains(id)) {
            throw new InvalidIdException("ID already exists. Please enter another ID.");
        }
    }

    // Method to check if ID exists in the list
    private static void validateIdExist(int id, Set<Integer> idSet) throws InvalidIdException {
        if (!idSet.contains(id)) {
            throw new InvalidIdException("ID does not exist. Please enter another ID.");
        }
    }

    // Method to validate name and throw exception if name contains numbers or special characters
    private static void validateName(String name) throws InvalidNameException {
        if (!name.matches("[a-zA-Z\\s]+")) {
            throw new InvalidNameException("Invalid name. The name cannot contain numbers or special characters.");
        }
    }

    // Method to validate marks and throw exception if marks are not within the range of 1-10
    private static void validateMarks(double marks) throws InvalidMarksException {
        if (marks < 1 || marks > 10) {
            throw new InvalidMarksException("Invalid score. Scores must be on a 10-point scale");
        }
    }
}

// Custom exception for invalid ID
class InvalidIdException extends Exception {

    public InvalidIdException(String message) {
        super(message);
    }
}

// Custom exception for invalid name
class InvalidNameException extends Exception {

    public InvalidNameException(String message) {
        super(message);
    }
}

// Custom exception for invalid marks
class InvalidMarksException extends Exception {

    public InvalidMarksException(String message) {
        super(message);
    }
}