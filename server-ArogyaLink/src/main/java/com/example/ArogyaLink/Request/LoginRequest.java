package com.example.ArogyaLink.Request;

public class LoginRequest {
    private String email;
    private String password;

    private String role;



    public LoginRequest() {

    }
    public LoginRequest(String email, String password, String Role) {
        super();
        this.email = email;
        this.password = password;
        this.password = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
