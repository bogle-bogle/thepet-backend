package com.thehyundai.thepet.global.cmcode;

import lombok.Getter;

@Getter
public enum CmCode {

    PUPPY("41"),
    SENIOR("42"),
    ADULT("43"),

    BIG("D-BG"),
    MIDDLE("D-MD"),
    SMALL("D-SM");

    private String codeValue;

    CmCode(String codeValue) {
        this.codeValue = codeValue;
    }

    public static CmCode convertToPetAgeCode(Integer age, String size) {
        if ("SMALL".equals(size)) {
            return age < 10 ? PUPPY : (age >= 10 ? SENIOR : ADULT);
        } else if ("MIDDLE".equals(size)) {
            return age < 12 ? PUPPY : (age >= 9 ? SENIOR : ADULT);
        } else if ("BIG".equals(size)) {
            return age < 15 ? PUPPY : (age >= 7 ? SENIOR : ADULT);
        }
        return ADULT;
    }

}