package com.homework.entity;

public class PokerCard {

    private String mask;
    private int value;
    private int icon;
    private String iconMask;
    private boolean isShow;

    public PokerCard(String mask, int value, int icon, String iconMask, boolean isShow) {
        this.mask = mask;
        this.value = value;
        this.icon = icon;
        this.iconMask = iconMask;
        this.isShow = isShow;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean isShow) {
        this.isShow = isShow;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIconMask() {
        return iconMask;
    }

    public void setIconMask(String iconMask) {
        this.iconMask = iconMask;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
