package me.nrubin29.quizette.quiz;

/**
 * The standard quiz type. The question has many short answers, and you need to guess all of them before the time runs out.
 * Example: List all of the characters in [insert tv show or movie here].
 */
public class GuessListQuiz extends Quiz {

    public GuessListQuiz(String name, int time, String question, String... answers) {
        super(name, time, question, answers);
    }
}