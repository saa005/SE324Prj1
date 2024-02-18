import java.util.Random;

public class Baker {

    String letters = "COVS";  //C=Chocolate,O=Orange,V=Vanilla,S=Strawberry
    Random random = new Random();

    public void produce(int bakerNum) throws Exception {
        for(int i=0;i<Shared_var.Produced;i++){             // Any producer arriving here, will execute the loop until reaching the number of cakes to be baked
            int randomIndex = random.nextInt(letters.length());
            char randomLetter = letters.charAt(randomIndex);    // Get a random letter from "COVS". Basically a random flavor
            Struct cupcake = new Struct();
            cupcake.setValues(i,randomLetter);                  // Bake a new cupcake passing the index of the cupcake as well as the flavor
            BakerWait.bakerWait(cupcake,bakerNum);              // This method is synchronized (Can be accessed by only one at time)
        }

    }
    public static boolean isBakerAvailable(){                   // Check if there is at least one baker that have not finished
        for (Thread baker:Shared_var.bakers){
            if (baker.isAlive()) return true;
        }
        return false;
    }

}
