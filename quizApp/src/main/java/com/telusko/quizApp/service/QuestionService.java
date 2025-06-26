package com.telusko.quizApp.service;

import com.telusko.quizApp.Question;

import com.telusko.quizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository ;

    public List<Question> getAllQuestions() {
         return questionRepository.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

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
