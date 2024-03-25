
import java.io.*;
import java.util.*;


public class ClassVersion {
    public static int Income_1 = 0;   // Stores income for Queue 1
    public static int Income_2 = 0;   // Stores income for Queue 2
    public static int Income_3 = 0;   // Stores income for Queue 3
    public static final Scanner input = new Scanner(System.in);  // Scanner for input
    public static int Pizza = 100;  // Initial stock of pizzas
    public static Waiting_List Waiting_List = new Waiting_List(5);  // Waiting list for customers
    static int customer_count = 0;  // Count of customers
    private final FoodQueue[] Queues;  // Array of FoodQueue objects
    static ClassVersion c1 = new ClassVersion();  // An instance of ClassVersion
    public static FoodQueue[] queues = c1.getQueues();  // Array of FoodQueue objects from c1
    public static int BillNo = 1000;  // Initial bill number

    public ClassVersion() {
        Queues = new FoodQueue[3];  // Create an array to hold FoodQueue objects
        Queues[0] = new FoodQueue(2);  // Initialize Queue 1 with a capacity of 2
        Queues[1] = new FoodQueue(3);  // Initialize Queue 2 with a capacity of 3
        Queues[2] = new FoodQueue(5);  // Initialize Queue 3 with a capacity of 5
    }


    public static void main(String[] args) throws IOException{
        initiate();
        menuDisplay();
    }
    //
    public static void menuDisplay() throws IOException {
        while (true) {
            System.out.println();
            System.out.println("""
                ************************************
                *               MENU               *
                ************************************""");
            System.out.println("100 or VFQ: View all Queues");
            System.out.println("101 or VEQ: View all Empty Queues");
            System.out.println("102 or ACQ: Add Customer to a Queue");
            System.out.println("103 or RCQ: Remove a Customer From a Queue");
            System.out.println("104 or PCQ: Remove a served customer");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order");
            System.out.println("106 or SPD: Store Program Data into file");
            System.out.println("107 or LPD: Load Program Data from file");
            System.out.println("108 or STK: View Remaining pizza Stock");
            System.out.println("109 or AFS: Add pizza to Stock");
            System.out.println("110 or IFQ: view income of each queue");
            System.out.println("999 or EXT: Exit the Program");

            // Creating "input1" object in menuDisplay Method
            Scanner input1 = new Scanner(System.in);
            System.out.println(); // This is help to create a line space between "• Press 999 or EXT: Exit the Program." and "Enter the option that you want to proceed: ".
            System.out.print("Enter the option that you want to proceed: ");
            String option = input1.next(); // Creating variable "operator_option" to store operator's option.
            switch (option) {
                case "100", "VFQ" -> ViewAllQueues();
                case "101", "VEQ" -> ViewAllEmptyQueues();
                case "102", "ACQ" -> AddCustomer();
                case "103", "RCQ" -> RemoveCustomer();
                case "104", "PCQ" -> RemoveServedCustomer();
                case "105", "VCS" -> CustomerSort();
                case "106", "SPD" -> StoreProgram();
                case "107", "LPD" -> LoadProgram();
                case "108", "STK" -> ViewRemainingStock();
                case "109", "AFS" -> UpdateStock();
                case "110", "IFQ" -> IncomeOfPizzas();
                case "999", "EXT" -> {
                    System.out.println("---THANK YOU !!!---");
                }
                default -> System.out.println("Invalid Option!. Please Enter a Valid Input....");
            }

        }
    }
    public FoodQueue[] getQueues() {
        return Queues;  // Return the array of FoodQueue instances
    }

    public static void initiate() {
        queues[0].initiate();  // Initialize the first queue
        queues[1].initiate();  // Initialize the second queue
        queues[2].initiate();  // Initialize the third queue
    }

