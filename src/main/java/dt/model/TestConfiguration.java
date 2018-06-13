package dt.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="testconfigs")
public class TestConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="testconfigs_id")
    private long id;

    @Column(name="t_name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="test_question_config",
            joinColumns = { @JoinColumn(name="testconfigs_id")},
            inverseJoinColumns = {@JoinColumn(name="q_id")})
    private List<Question> questions;

    public TestConfiguration() {

    }

    public TestConfiguration(int id) {
        this.id = id;
    }

    public long getId() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
