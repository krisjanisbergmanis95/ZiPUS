package com.venta.zipus.models.user;

import com.venta.zipus.customvalidators.PasswordMatches;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Table
@Entity(name = "User_Table")
@NoArgsConstructor
@Getter
@Setter
//@PasswordMatches
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "U_ID")

    @Setter(value = AccessLevel.PRIVATE)
    private long u_ID;

//    @NotNull
//    @NotEmpty
    @Column(name = "U_Name")
    private String name;

//    @NotNull
//    @NotEmpty
    @Column(name = "U_Surname")
    private String surname;

//    @NotNull
//    @NotEmpty
    @Column(name = "U_Username")
    private String username;

//    @NotNull
//    @NotEmpty
    @Column(name = "U_Password") //TODO more secure
    private String password;

    private String matchingPassword;

//    @NotNull
//    @NotEmpty
    @Column(name = "U_Email")
    private String email;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "User_Authority",
            joinColumns = @JoinColumn(name = "rId"),
            inverseJoinColumns = @JoinColumn(name = "uID"))
    private Collection<UserAuthority> authorities;

    public User(String username, String password, UserAuthority... roles) {
        this.username = username;
        this.password = password;
        //creation of authorities. copied algorithm from InMemoryUserDetailsManager constructor
        this.authorities = new ArrayList<>();
        UserAuthority[] tempArgsArray = roles;
        int tempArgsArrayLength = roles.length;

        for(int i = 0; i < tempArgsArrayLength; ++i) {
            UserAuthority authority = tempArgsArray[i];
            this.authorities.add(authority);
        }
    }

    public User(String name, String surname, String username, String email, String password, ArrayList<UserAuthority> roles) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = roles;
    }

    public User(String username, String password, ArrayList<UserAuthority> roles) {
        this.username = username;
        this.password = password;
        this.authorities = roles;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", surname=" + surname + ", username=" + username + ", email=" + email + "]";
    }
}
