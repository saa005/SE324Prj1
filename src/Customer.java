public class Customer {
    public int consume (int customerNum,int numOfcakes) {
        while(Baker.isBakerAvailable() || !(Top.getTop()<=0)) {           //Continue as long as the array is not empty or there is at least one baker available
            numOfcakes=CustomerWait.customerWait(customerNum,numOfcakes); // Only accessed by one customer at a time
        }
        return numOfcakes;                                                // Return the number of cakes this customer ate
    }


}