import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Shared_var {                              //Contains shared variable and locked methods to maintain synchronization
    public static int size=Main.TraySize();
    public static int Produced=Main.checkWeek();       //The number to of cupcakes to be produced by each baker

    public static Struct[] cupcakes=new Struct[size]; //This is the shared array containing the cupcakes that has been eaten
    public static Pro_Thread[] bakers;                // Save the producer threads array as Shared
    public static synchronized void producerUpdate(Struct cupcake, int pro_num){    // The locked method that allows a producer to access the array
        //Critical Section of the producer
        String writeText = "";
        Top.updateTop(1);                                                       // Synchronized method to update "top" it adds 1 to "top"
        cupcakes[Top.getTop()]= cupcake;
        writeText = "Baker "+pro_num+": ("+Top.getTop()+") "+cupcake.getFullFlavor(); // To show the progress
        System.out.println(writeText);
        writeToFile(writeText);
    }
    public static synchronized void consumerUpdate(int con_num){    // The locked method that allows a consumer to access the array
        //Critical Section of the consumer
        String writeText = "";
        int index= Top.getTop();                                    // Synchronized method to get the most updated value  of "top"
        writeText = "Customer "+con_num+": (" +index+") "+cupcakes[index].getFullFlavor()+" & ("+(index-1)
                +") "+cupcakes[index-1].getFullFlavor();
        Top.updateTop(-2);                                         // Adds -2 to "top"
        System.out.println(writeText);                               // To show the progress
        writeToFile(writeText);
    }


    public static void writeToFile (String text) {
        String filePath = "Output.txt";

        try {
            File txtFile = new File(filePath);

            if (txtFile.createNewFile()) { //checks if the file needs to be created
                System.out.println("File Created: "+ txtFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating text file");
            e.printStackTrace();
        }

        try(FileWriter fw=new FileWriter(filePath,true);
            PrintWriter pw=new PrintWriter(fw)){
//            The FileWriter class us used to write data into the file
//            The PrintWriter class us used to write formatted text into the file
                pw.println(text); //writes the text that was passed as an argument to the file
        } catch (IOException e) {
            System.out.println("An error occurred while to writing to the file.");
            e.printStackTrace();
        }

    }



}
class BakerWait {                       //To synchronize the bakers
    public synchronized static void bakerWait(Struct cupcake, int pro_num){     // Locked to ensure that no more than one baker is waiting
        while (Top.getTop()==Shared_var.size-1);                            // Wait if the array is full
        Shared_var.producerUpdate(cupcake,pro_num);                         // Pass the baker's number and the cupcake
    }


}
class CustomerWait {                   //To synchronize the customer
    public synchronized static int customerWait(int con_num,int numOfCakes){    // Locked to ensure that no more than one customer is waiting
        while(Top.getTop()<=0){                                                 // Wait if the array is empty
            if (!(Baker.isBakerAvailable())) return numOfCakes;                 // If no bakers are working, leave
        }
        Shared_var.consumerUpdate(con_num);                                     //Otherwise update the array and pass the customer algorithm
        numOfCakes+=2;                                                          // increment the number of cakes this customer ate by 2
        return numOfCakes;
    }

}
class Top{                          // To synchronize access to "top"
    public static int top=-1;

    public synchronized static void updateTop(int i){
        top+=i;
    }
    public  synchronized static int getTop(){
        return top;
    }

}
