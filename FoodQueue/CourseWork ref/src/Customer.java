class Customer {
    // Initializing default values for customer details
    public String firstName = "X";  // First name of the customer
    public String secondName = "X"; // Last name of the customer
    public int noOfPizzas = 0;      // Number of pizzas required by the customer
    public int PhoneNumber;         // Phone number of the customer
    int BillNo;                     // Bill number for the customer

    // Method to set customer data
    public void setData(String firstName, String secondName, int noOfPizzas, int PhoneNumber, int BillNo) {
        // Setting the provided data to customer attributes
        this.firstName = firstName;
        this.secondName = secondName;
        this.noOfPizzas = noOfPizzas;
        this.PhoneNumber = PhoneNumber;
        this.BillNo = BillNo;
    }

}