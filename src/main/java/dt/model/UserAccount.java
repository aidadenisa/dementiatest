package dt.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="u_id")
    private int id;

//    @NotNull
    @Column(name="u_hash", unique = true)
    private String hash;

//    @NotNull
    @Column(name="u_firstname")
    private String firstName;

//    @NotNull
    @Column(name="u_lastname")
    private String lastName;

//    @NotNull
    @Column(name="u_birthday")
    private Date birthday;

//    @NotNull
    @Column(name="u_email", unique = true)
    private String email;

    @Column(name="u_address")
    private String address;

    // 0 - patient, 1 - doctor
//    @NotNull
    @Column(name="u_role")
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
