package com.thehyundai.thepet.global;

public enum CmCode {

    PUPPY("41"),
    SENIOR("42"),
    ADULT("43"),

    BIG("D-BG"),
    MIDDLE("D-MD"),
    SMALL("D-SM");

    private String code;

    CmCode(String code) {
        this.code = code;
    }

    public String convertToAgeCode(Integer age, String size) {
        return null;
    }

}