    public static void ViewAllQueues() {
        System.out.println("*****************");
        System.out.println("*    Cashiers   *");
        System.out.println("*****************");

        for (int i = 0; i < 5; i++) {
            for (FoodQueue queue : queues) {
                try {
                    queue.showQueue(i);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("      "); // Add space between queues
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }

    public static void ViewAllEmptyQueues() {
        System.out.println();
        System.out.println("Empty Queues : ");

        // Iterate through all queues
        for (int i = 0; i < queues.length; i++) {
            // Check if the current queue is empty
            int j = 0;
            if(i==0){ j = 1;}
            if(i==1){ j = 2;}
            if(i==2){ j = 4;}
            if (queues[i].isEmpty(j)) {
                System.out.print("Queue " + (i + 1) + " : ");

                // Iterate through positions in the queue and display empty slots
                for (int position = 0; position < 5; position++) {
                    try {
                        // Display an empty slot or placeholder if position is out of bounds
                        queues[i].showEmptyQueue(position);
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                }
                System.out.println(); // Move to the next line after printing each empty queue
            }
        }
    }

    public static void AddCustomer() {
        try {
            customer_count++;

            // Input customer details
            System.out.print("Enter the Customer's First name: ");
            String firstName = input.next();
            System.out.print("Enter the Customer's Last name: ");
            String lastName = input.next();
            System.out.print("Enter the Customer's Phone Number: ");
            int phoneNumber = input.nextInt();
            System.out.print("How many Pizza(s) required: ");
            int pizzaRequired = input.nextInt();

            FoodQueue selectedQueue = null;

            // Find an available queue to add the customer
            boolean customerAdded = false; // use to stop if a customer has been added

            for (int i = 0; i < queues.length; i++) {
                if (customerAdded) {
                    break; // Exit the outer loop if a customer has been added
                }
                try {
                    for (int j = 0; j < 5; j++) {
                        if (queues[i].isEmpty(j)) {
                            selectedQueue = queues[i];
                            selectedQueue.AddCustomer(firstName, lastName, pizzaRequired, phoneNumber, BillNo, j);
                            System.out.println("Customer '" + firstName + " " + lastName + "' is added to Queue " + (i + 1) + " Successfully! (Bill No: " + BillNo + ")");
                            customerAdded = true; // Mark that a customer has been added
                            break;
                        } else continue;
                    }
                }catch(ArrayIndexOutOfBoundsException e){

                }
            }
            customerAdded = true;

            // If no available queue, add to waiting list
            if (customer_count > 10 && selectedQueue == null) {
                if (Waiting_List.isFull()) {
                    System.out.println("Waiting List is Full");
                } else {
                    Waiting_List.enQueue(firstName, lastName, pizzaRequired, pizzaRequired, BillNo);
                    Waiting_List.display();
                }
            }

            // Display warning if pizza stock is low
            if (Pizza < 10) {
                System.out.println("Warning! Pizza Stock is Low!!!");
            }

            // Display remaining stock and update BillNo
            ViewRemainingStock();
            BillNo += customer_count;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input format!!!. Please enter valid data.");
        }
    }


    public static void RemoveCustomer() {
        // Ask user for input
        System.out.print("From which Queue you want to remove the customer? (1 or 2 or 3): ");
        int queue = input.nextInt();

        System.out.print("Which position in the queue you want to remove?: ");
        int position = input.nextInt();

        // Check if input is valid
        try {
            // Check if the selected position in the queue is empty
            if (queues[queue-1].isEmpty(position-1)) {
                System.out.println();
                System.out.println("There is no customer in this Position!");
            } else {
                // Remove the customer and adjust the queue
                queues[queue - 1].RemoveCustomer(position-1);
                System.out.println("Customer removed successfully from Queue " + queue + " at Position " + (position));
                // Move customers to fill the removed position
                for (int i = position-1; i < 4; i++) {
                    try {
                        queues[queue - 1].moveFront(i);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;  // Continue even if exception is caught
                    }
                }

                //FoodQueue.customer_queue[position-1].firstName.equals("X");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            // Handle invalid input
            System.out.println("Invalid input for queue or position.");
        }
    }



    public static void RemoveServedCustomer() {
        // Prompt user for queue number
        System.out.print("In which queue you want to remove the served customer (1 or 2 or 3): ");
        int queueNumber = input.nextInt();

        // Check if the entered queue number is valid (between 1 and 3)
        try{
            FoodQueue selectedQueue = queues[queueNumber - 1];

            // Check if the selected queue is not empty
            if (!selectedQueue.isEmpty(0)) {
                selectedQueue.RemoveCustomer(0);
                System.out.println("Served customer removed from Queue " + queueNumber + " successfully");
                int removedPizza = selectedQueue.PizzaRequired(0);
                Pizza -= removedPizza;

                // Update income based on the queue
                if (queueNumber == 1) {
                    Income_1 += (removedPizza * 1350);
                } else if (queueNumber == 2) {
                    Income_2 += (removedPizza * 1350);
                } else{
                    Income_3 += (removedPizza * 1350);
                }

                // Move front of the queue to fill the removed customer's spot
                for (int i = 0; i < 5; i++) {
                    if (queueNumber == 1 && i < 1) {
                        selectedQueue.moveFront(i);
                    } else if (queueNumber == 2 && i < 2) {
                        selectedQueue.moveFront(i);
                    } else if (queueNumber == 3 && i < 4) {
                        selectedQueue.moveFront(i);
                    } else {
                        break;
                    }
                }

                // Add customer from the waiting list if there's space


            } else {
                System.out.println("Queue " + queueNumber + " is empty.");
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid queue number."); // Display a warning message if the entered queue number is invalid
        }

        // Check if the pizza stock is low and display a warning message
        if (Pizza <= 20) {
            System.out.println("Warning!!! Pizza stock is too low.");
        }
    }


    public static void CustomerSort() {
        // Displaying sorted customer's names for Cashier 1
        System.out.println("Sorted customer's names (Cashier 1) : ");
        queues[0].nameArrange();
        System.out.println(" ");

        // Displaying sorted customer's names for Cashier 2
        System.out.println("Sorted customer's names (Cashier 2) : ");
        queues[1].nameArrange();
        System.out.println(" ");

        // Displaying sorted customer's names for Cashier 3
        System.out.println("Sorted customer's names (Cashier 3) : ");
        queues[2].nameArrange();
        System.out.println(" ");
    }

    public static void StoreProgram() throws IOException {
        File details = new File("ProgramDetails.txt");
        details.createNewFile();  //A new file is created using the method
        FileWriter writer = new FileWriter(details.getName());
        writer.write("** FOODIES FAVE FOOD CENTER **");
        writer.write("\n");
        writer.write("Customer Details : ");
        writer.write("\n");
        writer.write("\n");
        queues[0].write(writer);
        queues[1].write(writer);
        queues[2].write(writer);

        System.out.println("Successfully written !");
        writer.close();
    }

    public static void LoadProgram() throws IOException {  //method to call the save data file
        try{
            File inputFile = new File("ProgramDetails.txt");
            Scanner reader = new Scanner(inputFile);
            String fileLine;
            while (reader.hasNextLine()) {
                fileLine = reader.nextLine(); //or use next() to get word
                System.out.println(fileLine);
            }
            reader.close();
        }
        catch (NumberFormatException e){}
        System.out.println();
    }
    // Method for add Pizzas to stock
    public static void UpdateStock(){
        Scanner input3 = new Scanner(System.in);
        System.out.print("How many Pizzas do you want to add to the stock: ");
        int addPizzaCount = input3.nextInt();
        Pizza = addPizzaCount + Pizza;        // Add Pizzas to the stock.
        System.out.println("Pizzas Stock Updated");
        System.out.println();
    }
    // Display the remaining stock of pizzas
    private static void ViewRemainingStock() {
        System.out.println();
        System.out.println("Remaining Pizzas : "+Pizza);
    }
    // Display the income for each cashier
    public static void  IncomeOfPizzas(){
        System.out.println("Income of cashier 1  : Rs."+Income_1);
        System.out.println("Income of cashier 2  : Rs."+Income_2);
        System.out.println("Income of cashier 3  : Rs."+Income_3);
        System.out.println();
    }
    //
    // Method to exit the program
//    public static void exitProgram() {
//        System.out.println(" Program Exiting... ");
//        System.exit(0);
//    }
}