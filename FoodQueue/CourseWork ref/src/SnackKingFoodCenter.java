import java.util.*;
import java.io.*;
public class SnackKingFoodCenter {
    private static final Scanner scanner = new Scanner(System.in);  // Scanner for input
    public static final int stock = 100;  // Initial stock of items
    public static String[] Customers = new String[50];
    public static int CurrentStock = stock;  // Current available stock

    // Arrays for display the Queues and Customers
    static String[] Cashier1 = {"X", "X"};  // Cashier 1 with 2 slots
    static String[] Cashier2 = {"X", "X", "X"};  // Cashier 2 with 3 slots
    static String[] Cashier3 = {"X", "X", "X", "X", "X"};  // Cashier 3 with 5 slots

    // Arrays to Save customer names for each queue
    static String[] Customer1 = new String[2];  // Queue 1 customer names
    static String[] Customer2 = new String[3];  // Queue 2 customer names
    static String[] Customer3 = new String[5];  // Queue 3 customer names

    static int Queue1Size = 0;  // Current size of Queue 1
    static int Queue2Size = 0;  // Current size of Queue 2
    static int Queue3Size = 0;  // Current size of Queue 3
    public static String filePath = "C:\\Users\\ASUS\\IdeaProjects\\CustomerData.txt";

    public static void main(String[] args) {
        boolean Process = true;  // use to control the main loop

        // Main program loop
        while (Process) {
            Menu();  // Display the menu options
            System.out.println();
            System.out.print("Enter An Option: "); // ask User to Enter a Choice
            String option = scanner.next();  // Get user's choice and Store in a Variable

            // Switch statement to handle menu options
            switch (option) {
                case "100", "VFQ" -> ViewAllQueues();  // Option for View all queues
                case "101", "VEQ" -> ViewAllEmptyQueues();  // Option for View empty queues
                case "102", "ACQ" -> AddCustomer();  // Option for Add a customer in the Queue
                case "103", "RCQ" -> RemoveCustomer();  // Option for Remove a customer From a Specific Queue's Position
                case "104", "PCQ" -> RemoveServedCustomer();  // Option for Remove a served customer From Specific Queue
                case "105", "VCS" -> CustomerSort();  // Option for Sort names of customers in Alphabetic Order
                case "106", "SPD" -> StoreProgram();  // Option for Write program data in a file
                case "107", "LPD" -> LoadProgram();  // Option for Store program data in a file
                case "108", "STK" -> ViewRemainingStock();  // Option for View remaining Pizza stock
                case "109", "AFS" -> UpdateStock();  // Option for add Pizzas to the stock
                case "999", "EXT" -> {
                    System.out.println("---THANK YOU !!!---");
                    Process = false;  // Exit the loop and end the program
                }
                default -> System.out.println("Invalid Option!. Please Enter a Valid Input....");  // Invalid option message
            }
        }
    }

