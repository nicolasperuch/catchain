import com.google.gson.GsonBuilder;
import model.Cat;

import java.util.ArrayList;
import java.util.List;

import static utils.HashUtils.getPreviousHash;
import static utils.HashUtils.isChainValid;

public class CatChain {

    public static List<Cat> catchain = new ArrayList<>();
    private static int difficulty = 5;


    public static void main(String[] args) {
        catchain.add(new Cat("Meow GOD", "0"));
        System.out.println("Trying to Mine cat 1... ");
        catchain.get(0).mineBlock(difficulty);

        catchain.add(new Cat("Meowseis", getPreviousHash(catchain)));
        System.out.println("Trying to Mine cat 2... ");
        catchain.get(1).mineBlock(difficulty);

        catchain.add(new Cat("Meowhammed", getPreviousHash(catchain)));
        System.out.println("Trying to Mine cat 3... ");
        catchain.get(2).mineBlock(difficulty);

        catchain.get(0).setData("Dog");

        String chainStatus = isChainValid(catchain, difficulty) ? "valid Chain" :
                                                                  "invalid Chain";

        System.out.println("Cat chain is a " + chainStatus);

        String catchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(catchain);
        System.out.println(catchainJson);
    }
}
