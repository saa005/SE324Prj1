public class Con_Thread extends Thread{
    private int customerNum;
    private int numOfCakes;         // To keep track of the number of cakes that each customer ate
    public static int numCustomers;// To keep track of the customer's number
    private static int count=0;    // Used for the waiting loop below

    public Con_Thread(int customerNum){
        this.customerNum=customerNum;
        this.numOfCakes=0;
    }

    @Override
    public void run() {         // The method to be executed by all consumer threads simultaneously
        Customer customer= new Customer();
        numOfCakes=customer.consume(this.customerNum,this.numOfCakes); // Once the customer is done, return the number of cakes he ate
        count++;
        while (count!=numCustomers){                    // customer will wait for others to leave, once they all leave the loop will be broken
            System.out.print("");
        }
        System.out.println("customer "+this.customerNum+" ate "+ this.numOfCakes); //all consumers will arrive here at the same time. This is the last statement executed
    }
}
