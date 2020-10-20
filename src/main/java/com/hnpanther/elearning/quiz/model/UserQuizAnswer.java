package com.hnpanther.elearning.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_quiz_answer")
@Data
public class UserQuizAnswer {

    @EmbeddedId
    private UserQuizAnswerId userQuizAnswerId;

    @Column(name = "answer")
    private String answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserQuizAnswer that = (UserQuizAnswer) o;
        return Objects.equals(userQuizAnswerId, that.userQuizAnswerId);
    }

    @Override
    public String toString() {
        return "UserQuizAnswer{" +
                "userQuizAnswerId=" + userQuizAnswerId +
                '}';
    }
}
