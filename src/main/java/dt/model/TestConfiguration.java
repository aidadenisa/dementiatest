package dt.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="testconfigs")
public class TestConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="testconfigs_id")
    private int id;

    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name="test_question_config",
            joinColumns = { @JoinColumn(name="testconfigs_id")},
            inverseJoinColumns = {@JoinColumn(name="q_id")})
    private List<Question> questions;

    public TestConfiguration() {

    }

    public TestConfiguration(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
