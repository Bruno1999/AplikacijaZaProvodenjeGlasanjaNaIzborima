package com.example.javafxaplikacijazaprovodenjeglasanjanaizborima.entitet;

public class User extends Entitet{

    private String username;

    private String password;

    private Role rola;

    public User(long id,String username, String password, Role rola) {
        super(id);
        this.username = username;
        this.password = password;
        this.rola = rola;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRola() {
        return rola;
    }

    public void setRola(Role rola) {
        this.rola = rola;
    }
}
