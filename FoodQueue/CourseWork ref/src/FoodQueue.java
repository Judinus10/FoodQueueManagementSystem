import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FoodQueue {
    public Customer[] customer_queue;

    // Constructor to initialize the customer_queue array with the given size
    public FoodQueue(int size) {
        customer_queue = new Customer[size];
    }

    // Method to initialize each element of customer_queue with a new Customer object
    public void initiate() {
        for (int i = 0; i < customer_queue.length; i++) {
            customer_queue[i] = new Customer();
        }
    }

    // Method to set customer data for a specific position in the queue
    public void AddCustomer(String firstName, String lastName, int RequiredBurger, int PhoneNumber, int BillNo, int i) {
        customer_queue[i].setData(firstName, lastName, RequiredBurger, PhoneNumber, BillNo);
    }

    // Method to show the queue status (empty or occupied) for a specific position
    public void showQueue(int i) {
        if (customer_queue[i].firstName.equals("X")) {
            System.out.print("  X" + "   ");  // Display 'X' for an empty slot
        } else {
            System.out.print("  O" + "   ");  // Display 'O' for an occupied slot
        }
    }

    // Method to show the positions of empty slots in the queue
    public void showEmptyQueue(int i) {
        if (customer_queue[i].firstName.equals("X")) {
            System.out.print(i + 1);  // Display the index + 1 to show available position
            if (i + 1 != customer_queue.length) {
                System.out.print(" , ");
            }
        } else {
            System.out.print("");  // Display nothing for an occupied slot
        }
    }

    // Check if a specific slot in the queue is empty
    boolean isEmpty(int i) {
        return customer_queue[i].firstName.equals("X");
    }

    // Remove a customer from a specific slot in the queue
    public void RemoveCustomer(int i) {
        customer_queue[i].setData("X", "X", 0, 0, 0);
    }

    // Move customer data one slot forward in the queue
    public void moveFront(int i) {
        customer_queue[i].firstName = customer_queue[i + 1].firstName;
        customer_queue[i].secondName = customer_queue[i + 1].secondName;
        customer_queue[i].noOfPizzas = customer_queue[i + 1].noOfPizzas;
        customer_queue[i].PhoneNumber = customer_queue[i + 1].PhoneNumber;
        customer_queue[i].BillNo = customer_queue[i + 1].BillNo;
        RemoveCustomer(i + 1);
    }

    // Get the number of pizzas required by a customer in a specific slot
    public int PizzaRequired(int i) {
        return customer_queue[i].noOfPizzas;
    }

    // Arrange and display customer names in alphabetical order
    public void nameArrange() {
        // Create a temporary array to hold Customer objects for sorting
        Customer[] c = new Customer[5];
        // Copy customer_queue array to the temporary array
        System.arraycopy(customer_queue, 0, c, 0, customer_queue.length);

        // Sort the temporary array based on customer names
        for (int i = 0; i < 3; i++) {
            try {
                for (int j = i + 1; j < 6; j++) {
                    try {
                        // Compare and swap customers based on their names
                        if (c[i].firstName.compareTo(c[j].firstName) > 0 && (!c[i].firstName.equals("X"))) {
                            Customer temp = c[i];
                            c[i] = c[j];
                            c[j] = temp;
                        }
                    } catch (NullPointerException ignored) {
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }

        // Print the sorted customer names
        try {
            for (int i = 0; i < 6; i++) {
                try {
                    if (!c[i].firstName.equals("X")) {
                        System.out.println((i + 1) + ". " + c[i].firstName);
                    }
                } catch (NullPointerException ignored) {//ignore if slot data is null
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {//ignore if array is out of index

        }
    }


    public void write(FileWriter x) throws IOException {
        // Loop through each customer in the queue
        for (Customer customer : customer_queue) {
            // Check if the customer is valid (not "X")
            if (!Objects.equals(customer.firstName, "X")) {
                // Write customer information to the FileWriter
                x.write("➤" + " " + "Customer name: " + customer.firstName + " ");
                x.write(customer.secondName + "\n");
                x.write("  " + "Required Pizzas: " + customer.noOfPizzas + "\n");
                x.write("\n");
            }
        }
    }

}
