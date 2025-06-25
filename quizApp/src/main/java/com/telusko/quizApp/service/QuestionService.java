package com.telusko.quizApp.service;

import com.telusko.quizApp.Question;

import com.telusko.quizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository ;

    public List<Question> getAllQuestions() {
         return questionRepository.findAll();
    }
}
