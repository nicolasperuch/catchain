package model;

import utils.HashUtils;

import java.util.Date;

public class Cat {

    private String hash;
    private String previousHash;
    private String data;
    private long bornDate;
    private int nonce;

    public Cat(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.bornDate = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        return HashUtils
                    .applySha256(previousHash + Long.toString(bornDate) +
                                    Integer.toString(nonce) + data);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
        System.out.println("Nonce : " + nonce);
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setData(String data) {
        this.data = data;
        hash = calculateHash();
    }
}
