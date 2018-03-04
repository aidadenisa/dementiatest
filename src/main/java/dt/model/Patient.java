package dt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="p_id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "u_id")
    private User userAccount;

    @OneToMany(mappedBy="patient")
    @JsonIgnore
    private List<Test> takenTests;

    @ManyToMany(mappedBy = "patients")
    @JsonIgnore
    private List<Doctor> doctors;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }

    public List<Test> getTakenTests() {
        return takenTests;
    }

    public void setTakenTests(List<Test> takenTests) {
        this.takenTests = takenTests;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
