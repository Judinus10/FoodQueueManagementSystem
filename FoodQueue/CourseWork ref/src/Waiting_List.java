class Waiting_List {
    public Customer[] waiting;
    int size;      // Size of Circular Queue
    static int front;
    int rear;

    // Constructor for the Waiting_List class
    // Initializes the waiting list with the given size, creates Customer objects for each slot and sets their initial data to placeholder values.
    public Waiting_List(int size) {
        this.size = size;  // Initialize the size of the waiting list
        waiting = new Customer[size];  // Create an array to hold customer objects

        // Initialize each slot in the waiting list array with a new Customer object and set its initial data to placeholder values.
        for (int i = 0; i < waiting.length; i++) {
            waiting[i] = new Customer();
            waiting[i].setData("X", "X", 0, 0, 0);
        }

        front = -1;  // Initialize the front pointer to -1
        rear = -1;   // Initialize the rear pointer to -1
    }


    // Check if the queue is full
    boolean isFull() {  //Check the queue is Full or not
        return (front == rear + 1) || (front == 0 && rear == size - 1);
    }
    static boolean isEmpty() {  //Check the queue is Empty or not
        return front == -1;
    }
    // Adding an element
    public void enQueue(String first_name, String last_name, int no_of_pizza, int phone_number, int bill_no) {
        // Check if the waiting list is full
        if (isFull()) {
            System.out.println("Waiting List is full");
        } else {
            // If the waiting list is not full, add the customer to the waiting list
            if (front == -1)
                front = 0;
            rear = (rear + 1) % size;
            waiting[rear].setData(first_name, last_name, no_of_pizza, phone_number, bill_no);
            System.out.println("Customer '" + first_name + " " + last_name + "' is added to the waiting list successfully. (Bill no : " + bill_no + " )");
        }
    }


    // Removing an element
    public Customer deQueue() {
        Customer element; // Variable to store the dequeued element
        if (isEmpty()) {
            System.out.println("Waiting List is empty"); // Display message if the waiting list is empty
            return waiting[6]; // Return a specific value (waiting[6]) to indicate an empty queue
        } else {
            element = waiting[front]; // Store the element at the front of the queue
            if (front == rear) {
                front = -1; // If there was only one element in the queue, reset the front and rear pointers
                rear = -1;
            } else {
                front = (front + 1) % size; // Move the front pointer to the next position in a circular manner
            }
            return element; // Return the dequeued element
        }
    }

    public Customer Details(){
        return waiting[front];  // Return the details of the customer at the front of the waiting list
    }

    public void display() {
        /* Function to display the status of the Circular Queue */
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");  // Display a message if the queue is empty
        } else {
            System.out.println("** Waiting List Details **");
            for (i = front; i != rear; i = (i + 1) % size)
                System.out.print(waiting[i].firstName + ", ");  // Display each customer's first name
            System.out.println(waiting[i].firstName);  // Display the last customer's first name
        }
    }

}
