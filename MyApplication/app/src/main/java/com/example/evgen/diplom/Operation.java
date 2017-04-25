package com.example.evgen.diplom;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.example.evgen.diplom.Operation.OPERATION_DIVISION;
import static com.example.evgen.diplom.Operation.OPERATION_INCREASE;
import static com.example.evgen.diplom.Operation.OPERATION_MINUS;
import static com.example.evgen.diplom.Operation.OPERATION_PLUS;
import static com.example.evgen.diplom.Operation.OPERATION_RANDOM;
import static com.example.evgen.diplom.Operation.OPERATION_SQR;
import static com.example.evgen.diplom.Operation.OPERATION_SQRT;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({OPERATION_PLUS, OPERATION_MINUS, OPERATION_INCREASE, OPERATION_DIVISION, OPERATION_SQR, OPERATION_SQRT, OPERATION_RANDOM})
public @interface Operation {

    public static final int OPERATION_PLUS = 0;
    public static final int OPERATION_MINUS = 1;
    public static final int OPERATION_INCREASE = 2;
    public static final int OPERATION_DIVISION = 3;
    public static final int OPERATION_SQR = 4;
    public static final int OPERATION_SQRT = 5;
    public static final int OPERATION_RANDOM = 6;

}
