package com.example.evgen.diplom.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.evgen.diplom.R;

public class SorobanView extends LinearLayout implements CheckBox.OnCheckedChangeListener{

    private CheckBox choice1;
    private CheckBox choice2;
    private CheckBox choice3;
    private CheckBox choice4;
    private CheckBox choice5;
    private CheckBox choice6;
    private CheckBox choice7;
    private TextView result;
    int first = 0;
    int second = 0;

    public SorobanView(Context context) {
        super(context);
        init();
    }

    public SorobanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SorobanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.choice_sorban, this);


        choice1 = (CheckBox) findViewById(R.id.choice_1);
        choice1.setOnCheckedChangeListener(this);

        choice2 = (CheckBox) findViewById(R.id.choice_2);
        choice2.setOnCheckedChangeListener(this);

        choice3 = (CheckBox) findViewById(R.id.choice_3);
        choice3.setOnCheckedChangeListener(this);

        choice4 = (CheckBox) findViewById(R.id.choice_4);
        choice4.setOnCheckedChangeListener(this);

        choice5 = (CheckBox) findViewById(R.id.choice_5);
        choice5.setOnCheckedChangeListener(this);

        choice6 = (CheckBox) findViewById(R.id.choice_6);
        choice6.setOnCheckedChangeListener(this);

        choice7 = (CheckBox) findViewById(R.id.choice_7);
        choice7.setOnCheckedChangeListener(this);

        result = (TextView) findViewById(R.id.result);

        reset();
    }

    public void reset() {
        choice1.setChecked(true);
        choice1.setEnabled(true);
        choice2.setChecked(false);
        choice2.setEnabled(false);
        choice3.setChecked(false);
        choice3.setEnabled(false);
        choice4.setChecked(true);
        choice4.setEnabled(true);
        choice5.setChecked(true);
        choice5.setEnabled(true);
        choice6.setChecked(true);
        choice6.setEnabled(true);
        choice7.setChecked(true);
        choice7.setEnabled(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        compoundButton.setEnabled(false);
        int id = compoundButton.getId();
        if (!b) {
            switch (id) {
                case R.id.choice_1:
                    choice2.setChecked(true);
                    choice2.setEnabled(true);
                    first = 5;
                    break;
                case R.id.choice_2:
                    choice1.setChecked(true);
                    choice1.setEnabled(true);
                    first = 0;
                    break;
                case R.id.choice_3:
                    second = 0;
                    choice4.setChecked(true);
                    choice5.setChecked(true);
                    choice6.setChecked(true);
                    choice7.setChecked(true);

                    choice4.setEnabled(true);
                    choice5.setEnabled(true);
                    choice6.setEnabled(true);
                    choice7.setEnabled(true);
                    break;
                case R.id.choice_4:
                    second = 1;
                    if(choice3.isChecked()) {
                        choice5.setChecked(true);
                        choice6.setChecked(true);
                        choice7.setChecked(true);

                        choice5.setEnabled(true);
                        choice6.setEnabled(true);
                        choice7.setEnabled(true);
                    } else {
                        choice3.setChecked(true);
                        choice3.setEnabled(true);
                    }
                    break;

                case R.id.choice_5:
                    second = 2;
                    if(choice3.isChecked() && choice4.isChecked()) {
                        choice6.setChecked(true);
                        choice7.setChecked(true);

                        choice6.setEnabled(true);
                        choice7.setEnabled(true);
                    } else {
                        choice3.setChecked(true);
                        choice3.setEnabled(true);
                        choice4.setChecked(true);
                        choice4.setEnabled(true);
                    }
                    break;

                case R.id.choice_6:
                    second = 3;
                    if(choice3.isChecked() && choice4.isChecked() && choice5.isChecked()) {
                        choice7.setChecked(true);
                        choice7.setEnabled(true);
                    } else {
                        choice3.setChecked(true);
                        choice3.setEnabled(true);
                        choice4.setChecked(true);
                        choice4.setEnabled(true);
                        choice5.setChecked(true);
                        choice5.setEnabled(true);
                    }
                    break;

                case R.id.choice_7:
                    second = 4;
                    choice3.setChecked(true);
                    choice3.setEnabled(true);
                    choice4.setChecked(true);
                    choice4.setEnabled(true);
                    choice5.setChecked(true);
                    choice5.setEnabled(true);
                    choice6.setChecked(true);
                    choice6.setEnabled(true);
                    break;
            }
            result.setText("" + (first + second));
        }
    }

    public int getResult(){
        return Integer.valueOf(result.getText().toString());
    }
}
