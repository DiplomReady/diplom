package com.example.evgen.diplom;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.example.evgen.diplom.Level.LEVEL_EXPERT;
import static com.example.evgen.diplom.Level.LEVEL_HIGHT;
import static com.example.evgen.diplom.Level.LEVEL_NORMAL;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({LEVEL_NORMAL, LEVEL_HIGHT, LEVEL_EXPERT})
public @interface Level {

    public static final int LEVEL_NORMAL = 0;
    public static final int LEVEL_HIGHT = 1;
    public static final int LEVEL_EXPERT = 2;

}
