package com.example.evgen.diplom.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
        final ImageView firstValueImage = (ImageView) rootView.findViewById(R.id.first_value_image);

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
                operation.setText("");
                break;
            case Operation.OPERATION_SQR:
                operation.setText("^2");
                break;
        }

        final TextView positiveValue = (TextView) rootView.findViewById(R.id.pozitive_value);
        final TextView negativeValue = (TextView) rootView.findViewById(R.id.negative_value);
        final TextView precentage = (TextView) rootView.findViewById(R.id.precentage);

        final EditText resultEdit = (EditText) rootView.findViewById(R.id.result);
        final CountDownTimer countDownTimer = new CountDownTimer(SettingsManager.getInstance().getCurrentSpeed() * 1000 + 100, 1000) {
            @Override
            public void onTick(long milliss) {
                if (SettingsManager.getInstance().isShowTimer()) {
                    timerText.setText("" + (int) milliss / 1000);
                } else {
                    timerText.setText("");
                }
            }

            @Override
            public void onFinish() {
                resultEdit.setText("");
                negativeValue.setText(String.valueOf(Integer.valueOf(negativeValue.getText().toString()) + 1));
                double positInt = Double.valueOf(positiveValue.getText().toString());
                double negatInt = Double.valueOf(negativeValue.getText().toString());
                if (positInt == 0) {
                    precentage.setText("" + "0");
                } else if (negatInt == 0d) {
                    precentage.setText("" + "100");
                } else if (positInt > negatInt) {
                    precentage.setText("" + ((int)(100 - negatInt/positInt * 100)));
                } else if (positInt == negatInt) {
                    precentage.setText("" + "50");
                } else {
                    precentage.setText("" + ((int)(positInt/negatInt * 100)));
                }
                timerText.setText("");
            }
        };
        final int finalCurrentOperation = currentOperation;



        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNext(countDownTimer, resultEdit, startBtn, firstText, finalCurrentOperation, firstValueImage, secondText);
            }
        });

        resultEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    int firstValue = Integer.valueOf(firstText.getText().toString());
                    int secondValue = 0;
                    if (!TextUtils.isEmpty(secondText.getText().toString())) {
                        secondValue = Integer.valueOf(secondText.getText().toString());
                    }
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
                            correctResult = firstValue * firstValue;
                            break;
                    }

                    if (correctResult == result) {
                        positiveValue.setText(String.valueOf(Integer.valueOf(positiveValue.getText().toString()) + 1));
                    } else {
                        negativeValue.setText(String.valueOf(Integer.valueOf(negativeValue.getText().toString()) + 1));
                    }
                    double positInt = Double.valueOf(positiveValue.getText().toString());
                    double negatInt = Double.valueOf(negativeValue.getText().toString());
                    if (positInt == 0) {
                        precentage.setText("" + "0");
                    } else if (negatInt == 0d) {
                        precentage.setText("" + "100");
                    } else if (positInt > negatInt) {
                        precentage.setText("" + ((int)(100 - negatInt/positInt * 100)));
                    } else if (positInt == negatInt) {
                        precentage.setText("" + "50");
                    } else {
                        precentage.setText("" + ((int)(positInt/negatInt * 100)));
                    }
                    countDownTimer.cancel();
                    goNext(countDownTimer, resultEdit, startBtn, firstText, finalCurrentOperation, firstValueImage, secondText);
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

    private void goNext(CountDownTimer countDownTimer, EditText resultEdit, Button startBtn, TextView firstText, int finalCurrentOperation, ImageView firstValueImage, TextView secondText) {
        countDownTimer.cancel();
        resultEdit.setText("");
        resultEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(resultEdit, InputMethodManager.SHOW_IMPLICIT);
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
        int firstrandomValue = random.nextInt(randomValue);
        firstText.setText(String.valueOf(firstrandomValue));
        int secondRandomValue = random.nextInt(randomValue);
        if (finalCurrentOperation == Operation.OPERATION_SQR || finalCurrentOperation == Operation.OPERATION_SQRT) {
            if (finalCurrentOperation == Operation.OPERATION_SQRT) {
                firstrandomValue = ((int) Math.sqrt(firstrandomValue));
                firstrandomValue = firstrandomValue * firstrandomValue;
                firstValueImage.setVisibility(View.VISIBLE);
                firstText.setText(String.valueOf(firstrandomValue));
            } else {
                firstValueImage.setVisibility(View.GONE);
            }
            secondText.setText("");
        } else {
            if(finalCurrentOperation == Operation.OPERATION_MINUS && firstrandomValue < secondRandomValue) {
                secondRandomValue = firstrandomValue;
            }
            secondText.setText(String.valueOf(secondRandomValue));
        }
        countDownTimer.start();
    }
}
