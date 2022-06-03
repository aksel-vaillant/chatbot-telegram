package fr.ensim.interop.introrest.model.secure;

public class Token {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    Token(){}

    public Token(String token){
        this.token = token;
    }
}
