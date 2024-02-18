import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Struct {               //Used to bake cupcakes
    String filePath = "Output.txt";
    int id;                        //Cupcake index
    char Flavour;
    void setValues(int num,char flavour){   //set the values of index and flavor
        id =num;
        Flavour =flavour;
    }
    public String getFullFlavor(){     //Convert a letter in "COVS" to its flavor
        return switch (Flavour) {
            case 'C' -> "Chocolate";
            case 'O' -> "Orange";
            case 'V' -> "Vanilla";
            case 'S' -> "Strawberry";
            default -> "";
        };
    }

}

