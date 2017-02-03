package com.example.android.betrueornotbetrue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private TextView mQuestionTextView;

    // For more complex applicaitons this array would be created and stored elsewhere.
    private Question[] mQuestionArray = new Question[] {
        new Question(R.string.question1, true),
        new Question(R.string.question2, true),
        new Question(R.string.question3, true),
        new Question(R.string.question4, true),
        new Question(R.string.question5, true),
    };

    private int mCurrentIndex = 0;

    // Update the question index
    private void updateQuestion() {
        int question = mQuestionArray[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    // Check if the answer is correct or not
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionArray[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionArray[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
//                Toast.makeText(MainActivity.this, R.string.incorrect_answer, Toast.LENGTH_SHORT).show();
            }
        });

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionArray.length;
                int question = mQuestionArray[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionArray.length;
                int question = mQuestionArray[mCurrentIndex].getTextResId();
                mQuestionTextView.setText(question);
            }
        });

        updateQuestion();
    }
}
