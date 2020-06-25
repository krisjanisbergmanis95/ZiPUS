package com.venta.ZiPUS.models.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table
@Entity(name = "User_Table")
@NoArgsConstructor @Getter @Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "U_ID")

    @Setter(value = AccessLevel.PRIVATE)
    private long u_ID;

    @Column(name = "U_Name")
    private String name;

    @Column(name = "U_Surname")
    private String surname;

    @Column(name = "U_Username")
    private String username;

    @Column(name = "U_Password") //TODO more secure
    private String password;

    @Column(name = "U_Email")
    private String email;

    @Column(name = "U_Type")
    private String type;  //TODO types



    public void setPassword(String password)  {
        this.password = password;
        
    }



    public User(String name, String surname, String username, String email, String password, String type) {
        super();
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }








    @Override
    public String toString() {
        return "User [name=" + name + ", surname=" + surname + ", username=" + username + ", email=" + email + ", type="
                + type + "]";
    }

}








