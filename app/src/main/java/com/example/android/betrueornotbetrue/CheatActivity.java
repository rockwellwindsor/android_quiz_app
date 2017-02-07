package com.example.android.betrueornotbetrue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.betrueornotbetrue.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.betrueornotbetrue.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private static final String TAG = "Cheat Activity";

    private static final String CHEATING_STATUS = "index";
    private boolean mAnswerWasShown;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Log.i("onCreate Called", Boolean.toString(mAnswerWasShown));
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerWasShown = true;
                setAnswerShownResult(mAnswerWasShown);

                Log.i(TAG, "Answer was shown");
                Log.i(TAG, "Answer status :" + Boolean.toString(mAnswerWasShown));
            }
        });
        if (savedInstanceState != null) {
            mAnswerWasShown = savedInstanceState.getBoolean(CHEATING_STATUS, mAnswerWasShown);
            if (mAnswerWasShown) {
                mAnswerTextView.setText(R.string.true_button);
                mAnswerWasShown = true;
                setAnswerShownResult(mAnswerWasShown);
                Log.i(TAG, "Answer status onCreate:" + Boolean.toString(mAnswerWasShown));
            } else {
                //
            }
        }
    }

    private void setAnswerShownResult(boolean shown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, shown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Answer status :" + Boolean.toString(mAnswerWasShown));
        Log.i(TAG, "onSavedInstanceState called");

        savedInstanceState.putBoolean(CHEATING_STATUS, mAnswerWasShown);

    }
}