    public static void Menu(){
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
        System.out.println("999 or EXT: Exit the Program");
    }
    public static void ViewAllQueues(){
        System.out.println();
        System.out.println("""
                ******************
                *    Cashiers    *
                ******************""");
        for (int i = 0; i < Cashier3.length; i++) {
            // Print the content of Cashier1 or empty space
            if (i < Cashier1.length) {
                System.out.print("   " + Cashier1[i]);  // Printing Cashier1 content
            } else {
                System.out.print("    ");  // Printing empty space
            }

            // Print the content of Cashier2 or empty space
            if (i < Cashier2.length) {
                System.out.print("     " + Cashier2[i]);  // Printing Cashier2 content
            } else {
                System.out.print("      ");  // Printing empty space
            }

            // Print the content of Cashier3 or empty space
            if (i < Cashier3.length) {
                System.out.print("     " + Cashier3[i]);  // Printing Cashier3 content
            } else {
                System.out.print("   ");  // Printing empty space
            }
            System.out.println();// Moving to the next line after printing one row of Cashier content
        }
    }
    public static void ViewAllEmptyQueues(){
        System.out.println();
        // Display empty slots in Queue 1
        if(Queue1Size<2){
            System.out.print("Queue 1 : ");
            for(int i=Queue1Size;i< Cashier1.length;i++){
                System.out.print(i+1);
                if(i!=Cashier1.length-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        // Display empty slots in Queue 2
        if(Queue2Size<3){
            System.out.print("Queue 2 : ");
            for(int i=Queue2Size;i< Cashier2.length;i++){
                System.out.print(i+1);
                if(i!=Cashier2.length-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        // Display empty slots in Queue 3
        if(Queue3Size<5){
            System.out.print("Queue 3 : ");
            for(int i=Queue3Size;i< Cashier3.length;i++){
                System.out.print(i+1);
                if(i!=Cashier3.length-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
    public static void AddCustomer() {
        try {
            if (CurrentStock > 10) {
                System.out.println();
                System.out.print("Enter Customer Name : ");
                String Name = scanner.next();  // Input customer name

                ViewAllEmptyQueues();  // Display available queues
                System.out.println();
                System.out.print("Enter the Queue : ");
                int QueueNo = scanner.nextInt();  // Input queue number

                // Adding customer to the selected queue based on the queue number
                if (QueueNo == 1) {
                    if (Queue1Size == Cashier1.length) {
                        System.out.println("Queue is Full!!!");
                    } else {
                        Cashier1[Queue1Size] = "O";
                        Customer1[Queue1Size] = Name;
                        Queue1Size++;
                    }
                } else if (QueueNo == 2) {
                    if (Queue2Size == Cashier2.length) {
                        System.out.println("Queue is Full!!!");
                    } else {
                        Cashier2[Queue2Size] = "O";
                        Customer2[Queue2Size] = Name;
                        Queue2Size++;
                    }
                } else if (QueueNo == 3) {
                    if (Queue3Size == Cashier3.length) {
                        System.out.println("Queue is Full!!!");
                    } else {
                        Cashier3[Queue3Size] = "O";
                        Customer3[Queue3Size] = Name;
                        Queue3Size++;
                    }
                } else {
                    System.out.println("Enter a valid Queue Number!");
                }

                // Display warning if pizza stock is low
                if (CurrentStock < 20) {
                    System.out.println("Warning!!! \n Current Pizza Stock is " + CurrentStock);
                }

                // Display success message
                System.out.println();
                System.out.println(" ---Customer " + Name + " Added Successfully in the Queue " + QueueNo + ")---");
                int CustomerCount=0;
                Customers[CustomerCount]=Name;
                CustomerCount++;
            } else {
                System.out.println("Insufficient Pizza Stock!!!");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input format!!!. Please enter valid data.");// Invalid input message
        }
    }

    public static void RemoveCustomer(){
        try {
            System.out.println();
            System.out.print("Enter the Queue: ");
            int QueueNo = scanner.nextInt();

            // Check Queue Number
            if (QueueNo == 1) {
                if (Queue1Size == 0) {
                    System.out.println("Queue is Empty!!!");
                } else {
                    System.out.print("Enter the Position of the Customer: ");
                    int Position = scanner.nextInt();
                    System.out.println();

                    // Check Position validity
                    if (Position < 1 || Position > 2) {
                        System.out.println("Enter a valid Position!");
                    } else {
                        // Check if the customer exists
                        if (Objects.equals(Cashier1[Queue1Size - 1], "X")) {
                            System.out.println("There is No Customer in that Position!!!");
                        } else {
                            // Remove the customer
                            Cashier1[Queue1Size - 1] = "X";
                            System.out.println("---Customer " + Customer1[Position - 1] + " Removed Successfully---");
                            Customer1[Position - 1] = null;
                            Queue1Size--;
                        }
                    }
                }
            } else if (QueueNo == 2) {
                if (Queue2Size == 0) {
                    System.out.println("Queue is Empty!!!");
                } else {
                    System.out.print("Enter the Position of the Customer: ");
                    int Position = scanner.nextInt();
                    System.out.println();

                    // Check Position validity
                    if (Position < 1 || Position > 3) {
                        System.out.println("Enter a valid Position!");
                    } else {
                        // Check if the customer exists
                        if (Objects.equals(Cashier2[Queue2Size - 1], "X")) {
                            System.out.println("There is No Customer in that Position!!!");
                        } else {
                            // Remove the customer
                            Cashier2[Queue2Size - 1] = "X";
                            System.out.println("---Customer " + Customer2[Position - 1] + " Removed Successfully---");
                            Customer2[Position - 1] = null;
                            Queue2Size--;
                        }
                    }
                }
            } else if (QueueNo == 3) {
                if (Queue3Size == 0) {
                    System.out.println("Queue is Empty!!!");
                } else {
                    System.out.print("Enter the Position of the Customer: ");
                    int Position = scanner.nextInt();
                    System.out.println();

                    // Check Position validity
                    if (Position < 1 || Position > 5) {
                        System.out.println("Enter a valid Position!");
                    } else {
                        // Check if the customer exists
                        if (Objects.equals(Cashier3[Queue3Size - 1], "X")) {
                            System.out.println("There is No Customer in that Position!!!");
                        } else {
                            // Remove the customer
                            Cashier3[Queue3Size - 1] = "X";
                            System.out.println("---Customer " + Customer3[Position - 1] + " Removed Successfully---");
                            Customer3[Position - 1] = null;
                            Queue3Size--;
                        }
                    }
                }
            } else {
                System.out.println("Enter a valid Queue Number!");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input format!!!. Please enter valid data.");// Invalid input message
        }

    }
    public static void RemoveServedCustomer(){
        try{
            System.out.println();
            System.out.print("Enter the Queue : ");
            int QueueNo = scanner.nextInt();
            if(QueueNo==1){
                if(Queue1Size==0){// Check if the queue is empty or not
                    System.out.println("Queue is Empty!!!");
                }else{
                    Cashier1[Queue1Size-1]="X";// Remove the customer
                    System.out.println();
                    System.out.println("Customer "+Customer1[0]+" Removed Successfully ");
                    for(int i=0;i<Queue1Size;i++){ //update arrays
                        Customer1[i] = Customer1[i + 1];
                    }
                    CurrentStock-=10;//decrease stock
                    Queue1Size--;
                }
            }else if(QueueNo==2){
                if(Queue2Size==0){// Check if the queue is empty or not
                    System.out.println("Queue is Empty!!!");
                }else{
                    Cashier2[Queue2Size-1]="X";// Remove the customer
                    System.out.println();
                    System.out.println("Customer "+Customer2[0]+" Removed Successfully ");
                    for(int i=0;i<Queue2Size;i++){
                        Customer2[i] = Customer2[i + 1];
                    }
                    CurrentStock-=10;
                    Queue2Size--;
                }
            }else if (QueueNo==3){
                if(Queue3Size==0){// Check if the queue is empty or not
                    System.out.println("Queue is Empty!!!");
                }else{
                    Cashier3[Queue3Size-1]="X";// Remove the customer
                    System.out.println();
                    System.out.println("Customer "+Customer3[0]+" Removed Successfully ");
                    for(int i=0;i<Queue3Size;i++){
                        Customer3[i] = Customer3[i + 1];
                    }
                    CurrentStock-=10;
                    Queue3Size--;
                }
            }else{
                System.out.println("Enter a valid Queue Number!");
            }
        }catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input format. Please enter valid data.");// Invalid input message
        }
    }
    public static void CustomerSort(){
        if(Queue1Size==0 && Queue2Size==0 && Queue3Size==0){
            System.out.println("There is No Customer to Sort!!");
        }
        // Sort and print Queue 1 customers
        if(Queue1Size>0) {
            System.out.println();
            System.out.println("Queue 1 Customer Sort :- ");

            for (int i = 0; i < Queue1Size - 1; i++) {
                for (int j = 0; j < Queue1Size - i - 1; j++) {
                    if (Customer1[j].compareTo(Customer1[j + 1]) > 0) {
                        String Change = Customer1[j];
                        Customer1[j] = Customer1[j + 1];
                        Customer1[j + 1] = Change;
                    }
                }
            }

            for (String Element : Customer1) {
                if (Element != null) {
                    System.out.println("  * " + Element);
                }
            }
        }
        // Sort and print Queue 2 customers
        if(Queue2Size>0) {
            System.out.println();
            System.out.println("Queue 2 Customer Sort :- ");

            for (int i = 0; i < Queue2Size - 1; i++) {
                for (int j = 0; j < Queue2Size - i - 1; j++) {
                    if (Customer2[j].compareTo(Customer2[j + 1]) > 0) {
                        String Change = Customer2[j];
                        Customer2[j] = Customer2[j + 1];
                        Customer2[j + 1] = Change;
                    }
                }
            }

            for (String Element : Customer2) {
                if (Element != null) {
                    System.out.println("  * " + Element);
                }
            }
        }
        // Sort and print Queue 3 customers
        if(Queue3Size>0) {
            System.out.println();
            System.out.println("Queue 3 Customer Sort :- ");

            for (int i = 0; i < Queue3Size - 1; i++) {
                for (int j = 0; j < Queue3Size - i - 1; j++) {
                    if (Customer3[j].compareTo(Customer3[j + 1]) > 0) {
                        String Change = Customer3[j];
                        Customer3[j] = Customer3[j + 1];
                        Customer3[j + 1] = Change;
                    }
                }
            }

            for (String Element : Customer3) {
                if (Element != null) {
                    System.out.println("  * " + Element);
                }
            }
        }
    }
    public static void StoreProgram(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String item : Customers) {
                if (item != null) {
                    writer.write("Customers Name:- ");
                    writer.write(item);
                    writer.newLine();
                }
            }
            System.out.println("Array successfully written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void LoadProgram(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
        }
    }
    public static void ViewRemainingStock() {
        System.out.println();
        System.out.println("---Remaining Pizza Stock is " + CurrentStock+"---");
    }
    public static void UpdateStock(){
        try {
            System.out.println("Enter the Number of Pizzas You Need to Add in The Stock : ");
            int Add = scanner.nextInt(); // Read the number of pizzas to add from user input
            CurrentStock += Add; // Increase the current stock by the user input
            System.out.println(Add + " Successfully Added to the Stock"); // Confirmation message
            ViewRemainingStock(); // Display the updated stock count
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input format. Please enter valid data."); // Invalid input message
        }

    }
}