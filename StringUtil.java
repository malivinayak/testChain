import java.security.MessageDigest;          //to get access to the SHA256 algorithm

public class StringUtil {       //StringUtil applies sha256 security algo to a string and returns a result
    public static String applySha256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies SHA-256 to our String
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();                        //This will Contain Hash As Hexadecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
