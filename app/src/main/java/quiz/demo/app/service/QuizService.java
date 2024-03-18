package quiz.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import quiz.demo.app.entity.Question;
import quiz.demo.app.entity.QuestionFormat;
import quiz.demo.app.entity.Result;
import quiz.demo.app.repository.QuestionRepo;
import quiz.demo.app.repository.ResultRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {
    @Autowired
    Question question;
    @Autowired
    QuestionFormat questionFormat;
    @Autowired
    Result result;
    @Autowired
    ResultRepo resultRepo;
    @Autowired
    QuestionRepo questionRepo;

    public QuestionFormat getQuestionFormat() {
        List<Question> allQues = questionRepo.findAll();
        List<Question> questionList = new ArrayList<Question>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(allQues.size());
            questionList.add(allQues.get(rand));
            allQues.remove(rand);
        }
        questionFormat.setQuestions(questionList);
        return questionFormat;
    }

    public int getResult(QuestionFormat questionFormat) {
        int correct = 0;
        for (Question q : questionFormat.getQuestions())
            if (q.getAns() == q.getChose())
                correct++;
        return correct;



    }
    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        resultRepo.save(saveResult);
    }
    public List<Result> getTopScore(){
        List<Result> sList = resultRepo.findAll(Sort.by(Sort.Direction.DESC));
        return sList;
    }





}
