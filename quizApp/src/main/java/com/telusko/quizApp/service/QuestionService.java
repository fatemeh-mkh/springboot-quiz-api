package com.telusko.quizApp.service;

import com.telusko.quizApp.Question;

import com.telusko.quizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository ;

    public List<Question> getAllQuestions() {
         return questionRepository.findAll();
    }

//-----------------------------------------------------------------------------------------------------
public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
    try {
        List<Question> questions = questionRepository.findByCategory(category);

        if (questions == null || questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    //-------------------------------------------------------------------------------------------------
    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findQuestionById(id);
    }

    public String addQuestion(Question question) {
       questionRepository.save(question);
       return "Success";
    }

    public void insertCustom(Question question) {

        questionRepository.insertQuestion(
                question.getQuestion_title(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4(),
                question.getRight_answer(),
                question.getDifficulty_level(),
                question.getCategory()
        );
    }

    public boolean deleteQuestion(Integer id) {
        if (questionRepository.existsById(id)) {
            questionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
