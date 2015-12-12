package com.spend.wisely.db;

/**
 * Created by Sunil on 12/8/2015.
 */
public class AppColor {
    int color;
    String name;

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    int color_row;
    boolean tag;

    public int getColor_row() {
        return color_row;
    }

    public void setColor_row(int color_row) {
        this.color_row = color_row;
    }


    public AppColor() {

    }

    public AppColor(int row, int purple, String purple1, boolean tag) {
        this.color_row = row;
        this.color = purple;
        this.name = purple1;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
