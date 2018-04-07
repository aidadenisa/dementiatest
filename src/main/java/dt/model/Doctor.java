package dt.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="d_id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "u_id")
    private UserAccount userAccount;

    @ManyToMany
    @JoinTable(name="doctors_patients",
            joinColumns = { @JoinColumn(name="d_id")},
            inverseJoinColumns = {@JoinColumn(name="p_id")})
    private List<Patient> patients;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
