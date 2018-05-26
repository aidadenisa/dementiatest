package dt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dt.model.TestConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.*;
import java.util.List;

@Entity
@Table(name="questions")
public class Question implements Comparable{

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

    @Column(name="q_yesOrNoConfig")
    private Boolean yesOrNoConfiguration;

    @Column(name="q_onlyOccasionally")
    private Boolean onlyOccasionallyOption;

    @Column(name="q_yesWithSpecification")
    private Boolean yesAnswerWithSpecification;

    @Column(name="q_dateConfig")
    private Boolean dateConfiguration;

    @Column(name="q_noImmediateAnswerConfig")
    private Boolean noImmediateAnswer;

    @Column(name="q_drawingConfig")
    private Boolean drawingConfiguration;

    @Column(name="q_multipleTextConfig")
    private Boolean multipleTextConfiguration;

    @Column(name="q_dragAndDropConfig")
    private Boolean dragAndDropConfiguration;

    @Column(name="q_inputConfig")
    private Boolean inputConfiguration;

    @Column(name="q_index")
    private int index;

    @OneToMany(mappedBy="question")
    private List<ConnectPoint> points;

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

    public Boolean getYesOrNoConfiguration() {
        return yesOrNoConfiguration;
    }

    public Boolean getOnlyOccasionallyOption() {
        return onlyOccasionallyOption;
    }

    public Boolean getYesAnswerWithSpecification() {
        return yesAnswerWithSpecification;
    }

    public void setYesOrNoConfiguration(Boolean yesOrNoConfiguration) {
        this.yesOrNoConfiguration = yesOrNoConfiguration;
    }

    public void setOnlyOccasionallyOption(Boolean onlyOccasionallyOption) {
        this.onlyOccasionallyOption = onlyOccasionallyOption;
    }

    public void setYesAnswerWithSpecification(Boolean yesAnswerWithSpecification) {
        this.yesAnswerWithSpecification = yesAnswerWithSpecification;
    }

    public Boolean getDateConfiguration() {
        return dateConfiguration;
    }

    public void setDateConfiguration(Boolean dateConfiguration) {
        this.dateConfiguration = dateConfiguration;
    }

    public Boolean getNoImmediateAnswer() {
        return noImmediateAnswer;
    }

    public void setNoImmediateAnswer(Boolean noImmediateAnswer) {
        this.noImmediateAnswer = noImmediateAnswer;
    }

    public Boolean getDrawingConfiguration() {
        return drawingConfiguration;
    }

    public void setDrawingConfiguration(Boolean drawingConfiguration) {
        this.drawingConfiguration = drawingConfiguration;
    }

    public Boolean getMultipleTextConfiguration() {
        return multipleTextConfiguration;
    }

    public void setMultipleTextConfiguration(Boolean multipleTextConfiguration) {
        this.multipleTextConfiguration = multipleTextConfiguration;
    }

    public Boolean getDragAndDropConfiguration() {
        return dragAndDropConfiguration;
    }

    public void setDragAndDropConfiguration(Boolean dragAndDropConfiguration) {
        this.dragAndDropConfiguration = dragAndDropConfiguration;
    }

    public Boolean getInputConfiguration() {
        return inputConfiguration;
    }

    public void setInputConfiguration(Boolean inputConfiguration) {
        this.inputConfiguration = inputConfiguration;
    }

    public List<ConnectPoint> getPoints() {
        return points;
    }

    public void setPoints(List<ConnectPoint> points) {
        this.points = points;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public int compareTo(Object o) {

        return this.index - ((Question)o).getIndex();
    }
}
