package com.spring.dev2chuc.nutritious_food.model;

public enum ExerciseIntensity {
    LIGHT_ACTIVITY(1.2),
    LIGHT_MANUAL_LIMBS(1.4),
    MODERATE_ACTIVITY(1.6),
    ACTIVE_MUCH(1.8),
    WORKS_A_LOT(2.0);

    private double value;

    ExerciseIntensity(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

//    public ExerciseIntensity getName(string value) {
//        switch (value) {
//            case LIGHT_ACTIVITY.getValue():
//                return LIGHT_ACTIVITY;
//            case LIGHT_MANUAL_LIMBS.getValue():
//                return LIGHT_MANUAL_LIMBS;
//            case MODERATE_ACTIVITY.getValue():
//                return MODERATE_ACTIVITY;
//            case ACTIVE_MUCH.getValue():
//                return ACTIVE_MUCH;
//            case WORKS_A_LOT.getValue():
//                return WORKS_A_LOT;
//            default:
//                return LIGHT_MANUAL_LIMBS;
//        }
//        return LIGHT_MANUAL_LIMBS;
//    }
}
