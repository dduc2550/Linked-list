/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student_2;

/**
 *
 * @author Do Anh Duc
 */
class Node {

    Student data;
    Node next;

    public Node(Student data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {

    private Node head;

    public LinkedList() {
        this.head = null;
    }
    
    // Add a student to the end of the list
    public void addStudent(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Edit student information
    public boolean editStudent(int id, String name, double marks) {
        Node current = head;
        while (current != null) {
            if (current.data.getId() == id) {
                if (name != null) {
                    current.data.setName(name);
                }
                if (marks >= 0) {
                    current.data.setMarks(marks);
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Delete a student
    public boolean deleteStudent(int id) {
        if (head == null) {
            return false;
        }
        if (head.data.getId() == id) {
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getId() == id) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Sort students by marks in descending order using Bubble Sort
    public void sortByMarks() {
        if (head == null || head.next == null) {
            return;
        }
        boolean swapped;
        do {
            Node current = head;
            Node previous = null;
            Node next = head.next;
            swapped = false;
            while (next != null) {
                if (current.data.getMarks() < next.data.getMarks()) {
                    // Swap the nodes
                    swapped = true;
                    if (previous != null) {
                        Node temp = next.next;
                        previous.next = next;
                        next.next = current;
                        current.next = temp;
                    } else {
                        Node temp = next.next;
                        head = next;
                        next.next = current;
                        current.next = temp;
                    }
                    previous = next;
                    next = current.next;
                } else {
                    previous = current;
                    current = next;
                    next = next.next;
                }
            }
        } while (swapped);
    }

    // Sort students by ID in ascending order using Selection Sort
    public void sortById() {
        if (head == null || head.next == null) {
            return;
        }
        Node current = head;
        while (current != null) {
            Node min = current;
            Node next = current.next;
            while (next != null) {
                if (min.data.getId() > next.data.getId()) {
                    min = next;
                }
                next = next.next;
            }
            // Swap data of current node and min node
            Student temp = current.data;
            current.data = min.data;
            min.data = temp;
            current = current.next;
        }
    }

    // Search for a student by ID using Linear Search
    public Student searchStudent(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.getId() == id) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    

    // Search for a student by ID using Binary Search
    public Student binarySearchById(int id) {
        if (head == null) {
            return null;
        }

        Node[] nodesArray = toArray();
        int left = 0;
        int right = nodesArray.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nodesArray[mid].data.getId() == id) {
                return nodesArray[mid].data;
            }

            if (nodesArray[mid].data.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    // Helper method to convert linked list to array
    private Node[] toArray() {
        int size = getSize();
        Node[] array = new Node[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current;
            current = current.next;
        }
        return array;
    }

    // Helper method to get size of linked list
    private int getSize() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // Display all students
    public void displayStudents() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}