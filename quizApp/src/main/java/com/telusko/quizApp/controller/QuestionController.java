package com.telusko.quizApp.controller;

import com.telusko.quizApp.Question;
import com.telusko.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.springframework.util.ClassUtils.isPresent;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(),HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Integer id) {
        Optional<Question> question = questionService.getQuestionById(id);

        if (question.isPresent()) {
            return ResponseEntity.ok(question.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Question with ID " + id + " not found");
        }
    }

    @PostMapping("addQuestion")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("addQuestionByParam")
    public ResponseEntity<String> addQuestionWithParam(@RequestBody Question question) {
        questionService.insertCustom(question);
        return ResponseEntity.ok("Question inserted successfully!");
    }


    @DeleteMapping("deleteQuestion/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Integer id) {
        boolean Qdelete=questionService.deleteQuestion(id);

        if(Qdelete){
            return ResponseEntity.status(HttpStatus.OK).body("Question with ID " + id + " deleted successfully!");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question with ID " + id + " not found");
        }
    }
}
