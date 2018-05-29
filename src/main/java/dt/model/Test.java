package dt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name="tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="t_id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testconfigs_id")
    private TestConfiguration testConfiguration;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient_id")
    private Patient patient;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public TestConfiguration getTestConfiguration() {
        return testConfiguration;
    }

    public void setTestConfiguration(TestConfiguration testConfiguration) {
        this.testConfiguration = testConfiguration;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
