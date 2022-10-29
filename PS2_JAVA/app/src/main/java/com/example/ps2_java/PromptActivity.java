package com.example.ps2_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.temporal.ValueRange;

public class PromptActivity extends AppCompatActivity {
    private boolean correctAnswer;

    private Button showAnswerButton;
    private TextView answerTextView;
    private TextView hintPrompt;
    public static final String KEY_EXTRA_ANSWER_SHOWN = "com.example.ps2_java.answerShown";
    private void setAnswerShownResult(Boolean shown){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXTRA_ANSWER_SHOWN, shown);
        setResult(RESULT_OK, resultIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prompt);

        correctAnswer = getIntent().getBooleanExtra(MainActivity.KEY_EXTRA_ANSWER, true);
        showAnswerButton = findViewById(R.id.show_answer_button);

        answerTextView = findViewById(R.id.answer_text_view);
        hintPrompt = findViewById(R.id.hint_prompt);

        showAnswerButton.setOnClickListener(v -> {
            int answer = correctAnswer ? R.string.button_true : R.string.button_false;
            answerTextView.setText(answer);
            setAnswerShownResult(true);
        });


    }
}