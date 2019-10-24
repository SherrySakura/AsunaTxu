package com.asuna.textutils.entity;

public class Level {

    private int level;
    private int exp;

    public Level() {
    }

    public Level(int level, int exp) {
        this.level = level;
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Level{" +
                "level=" + level +
                ", exp=" + exp +
                '}';
    }
}
