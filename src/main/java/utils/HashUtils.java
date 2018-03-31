package utils;

import model.Cat;

import java.security.MessageDigest;
import java.util.List;

public class HashUtils {

    public static String applySha256(String input){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isChainValid(List<Cat> chain, int difficulty){

        Cat currentCat;
        Cat previousCat;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for(int i=1; i < chain.size(); i++) {

            currentCat = chain.get(i);
            previousCat = chain.get(i-1);

            if(!isHashEquals(currentCat, currentCat.getHash())){
                System.out.println("Current Hashes not equal");
                return false;
            }
            if(!isHashEquals(previousCat, currentCat.getPreviousHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }

            if(!currentCat.getHash().substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    public static String getPreviousHash(List<Cat> catchain){
        return catchain.get(catchain.size() - 1).getHash();
    }

    private static boolean isHashEquals(Cat firstCat, String hash){
        return firstCat.getHash().equals(hash);
    }

}
