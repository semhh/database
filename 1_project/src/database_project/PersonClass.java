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
public class PersonClass {
    
    private String tc, name, surname, password;

    public PersonClass(String tc, String name, String surname, String password) {
        this.tc = tc;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Object getByName(String attributeName) {
		switch (attributeName) {
		case "TC": return tc;
		case "Name": return name;
		case "Surname": return surname;
                case "UserPassword": return password;
		default: return null;
		}
	}

    @Override
    public String toString() {
        return "PersonClass{" + "tc=" + tc + ", name=" + name + ", surname=" + surname + '}';
    }
    
    
}
