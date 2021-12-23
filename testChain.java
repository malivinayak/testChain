/*
Project testChain
Developed by: Vinayak Mali
 */

//Libraries
import java.util.ArrayList;
import java.util.Scanner;


public class testChain {

    public static ArrayList<Block> testchain = new ArrayList<>();
    public static int difficulty = 5;                                        //must be less than 10 to increase mining speed


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ch; String data;

        System.out.println("""
                    |------------------------------------------------------------------------------------------------------------|
                    |                                **************** testChain ****************                                 |
                    |------------------------------------------------------------------------------------------------------------|
                    |                                                                                                            |
                    |  testChain is a demo Project, to get basic understanding of BlockChain.                                    |
                    |                                                                                                            |
                    |                                                                         developed by : Vinayak Mali        |
                    |------------------------------------------------------------------------------------------------------------|
                    
                    
                   """);

        do{
            System.out.print("""
                    Choose appropriate option
                    1. Display testChain in JSON Form
                    2. Insert Block into testChain
                    3. Exit
                    ```````Enter Your Choice : \t""");
            ch = input.nextInt();

            if(ch == 1) {
                if(testchain.size() == 0) {
                    System.out.println("\n\ntestChain is Empty\nTry display function after inserting Block in testChain \n");
                }
                else {
                    System.out.println("\nBlockchain is Valid: " + isChainValid());                                     //Validate the testChain
                    int chain=1;
                    System.out.println("\nThe testChain : ");
                    for(Block testChainData : testchain){
                        System.out.println("\ntestChain Block "+chain++ +" :");
                        System.out.println("Data : " + testChainData.getData());
                        System.out.println("Hash : " + testChainData.getHash());
                        System.out.println("Mining Run : " + testChainData.miner);
                    }
                    System.out.println("\n");
                }
            }

            else if(ch ==2) {
                System.out.print("\n\nEnter Data to insert into testChain :  ");
                input.nextLine();
                data = input.nextLine();
                if(testchain.size() == 0){
                    testchain.add(new Block(data,"0"));
                }
                else {
                    testchain.add(new Block(data, testchain.get(testchain.size()-1).getHash()));
                }
                System.out.println(" ------ Mining "+(testchain.size())+"th block ------");
                testchain.get(testchain.size()-1).mineBlock(difficulty);
                System.out.println("\n");
            }
            else if(ch == 3 ) {
                System.out.println("""
                        |------------------------------------------------------------------------------------------------------------|
                        |                                                                                                            |
                        |                 ~~~~~~~~~~~~~~~~~~~~~~THANK YOU FOR USING testChain ~~~~~~~~~~~~~~~~~~~~~~                 |
                        |                                                                                                            |
                        |------------------------------------------------------------------------------------------------------------|
                        """);
            }

            else{
                System.out.println(" WRONG CHOICE \nTRY AGAIN!!!!!!!!");
            }


        }while (ch!=3);

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for (int i = 1; i < testchain.size(); i++) {
            currentBlock = testchain.get(i);
            previousBlock = testchain.get(i-1);

            //compare registered hash and calculated hash:
            if(!currentBlock.getHash().equals(currentBlock.calculateHash())){
                System.out.println("Current Hash Not Equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.getHash().equals(currentBlock.previousHash)){
                System.out.println("Previous Hash not Equal");
                return false;
            }
            //Check if hash is solved or mineing is completed
            if(!currentBlock.getHash().substring(0,difficulty).equals(hashTarget)) {
                System.out.println("This Block Haven't been mined yet");
                return false;
            }
        }
        return true;
    }
}
