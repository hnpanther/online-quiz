package com.hnpanther.elearning.quiz.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class UserQuizAnswerId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuizAnswerId that = (UserQuizAnswerId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(quiz, that.quiz) &&
                Objects.equals(question, that.question);
    }

    @Override
    public String toString() {
        return "UserQuizAnswerId{" +
                "user=" + user.getId() +
                ", quiz=" + quiz.getId() +
                ", question=" + question.getId() +
                '}';
    }
}
