package com.ciber.springBoot.HolaSpringBoot.exception.enums;

/**
 * @author ciber
 *
 */
public enum LevelType {

    OK("OK"), ERROR("ERROR");

    private String importance;

    private LevelType(String importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return importance;
    }

}
