package dt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="t_id")
    private long id;

    @Column(name="t_score")
    private int score;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testconfigs_id")
    private TestConfiguration testConfiguration;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="patient_id")
    private Patient patient;

    public long getId() {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
