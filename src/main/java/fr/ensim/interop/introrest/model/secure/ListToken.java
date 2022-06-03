package fr.ensim.interop.introrest.model.secure;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

public class ListToken {
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe
    private static ArrayList<Token> listTokens = new ArrayList<Token>();

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public static ArrayList<Token> returnTokens(){
        listTokens.add(new Token("3XdvoxRjObxCRoOtzyXVfNqG52oAl8R6"));
        listTokens.add(new Token(generateNewToken()));
        listTokens.add(new Token(generateNewToken()));

        return listTokens;
    }
}
