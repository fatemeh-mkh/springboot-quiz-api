package com.telusko.quizApp.controller;

import com.telusko.quizApp.model.Question;
import com.telusko.quizApp.model.QuestionDto;
import com.telusko.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {


    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String>createQuiz(@RequestParam String category,
                                            @RequestParam int numQ,
                                            @RequestParam String title) {

       return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizService(id);
    }
}
