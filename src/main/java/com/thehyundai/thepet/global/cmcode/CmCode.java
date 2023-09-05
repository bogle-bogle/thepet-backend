package com.thehyundai.thepet.global.cmcode;

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

    public static String convertToPetAgeCode(Integer age, String size) {
        if ("SMALL".equals(size)) {
            return age < 10 ? PUPPY.code : (age >= 10 ? SENIOR.code : ADULT.code);
        } else if ("MIDDLE".equals(size)) {
            return age < 12 ? PUPPY.code : (age >= 9 ? SENIOR.code : ADULT.code);
        } else if ("BIG".equals(size)) {
            return age < 15 ? PUPPY.code : (age >= 7 ? SENIOR.code : ADULT.code);
        }
        return ADULT.code;
    }

}