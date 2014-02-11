package me.nrubin29.quizette;

import me.nrubin29.quizette.gui.QuizGUI;
import me.nrubin29.quizette.quiz.GuessListQuiz;

public class Quizette {

    public static void main(String[] args) {
        GuessListQuiz testQuiz = new GuessListQuiz(
                "Fruit list!",
                5,
                "Can you name all the common fruits?",
                "app(le)?",
                "orange|yellow",
                "peach",
                "pear",
                "banana",
                "blueberry",
                "plum",
                "strawberry"
        );

        new QuizGUI(testQuiz);
    }
}