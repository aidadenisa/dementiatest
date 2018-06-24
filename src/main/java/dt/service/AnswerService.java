package dt.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import dt.model.*;
import dt.repository.AnswerRepository;

import dt.utils.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestService testService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private TestConfigurationService testConfigurationService;

    @Autowired
    private QuestionService questionService;

    public List<Answer> getAllAnswers() {

        List<Answer> answers = new ArrayList<>();
        answerRepository.findAll().forEach(answers::add);
        return answers;

    }

    public void addAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public Answer getAnswer(long answerId) {
        return answerRepository.findOne(answerId);
    }

    public void updateAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    public void deleteAnswer(long answerConfigId) {
        answerRepository.delete(answerConfigId);
    }

    public void addAnswers(List<Answer> answers) {

        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(answers.get(0).getTest().getTestConfiguration().getId());

        Test test = testService.getTest(answers.get(0).getTest().getId());

        Patient patient = patientService.getPatient(answers.get(0).getPatient().getId());

        answerRepository.save(answers);
    }

    public Test saveAnswersToTest(long patientId, long testConfigId, List<Answer> answers) {

        TestConfiguration testConfiguration = testConfigurationService.getTestConfiguration(testConfigId);
        Patient patient = patientService.getPatient(patientId);

        Test existingTest = testService.getTestByPatientAndConfiguration(testConfiguration, patient);
        Test test = new Test();

        List<Answer> existingAnswers;

        if(existingTest == null) {

            test.setTestConfiguration(testConfiguration);
            test.setPatient(patient);
            testService.addTest(test);

            for (int i = 0; i < answers.size(); i++) {

                Answer answer= answers.get(i);

                createNewAnswer(patient, test, answer);
            }

            saveScoreOfTest(test,answers);

            answerRepository.save(answers);

            return testService.updateTest(test);

        } else {

            existingAnswers = answerRepository.findAllByPatientAndTest(patient,existingTest);

            if(existingAnswers.size() > 0) {
                for (int i = 0; i < answers.size(); i++) {

                    Answer answer = getExistingAnswer(answers.get(i), existingAnswers);

                    if(answer != null) {
                        answer.setAnswer(answers.get(i).getAnswer());
                    } else {
                        answer = answers.get(i);
                        createNewAnswer(patient,test,answer);
                    }

                    setScore(answer.getQuestion(),answer);

                }
            }

            saveScoreOfTest(existingAnswers.get(0).getTest(),existingAnswers);

            answerRepository.save(existingAnswers);

            return testService.updateTest(existingAnswers.get(0).getTest());

        }

    }

    private void createNewAnswer(Patient patient, Test test, Answer answer) {

        answer.setTest(test);

        answer.setPatient(patient);

        Question question = questionService.getQuestion(answer.getQuestion().getId());
        answer.setQuestion(question);

        setScore(question,answer);

    }

    private Answer getExistingAnswer(Answer answer, List<Answer> existingAnswers) {

        for( int i = 0; i< existingAnswers.size(); i++ ) {
            if(existingAnswers.get(i).getQuestion().getId() == answer.getQuestion().getId()) {
                return existingAnswers.get(i);
            }
        }
        return null;
    }

    private void saveScoreOfTest(Test test, List<Answer> answers) {

        int totalScore = 0;

        for(int i=0; i < answers.size(); i++ ) {
            totalScore += answers.get(i).getScore();
        }

        test.setScore(totalScore);
    }

    private void setScore(Question question, Answer answer) {

        if (question.getDrawingConfiguration() != null
                && question.getDrawingConfiguration()
                && question.getCorrectAnswer() != null
                && question.getCorrectAnswer().equals("cube")) {

//            scoreCanvasBasedQuestion(
//                    answer,
//                    "\\root\\dementia-cnn\\cube-cnn\\predict.py",
//                    "\\root\\dementia-cnn\\prediction\\image.jpg",
//                    "\\root\\dementia-cnn\\cube-cnn\\"
//            );

            scoreCanvasBasedQuestion(
                    answer,
                    "/root/dementia-cnn/cube-cnn/predict.py",
                    "/root/dementia-cnn/prediction/image.jpg",
                    "/root/dementia-cnn/cube-cnn"
            );
        }

        if( question.getDrawingConfiguration() != null
                && question.getDrawingConfiguration()
                && question.getCorrectAnswer() != null
                && question.getCorrectAnswer().equals("clock")) {

//            scoreCanvasBasedQuestion(
//                    answer,
//                    "\\root\\dementia-cnn\\clock-cnn\\predict.py",
//                    "\\root\\dementia-cnn\\prediction\\image.jpg",
//                    "\\root\\dementia-cnn\\clock-cnn\\"
//            );

            scoreCanvasBasedQuestion(
                    answer,
                    "/root/dementia-cnn/clock-cnn/predict.py",
                    "/root/dementia-cnn/prediction/image.jpg",
                    "/root/dementia-cnn/clock-cnn"
            );

        }

        if( question.getDragAndDropConfiguration() != null && question.getDragAndDropConfiguration()) {

//            scoreCanvasBasedQuestion(
//                    answer,
//                    "\\root\\dementia-cnn\\squares-cnn\\predict.py",
//                    "\\root\\dementia-cnn\\prediction\\image.jpg",
//                    "\\root\\dementia-cnn\\squares-cnn\\"
//            );

            scoreCanvasBasedQuestion(
                    answer,
                    "/root/dementia-cnn/clock-cnn/predict.py",
                    "/root/dementia-cnn/prediction/image.jpg",
                    "/root/dementia-cnn/clock-cnn"
            );

        }

        if(question.getDateConfiguration() != null && question.getDateConfiguration()) {
            scoreDateConfiguration(answer);
        }

        if(question.getImage1() != null) {
            scoreImageNamingQuestion(answer, question);
        }

        if(question.getImage1() == null
                && question.getSimilatitiesConfiguration() == null
                && question.getMemoryConfiguration() == null
                && question.getInputConfiguration() != null
                && question.getInputConfiguration()) {

            scoreSingleInputQuestion(answer,question);
        }

        if(question.getMemoryConfiguration() != null && question.getMemoryConfiguration()) {
            scoreMemoryQuestion(answer,question);
        }

        if(question.getSimilatitiesConfiguration() != null && question.getSimilatitiesConfiguration()) {
            scoreSimilaritiesConfiguration(answer,question);
        }

        if(question.getMultipleTextConfiguration() != null && question.getMultipleTextConfiguration() ) {
            scoreMultipleInputQuestion(answer);
        }

        if(question.getPoints().size() > 0 ) {
            scoreConnectedPointsQuestion(answer, question);
        }

    }

    private void scoreMemoryQuestion(Answer answer, Question question) {

        answer.setScore(0);

        if(answer.getAnswer().trim().toLowerCase().equals(question.getCorrectAnswer().trim().toLowerCase())) {
            answer.setScore(2);
        } else {

            String[] correctAnswer = question.getCorrectAnswer().split("\\s+|\\+|,|\\.|-|_|!\\?|;");

            if(answer.getAnswer().trim().toLowerCase().contains(correctAnswer[correctAnswer.length-1].trim().toLowerCase())) {
                answer.setScore(1);
            }
        }

    }

    private void scoreSimilaritiesConfiguration(Answer answer, Question question) {

        String[] keywordsFromJson = question.getCorrectAnswer().split("\\|");
        List<String> keywords = Arrays.asList(keywordsFromJson);
        for(int i = 0; i< keywords.size(); i++) {
            keywords.set(i, keywords.get(i).trim().toLowerCase());
        }

        String[] patientAnswer = answer.getAnswer().split("\\s+|\\+|,|\\.|-|_|!\\?|;");
        answer.setScore(0);

        for(int i = 0; i< patientAnswer.length; i++) {
            if(keywords.indexOf(patientAnswer[i].trim().toLowerCase()) > -1 ) {
                answer.setScore(2);
            }
        }


    }

    private void scoreConnectedPointsQuestion(Answer answer, Question question) {

        int countCorrectPointsMatched = 1;
        Type listType = new TypeToken<ArrayList<ConnectPoint>>(){}.getType();
        List<ConnectPoint> connectPoints =  new Gson().fromJson(answer.getAnswer(), listType);

        for( int i = 1; i < connectPoints.size(); i++ ) {
            if(connectPoints.get(i - 1).getIndex() + 1 == connectPoints.get(i).getIndex() ) {
                countCorrectPointsMatched += 1;
            }
        }

        if(countCorrectPointsMatched  == question.getPoints().size()) {
            answer.setScore(2);
        } else {
            if(countCorrectPointsMatched >= question.getPoints().size() - 2) {
                answer.setScore(1);
            } else {
                answer.setScore(0);
            }
        }

    }

    private void scoreMultipleInputQuestion(Answer answer) {

        int countCorrectAnswers = 0;

        String[] multipleInputAnswers = answer.getAnswer().split("\\|");

        for(int i=0; i<multipleInputAnswers.length; i++) {

            multipleInputAnswers[i] = multipleInputAnswers[i].trim().toLowerCase();

            if(AnimalService.isAnimal(multipleInputAnswers[i])) {
                countCorrectAnswers += 1;
            }
        }

        switch (countCorrectAnswers) {
            case 12:
                answer.setScore(2);
                break;
            case 11:
            case 10:
                answer.setScore(1);
                break;
            default:
                answer.setScore(0);
                break;
        }

    }

    private void scoreSingleInputQuestion(Answer answer, Question question) {
        if(question.getCorrectAnswer().trim().toLowerCase().equals(answer.getAnswer().trim().toLowerCase())) {
            answer.setScore(1);
        } else {
            answer.setScore(0);
        }
    }

    private void scoreImageNamingQuestion(Answer answer, Question question) {

        if(answer.getAnswer().toLowerCase().equals(question.getImage1().toLowerCase())) {
            answer.setScore(1);
        } else {
            answer.setScore(0);
        }
    }

    private void scoreDateConfiguration(Answer answer) {
        int countCorrectScore = 0;
        String[] splitDate = answer.getAnswer().split("-");

        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[2]);

        LocalDate today = LocalDate.now();

        if(year == today.getYear()) {
            countCorrectScore += 1;
        }
        if(month == today.getMonthValue()) {
            countCorrectScore += 1;
        }
        if( day == today.getDayOfMonth()) {
            countCorrectScore += 2;
        } else {
            LocalDate date3DaysBeforeToday = LocalDate.now().minusDays(3);

            LocalDate date3DaysAfterToday = LocalDate.now().plusDays(3);

            LocalDate userDayInputWithCurrentMonth = LocalDate.now();
            try {
                userDayInputWithCurrentMonth = userDayInputWithCurrentMonth
                        .withDayOfMonth(day)
                        .withMonth(today.getMonthValue());
            } catch(Exception e) {
                userDayInputWithCurrentMonth = userDayInputWithCurrentMonth.withMonth(today.getMonthValue() - 1)
                        .withDayOfMonth(day);
            }

            LocalDate userDayInputWithShiftedMonth = LocalDate.now();
            if(date3DaysBeforeToday.getMonth() != today.getMonth()) {

                try {
                    userDayInputWithShiftedMonth = userDayInputWithShiftedMonth
                            .withMonth(date3DaysBeforeToday.getMonthValue())
                            .withDayOfMonth(day);

                } catch(Exception e) {
                    userDayInputWithShiftedMonth = userDayInputWithShiftedMonth.withMonth(date3DaysBeforeToday.getMonthValue() - 1)
                            .withDayOfMonth(day);
                }


            } else {

                    if (date3DaysAfterToday.getMonth() != today.getMonth()) {
                        try {
                            userDayInputWithShiftedMonth = userDayInputWithShiftedMonth
                                    .withMonth(date3DaysAfterToday.getMonthValue())
                                    .withDayOfMonth(day);
                        } catch(Exception e) {
                            userDayInputWithShiftedMonth = userDayInputWithShiftedMonth
                                    .withMonth(date3DaysAfterToday.getMonthValue() - 1)
                                    .withDayOfMonth(day);
                        }
                    }

            }

            if(
                (userDayInputWithCurrentMonth.isBefore(date3DaysAfterToday) && userDayInputWithCurrentMonth.isAfter(date3DaysBeforeToday))
                || (userDayInputWithShiftedMonth.isBefore(date3DaysAfterToday) && userDayInputWithShiftedMonth.isAfter(date3DaysBeforeToday))
            ) {
                countCorrectScore += 1;
            }
        }

        answer.setScore(countCorrectScore);
    }

    private void scoreCanvasBasedQuestion(Answer answer, String relativePathToScript, String relativePathToImage, String relativePathToScriptFolder) {

        //decode image bytes from encoded string
        byte[] decodedImage = Base64.decode(answer.getAnswer());

        try {

            BufferedImage image = ImageIO.read(new ByteArrayInputStream(decodedImage));
            BufferedImage bwimage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

            inverseColorsOfImage(image);

//            File imagefile = new File("\\root\\dementia-cnn\\prediction\\image.jpg");
            File imagefile = new File("/root/dementia-cnn/prediction/image.jpg");
            ImageIO.write(bwimage, "jpg", imagefile);

            String scriptResult = executeAIScript(
                    relativePathToScript,
                    relativePathToImage,
                    relativePathToScriptFolder
            );

            System.out.println("acesta este rezultatul după analiza: " + scriptResult);
            if(scriptResult != null && scriptResult.equals("1")) {
                answer.setScore(2);
            } else {
                answer.setScore(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String executeAIScript(String relativePathToScript, String relativePathToImage, String relativePathToScriptFolder) {

        String workingDirectoryPath = null;
        Process process;
        String line = "";
        String output = null;
        try {

//            workingDirectoryPath = new File(".").getCanonicalPath();

//            Path pathToScript = Paths.get(workingDirectoryPath + relativePathToScript).toRealPath();
//            Path pathToImage = Paths.get(workingDirectoryPath + relativePathToImage).toRealPath();
//            Path pathToScriptFolder = Paths.get(workingDirectoryPath + relativePathToScriptFolder).toRealPath();


            Path pathToScript = Paths.get( relativePathToScript);
            Path pathToImage = Paths.get(relativePathToImage);
            Path pathToScriptFolder = Paths.get( relativePathToScriptFolder);



            System.out.println( "path to script" + pathToScript.toString() );
            System.out.println( "path to image" + pathToImage.toString() );
            System.out.println( "path to script folder" + pathToScriptFolder.toString() );

            String[] cmd = {
                    "/usr/bin/python3",
                    pathToScript.toString(),
                    "--image",
                    pathToImage.toString(),
                    "--scriptFolder",
                    pathToScriptFolder.toString()
            };

            process = Runtime.getRuntime().exec(cmd);

            process.waitFor();

            BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            while ((output=bfr.readLine())!=null)
            {

                return output;
            }

            line = bfr.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void inverseColorsOfImage(BufferedImage image) {

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);

                double luminosity = 0.2126*col.getRed() + 0.7152*col.getGreen() + 0.0722*col.getBlue();
                if(luminosity < 30){
                    col = new Color(Color.white.getRGB());
                } else {
                    col = new Color(Color.black.getRGB());
                }
                image.setRGB(x, y, col.getRGB());
            }
        }

    }

    public List<Answer> getAnswersOfPatientTest(long patientId, long testId) {
        Patient patient = patientService.getPatient(patientId);
        Test test = testService.getTest(testId);

        return answerRepository.findAllByPatientAndTest(patient, test);
    }
}
