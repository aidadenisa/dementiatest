package dt.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="a_id")
    private long id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="test_id")
    private Test test;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

    @Column(name="a_answer")
    private String answer;

    @Column(name="a_score")
    private int score;

    public long getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
