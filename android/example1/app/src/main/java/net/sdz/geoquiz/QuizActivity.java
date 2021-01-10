package net.sdz.geoquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String NUMBER_CORRECT = "correct";

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mNumberCorrect = 0;

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mNumberCorrect = savedInstanceState.getInt(NUMBER_CORRECT, 0);
            mTrueButton.setEnabled(savedInstanceState.getBoolean("true"));
            mFalseButton.setEnabled(savedInstanceState.getBoolean("false"));
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentIndex = mCurrentIndex + 1;

                if(mCurrentIndex==mQuestionBank.length) {
                    mNextButton.setEnabled(false);
                    showScore();
                    mCurrentIndex=0;
                    mNumberCorrect =0;

                    updateQuestion();
                    mTrueButton.setEnabled(true);
                    mFalseButton.setEnabled(true);
                    mNextButton.setEnabled(true);
                }else{
                    updateQuestion();
                    mTrueButton.setEnabled(true);
                    mFalseButton.setEnabled(true);
                }
            }
        });


        updateQuestion();
    }
    private void showScore(){


        int score = (int)((((float) mNumberCorrect)/mQuestionBank.length)*100);
        Log.d(TAG, "Score="+score);
        Toast.makeText(this, "Your score is "+score+"%", Toast.LENGTH_LONG)
                .show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(NUMBER_CORRECT, mNumberCorrect);
        savedInstanceState.putBoolean("true", mTrueButton.isEnabled());
        savedInstanceState.putBoolean("false", mFalseButton.isEnabled());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            mNumberCorrect++;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        mTrueButton.setEnabled(false);
        mFalseButton.setEnabled(false);

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
}
