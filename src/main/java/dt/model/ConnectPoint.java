package dt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="connect_points")
public class ConnectPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cp_id")
    private int id;

    @Column(name="cp_x")
    private int x;

    @Column(name="cp_y")
    private int y;

    @Column(name="cp_code")
    private String code;

    @Column(name="cp_index")
    private int index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="question_id")
    private Question question;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
