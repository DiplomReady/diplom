package com.example.evgen.diplom;

public class SettingsManager {

    private static SettingsManager instance;

    private @Level int currentLevel = Level.LEVEL_NORMAL;

    private @Operation int currentOperation = Operation.OPERATION_PLUS;

    private int currentSpeed = 10;
    private boolean isShowTimer = true;

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public @Level int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(@Level int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isShowTimer() {
        return isShowTimer;
    }

    public int getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(int currentOperation) {
        this.currentOperation = currentOperation;
    }

    public int getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(int currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setShowTimer(boolean showTimer) {
        isShowTimer = showTimer;
    }

}
