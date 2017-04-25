package com.example.evgen.diplom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
            case Operation.OPERATION_RANDOM:

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

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startBtn.setText("Следующий");
                Random random = new Random();
                firstText.setText(String.valueOf(random.nextInt(100)));
                secondText.setText(String.valueOf(random.nextInt(100)));
                new CountDownTimer(SettingsManager.getInstance().getCurrentSpeed() * 1000 + 100, 1000) {
                    @Override
                    public void onTick(long milliss) {
                        timerText.setText("" + (int)milliss/1000);
                    }

                    @Override
                    public void onFinish() {
                        negativeValue.setText(String.valueOf(Integer.valueOf(negativeValue.getText().toString()) + 1));
                    }
                }.start();
            }
        });

        return rootView;
    }
}
