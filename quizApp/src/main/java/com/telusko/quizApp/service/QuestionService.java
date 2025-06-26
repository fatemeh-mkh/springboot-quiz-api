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
}
