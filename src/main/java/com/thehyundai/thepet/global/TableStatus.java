package com.thehyundai.thepet.global;

import lombok.Getter;

@Getter
public enum TableStatus{
    Y("Y"),
    N("N");

    private String value;

    TableStatus(String value) {
        this.value = value;
    }
}
