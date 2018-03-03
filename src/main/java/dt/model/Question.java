package dt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dt.model.TestConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="q_id")
    private int id;

//    @NotNull
    @Column(name="q_text")
    private String text;

    @Column(name="q_instructions")
    private String instructions;

    @Column(name="q_choices")
    private String choices;

    @Column(name="q_image1")
    private String image1;

    @Column(name="q_image2")
    private String image2;

    @ManyToMany(mappedBy = "questions", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TestConfiguration> testConfigurations;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public List<TestConfiguration> getTestConfigurations() {
        return testConfigurations;
    }

    public void setTestConfigurations(List<TestConfiguration> testConfigurations) {
        this.testConfigurations = testConfigurations;
    }
}
