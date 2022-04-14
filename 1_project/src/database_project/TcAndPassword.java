/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database_project;

/**
 *
 * @author SaidPolat
 */
public class TcAndPassword {
    
    private String tc, password;

    public TcAndPassword(String tc, String password) {
        this.tc = tc;
        this.password = password;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
