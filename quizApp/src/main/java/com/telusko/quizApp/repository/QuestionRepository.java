package com.telusko.quizApp.repository;

import com.telusko.quizApp.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    Optional<Question> findQuestionById(Integer id);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO question (question_title, option1, option2, option3, option4, right_answer, difficulty_level, category) " +
            "VALUES (:questionTitle, :option1, :option2, :option3, :option4, :rightAnswer, :difficultyLevel, :category)", nativeQuery = true)
    void insertQuestion(@Param("questionTitle") String questionTitle,
                        @Param("option1") String option1,
                        @Param("option2") String option2,
                        @Param("option3") String option3,
                        @Param("option4") String option4,
                        @Param("rightAnswer") String rightAnswer,
                        @Param("difficultyLevel") String difficultyLevel,
                        @Param("category") String category);

}

