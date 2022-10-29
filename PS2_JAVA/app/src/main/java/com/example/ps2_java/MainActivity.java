package com.example.ps2_java;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String QUIZ_TAG = "MainActivity";


    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button promptButton;
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
        int res = 0;
        if (answerWasShown) {
            res = R.string.answer_was_shown;
        } else {
            if (userAnswer == correctAnswer) {
                res = R.string.correct_answer;
            } else {
                res = R.string.incorrect_answer;
            }
        }
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].questionId);
    }

    public static boolean answerWasShown;
    public static final String KEY_CURRENT_INDEX = "currentIndex";
    public static final String KEY_EXTRA_ANSWER = "com.example.ps2_java.correctAnswer";
    public static final int REQUEST_CODE_PROMPT = 0;

    @Override
    protected void onSaveInstanceState(Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        Log.d(QUIZ_TAG, "onSaveInstanceState method called");
        instanceState.putInt(KEY_CURRENT_INDEX, currentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {return;}
        if (requestCode == REQUEST_CODE_PROMPT) {
            if (data == null) {
                return;
            }
            answerWasShown = data.getBooleanExtra(PromptActivity.KEY_EXTRA_ANSWER_SHOWN, false);
        }
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(QUIZ_TAG, "onCreate method called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_CURRENT_INDEX);
        }

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);
        promptButton = findViewById(R.id.prompt_button);

        trueButton.setOnClickListener(v -> VerifyAnswer(true));
        falseButton.setOnClickListener(v -> VerifyAnswer(false));
        nextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % questions.length;
            answerWasShown = false;
            setNextQuestion();
        });
        promptButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PromptActivity.class);
            boolean correctAnswer = questions[currentIndex].answer;
            intent.putExtra(KEY_EXTRA_ANSWER, correctAnswer);
            startActivityForResult(intent, REQUEST_CODE_PROMPT);
        });
        setNextQuestion();
    }

}
