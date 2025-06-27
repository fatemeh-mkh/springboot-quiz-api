package com.telusko.quizApp.service;

import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.model.QuestionDto;
import com.telusko.quizApp.model.Quiz;
import com.telusko.quizApp.repository.QuestionRepository;
import com.telusko.quizApp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuizService(Integer id) {
        Optional<Quiz> quiz=quizRepository.findById(id);
        List<Question> getQuestionFromeDb=quiz.get().getQuestions();
        List<QuestionDto> questionDtoListForUser= getQuestionFromeDb.stream()
                .map(q -> new
                        QuestionDto(q.getId(),
                        q.getQuestion_title(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(questionDtoListForUser, HttpStatus.OK);
    }
}
