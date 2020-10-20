package com.hnpanther.elearning.quiz.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "duration")
    private LocalDateTime duration;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "show_result")
    private boolean showResult;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "quiz")
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "userQuizAnswerId.quiz")
    private Set<UserQuizAnswer> userQuizAnswerSet = new HashSet<>();


    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }

    public void addUserQuizAnswer(UserQuizAnswer userQuizAnswer) {
        this.userQuizAnswerSet.add(userQuizAnswer);
    }

    public void removeUserQuizAnswer(UserQuizAnswer userQuizAnswer) {
        this.userQuizAnswerSet.remove(userQuizAnswer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return id.equals(quiz.id);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", enabled=" + enabled +
                ", showResult=" + showResult +
                ", courseName=" + course.getName() +
                '}';
    }
}
