import java.util.Scanner;
import java.io.IOException;



public class Main {
    static Scanner input = new Scanner(System.in);  //for user input

    public static int checkWeek(){                  // To set the number of cakes to be produced by each baker to either 200 if it is a weekend or 100 if not
        System.out.println("Is it a weekend?(Y/N)");
        char EndorDay = input.next().charAt(0);

        if (Character.toLowerCase(EndorDay)=='y'){
            return 200;
        }
        else{
            return 100;
        }
    }
    public static int TraySize(){                 //Takes the size of the array from the user
        int arrSize;
        System.out.println("Enter the size of the tray: ");
        arrSize=input.nextInt();
        return arrSize;
    }



    public static void main(String[] args) throws IOException {

                                                                    // Take the number of
        System.out.println("Enter the number of bakers: ");         // 
        int numBakers=input.nextInt();                              //Bakers(producers)
        System.out.println("Enter the number of customers: ");      //and
        int numCustomers= input.nextInt();                          //Customers(Consumers)
        Con_Thread.numCustomers=numCustomers;                                 


        Pro_Thread[] producers=new Pro_Thread[numBakers];           // Create an array for producers
        Con_Thread[] consumers=new Con_Thread[numCustomers];        // For consumers
        Thread[] all=new Thread[producers.length+ consumers.length];// An array containg all threads
        for (int i=0;i< producers.length;i++){                      //Create producer threads and store them in their array
            Pro_Thread producer=new Pro_Thread(i);
            producers[i]=producer;
            all[i]=producer;                                        // Add them to the collective array
        }
        Shared_var.bakers =producers;
        for (int i=0;i< consumers.length;i++){                      //Create consumer threads and store them in their array
            Con_Thread consumer=new Con_Thread(i);
            consumers[i]=consumer;
            all[producers.length+i]=consumer;                       // Add them to the collective array
        }

        for (Thread i :all){                                        // Start all threads one by one
            i.start();
        }

        for (Thread i :all){                                        // wait for all of them
            try{
                i.join();
            }
            catch (Exception e){
                System.out.println("e");
            }
        }
    }

}
