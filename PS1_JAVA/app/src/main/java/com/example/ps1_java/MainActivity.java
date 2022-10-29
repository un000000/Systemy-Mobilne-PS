package com.example.ps1_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.jar.Attributes;


public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;

    private final Question[] questions = new Question[]{
            new Question(R.string.q_dog, true),
            new Question(R.string.q_cat, false),
            new Question(R.string.q_proboscis, true),
            new Question(R.string.q_rodent, false)
    };
    private int currentIndex = 0;

    private void VerifyAnswer(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].answer;
        int res;
        if (userAnswer == correctAnswer) {
            res = R.string.correct_answer;
        } else {
            res = R.string.incorrect_answer;
        }
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].questionId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyAnswer(true);
            }
        });
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerifyAnswer(false);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });
    }
}
