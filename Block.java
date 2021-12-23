import java.util.Date;      //To get Current timeStamp for Block

public class Block {
    private final String data;
    private String hash;
    public String previousHash;
    private final long timeStamp;
    public int miner;

    //Block Creating Method
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();            //Calculating Hash After Reciveing all parameters
    }

    //Calculate Hash Value using this.parameters
    public String calculateHash() {
        String calculateHash = StringUtil.applySha256(previousHash + Long.toString(timeStamp)  + Integer.toString(miner) + data);
        return calculateHash;
    }

    public String getHash() {
        return hash;
    }

    public String getData() {
        return data;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            miner++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}
