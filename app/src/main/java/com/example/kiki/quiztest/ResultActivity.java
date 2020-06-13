package com.example.kiki.quiztest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    TextView grade, finalScore;
    Button retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        grade = (TextView)findViewById(R.id.grade);
        finalScore = (TextView)findViewById(R.id.finalScore);
        retryButton = (Button)findViewById(R.id.retry);


        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("finalScore");

        finalScore.setText((("You scored " + score)));

        if (score == 5){
            grade.setText("Outstanding! You are aware of your male prvilege.");
        }else if (score == 4){
            grade.setText("Good Work! You are so close. Keep doing the work.");
        }else if (score == 3) {
            grade.setText("Meh, work on doing better.");
        }else if (score == 2) {
            grade.setText("You've got a lot of work to do, asshole.");
        } else {
            grade.setText("You are a total dick.");
        }

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, QuizActivity.class));
                ResultActivity.this.finish();
            }
        });

    }
}
