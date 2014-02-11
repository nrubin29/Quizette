package me.nrubin29.quizette.quiz;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents a quiz. It is abstract to ensure it cannot be instantiated.
 * Use the correct subclass for the quiz you want to make or make your own!
 */
public abstract class Quiz {

    private final String name, question;
    private final int time;
    private final ArrayList<String> answers, remainingAnswers;

    public Quiz(String name, int time, String question, String... answers) {
        this.name = name;
        this.time = time;
        this.question = question;
        this.answers = new ArrayList<String>(Arrays.asList(answers));
        this.remainingAnswers = new ArrayList<String>(this.answers);
    }

    public final String getName() {
        return name;
    }

    public final String getQuestion() {
        return question;
    }

    public final int getTime() {
        return time;
    }

    public final String[] getRemainingAnswers() {
        return remainingAnswers.toArray(new String[remainingAnswers.size()]);
    }

    public final int getAnswerCount() {
        return answers.size();
    }

    public final boolean isAnswer(String str) {
        String toRemove = null;

        for (String answer : remainingAnswers) {
            if (str.matches(answer)) {
                toRemove = answer;
            }
        }

        if (toRemove != null) {
            remainingAnswers.remove(toRemove);
            return true;
        }

        return false;
    }
}