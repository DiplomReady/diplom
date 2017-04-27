package com.example.evgen.diplom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.evgen.diplom.Level;
import com.example.evgen.diplom.Operation;
import com.example.evgen.diplom.R;
import com.example.evgen.diplom.SettingsManager;

import java.util.Random;

public class ExercisesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_exercises, container,
                false);

        final Button startBtn = (Button) rootView.findViewById(R.id.start_btn);
        final TextView timerText = (TextView) rootView.findViewById(R.id.timer);

        final TextView firstText = (TextView) rootView.findViewById(R.id.first_value);
        final TextView secondText = (TextView) rootView.findViewById(R.id.second_value);

        final TextView operation = (TextView) rootView.findViewById(R.id.operation);

        int currentOperation = SettingsManager.getInstance().getCurrentOperation();
        if (currentOperation == Operation.OPERATION_RANDOM) {
            Random random = new Random();
            currentOperation = random.nextInt(5);
        }
        switch (currentOperation) {

            case Operation.OPERATION_DIVISION:
                operation.setText("/");
                break;
            case Operation.OPERATION_INCREASE:
                operation.setText("*");
                break;
            case Operation.OPERATION_MINUS:
                operation.setText("-");
                break;
            case Operation.OPERATION_PLUS:
                operation.setText("+");
                break;
            case Operation.OPERATION_SQRT:
                operation.setText("^1/2");
                break;
            case Operation.OPERATION_SQR:
                operation.setText("^2");
                break;
        }

        final TextView positiveValue = (TextView) rootView.findViewById(R.id.pozitive_value);
        final TextView negativeValue = (TextView) rootView.findViewById(R.id.negative_value);

        final CountDownTimer countDownTimer = new CountDownTimer(SettingsManager.getInstance().getCurrentSpeed() * 1000 + 100, 1000) {
            @Override
            public void onTick(long milliss) {
                timerText.setText("" + (int) milliss / 1000);
            }

            @Override
            public void onFinish() {
                negativeValue.setText(String.valueOf(Integer.valueOf(negativeValue.getText().toString()) + 1));
                timerText.setText("");
            }
        };

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                startBtn.setText("Следующий");
                int randomValue = 10;
                int currentLevel = SettingsManager.getInstance().getCurrentLevel();
                switch (currentLevel) {
                    case Level.LEVEL_EXPERT:
                        randomValue = 1000;
                        break;
                    case Level.LEVEL_HIGHT:
                        randomValue = 100;
                        break;
                    case Level.LEVEL_NORMAL:
                        randomValue = 10;
                        break;
                }

                Random random = new Random();
                firstText.setText(String.valueOf(random.nextInt(randomValue)));
                secondText.setText(String.valueOf(random.nextInt(randomValue)));

                if (SettingsManager.getInstance().isShowTimer()) {
                    countDownTimer.start();
                }
            }
        });

        final EditText resultEdit = (EditText) rootView.findViewById(R.id.result);
        final int finalCurrentOperation = currentOperation;
        resultEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                 if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    int firstValue = Integer.valueOf(firstText.getText().toString());
                    int secondValue = Integer.valueOf(secondText.getText().toString());
                    int result = -2;
                    if (!TextUtils.isEmpty(resultEdit.getText().toString())) {
                        result = Integer.valueOf(resultEdit.getText().toString());
                    }
                    Log.d("***", "*" + firstValue + "*" + secondValue + "*" + result);
                    int correctResult = -1;

                    switch (finalCurrentOperation) {

                        case Operation.OPERATION_DIVISION:
                            correctResult = firstValue / secondValue;
                            break;
                        case Operation.OPERATION_INCREASE:
                            correctResult = firstValue * secondValue;
                            break;
                        case Operation.OPERATION_MINUS:
                            correctResult = firstValue - secondValue;
                            break;
                        case Operation.OPERATION_PLUS:
                            correctResult = firstValue + secondValue;
                            break;
                        case Operation.OPERATION_SQRT:
                            correctResult = (int) Math.sqrt(firstValue);
                            break;
                        case Operation.OPERATION_SQR:
                            correctResult = firstValue * secondValue;
                            break;
                    }

                    if (correctResult == result) {
                        positiveValue.setText(String.valueOf(Integer.valueOf(positiveValue.getText().toString()) + 1));
                    } else {
                        negativeValue.setText(String.valueOf(Integer.valueOf(negativeValue.getText().toString()) + 1));
                    }
                    countDownTimer.cancel();
                }
                return false;
            }
        });


        return rootView;
    }
}
