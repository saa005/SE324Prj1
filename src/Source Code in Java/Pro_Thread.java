
public class Pro_Thread extends Thread{
    private int bakerNum=0; // To keep track of the baker's number
    public Pro_Thread(int bakerNum){
        this.bakerNum=bakerNum;
    }

    @Override
    public void run() {     // The method to be executed by all producer threads simultaneously
        Baker baker=new Baker();
        try {
            baker.produce(this.bakerNum); //Pass the baker's number to keep track of it

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
