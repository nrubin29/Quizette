package me.nrubin29.quizette.gui;

import me.nrubin29.quizette.quiz.Quiz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

/*
 -------------------------------
|           Question            |
|             Time              |
|                               |
|          [ Input ]            |
|                               |
| xxxxxxxxxxxxxxxxxxxxxxxxxxxxx | // x = table answer area.
| xxxxxxxxxxxxxxxxxxxxxxxxxxxxx |
| xxxxxxxxxxxxxxxxxxxxxxxxxxxxx |
| xxxxxxxxxxxxxxxxxxxxxxxxxxxxx |
| xxxxxxxxxxxxxxxxxxxxxxxxxxxxx |
 -------------------------------
 */
public class QuizGUI extends JFrame {

    private JLabel question, time;
    private JTextField input;
    private JTable answersTable;
    private DefaultTableModel answersTableModel;

    private int currentColumn, currentRow;

    private Timer timer;

    public QuizGUI(final Quiz quiz) {
        super(quiz.getName());

        question = new JLabel(quiz.getQuestion());
        question.setAlignmentX(Component.CENTER_ALIGNMENT);

        time = new JLabel(quiz.getTime() != 0 ? "Time Left: " + quiz.getTime() : "No time limit.");
        time.setAlignmentX(Component.CENTER_ALIGNMENT);

        input = new JTextField("Click to start.", 20);
        input.setEnabled(false);
        input.setMaximumSize(new Dimension(620, 30));
        input.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (input.getText().equals("Click to start.")) {
                    input.setText("");
                    input.setEnabled(true);
                    input.requestFocusInWindow();

                    if (quiz.getTime() != 0) timer.start();
                    // TODO: Start timer (once timer is implemented).
                }
            }
        });

        input.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && quiz.isAnswer(input.getText())) {
                    String text = input.getText();

                    input.setText("");

                    if (setGrid(text) && currentColumn == quiz.getAnswerCount() / 4) {
                        if (timer.isRunning()) timer.stop();
                        input.setText("You win!");
                        input.setEnabled(false);
                    }
                }
            }
        });

        answersTableModel = new DefaultTableModel();

        answersTable = new JTable(answersTableModel);
        answersTable.setBackground(getBackground());
        answersTable.setGridColor(getBackground());

        for (int i = 0; i < 4; i++) answersTableModel.addColumn(new TableColumn(), new Object[quiz.getAnswerCount() / 4]);

        timer = new Timer(1000, new ActionListener() {
            int timeLeft = quiz.getTime();
            public void actionPerformed(ActionEvent e) {
                timeLeft--;

                time.setText("Time Left: " + timeLeft);

                if (timeLeft == 0) {
                    timer.stop();
                    input.setText("You ran out of time!");
                    input.setEnabled(false);

                    /*
                    NOTE: If the answer is in regex format, the regex will show.
                     */
                    for (String answer : quiz.getRemainingAnswers()) {
                        setGrid(answer);
                    }
                }
            }
        });

        timer.setRepeats(true);

        add(question);
        add(time);
        add(input);
        add(answersTable);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(640, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private boolean setGrid(String text)  {
        answersTableModel.setValueAt(text, currentColumn, currentRow++);

        if (currentRow == 4) {
            currentRow = 0;
            currentColumn++;
            return true;
        }

        return false;
    }
}