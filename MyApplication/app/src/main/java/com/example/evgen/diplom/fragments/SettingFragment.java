package com.example.evgen.diplom.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.evgen.diplom.Level;
import com.example.evgen.diplom.Operation;
import com.example.evgen.diplom.R;
import com.example.evgen.diplom.SettingsManager;

public class SettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container,
                false);

        RadioButton normalLevel = (RadioButton) rootView.findViewById(R.id.normal);
        normalLevel.setChecked(true);
        normalLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentLevel(Level.LEVEL_NORMAL);
            }
        });
        RadioButton hightLevel = (RadioButton) rootView.findViewById(R.id.hight);
        hightLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentLevel(Level.LEVEL_HIGHT);
            }
        });
        RadioButton expertLevel = (RadioButton) rootView.findViewById(R.id.expert);
        expertLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentLevel(Level.LEVEL_EXPERT);
            }
        });
        CheckBox showTimer = (CheckBox) rootView.findViewById(R.id.show_timer);
        showTimer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SettingsManager.getInstance().setShowTimer(b);
            }
        });

        RadioButton operationPlus = (RadioButton) rootView.findViewById(R.id.plus);
        operationPlus.setChecked(true);
        operationPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentOperation(Operation.OPERATION_PLUS);
            }
        });

        RadioButton operationMinus = (RadioButton) rootView.findViewById(R.id.minus);
        operationMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentOperation(Operation.OPERATION_MINUS);
            }
        });

        RadioButton operationIncrease = (RadioButton) rootView.findViewById(R.id.increase);
        operationIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentOperation(Operation.OPERATION_INCREASE);
            }
        });

        RadioButton operationDiv = (RadioButton) rootView.findViewById(R.id.division);
        operationDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentOperation(Operation.OPERATION_DIVISION);
            }
        });

        RadioButton operationSqr = (RadioButton) rootView.findViewById(R.id.sqr);
        operationSqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentOperation(Operation.OPERATION_SQR);
            }
        });

        RadioButton operationSqrt = (RadioButton) rootView.findViewById(R.id.sqrt);
        operationSqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentOperation(Operation.OPERATION_SQRT);
            }
        });

        RadioButton operationRandom = (RadioButton) rootView.findViewById(R.id.random);
        operationRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentOperation(Operation.OPERATION_RANDOM);
            }
        });

        RadioButton speedX1 = (RadioButton) rootView.findViewById(R.id.x1);
        speedX1.setChecked(true);
        speedX1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentSpeed(10);
            }
        });

        RadioButton speedX2 = (RadioButton) rootView.findViewById(R.id.x2);
        speedX2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentSpeed(20);
            }
        });

        RadioButton speedX3 = (RadioButton) rootView.findViewById(R.id.x3);
        speedX3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsManager.getInstance().setCurrentSpeed(30);
            }
        });
        return rootView;
    }

}