package com.thehyundai.thepet.global.cmcode;

import lombok.Getter;

@Getter
public enum CmCode {

    PUPPY("41"),
    SENIOR("42"),
    GENERAL("43"),
    ADULT("44"),

    BIG("D-BG"),
    MIDDLE("D-MD"),
    SMALL("D-SM");

    private String codeValue;

    CmCode(String codeValue) {
        this.codeValue = codeValue;
    }

    public static CmCode convertToPetAgeCode(Integer age, String sizeCode) {
        if (sizeCode.equals(SMALL.codeValue)) {
            return age < 10 ? PUPPY : (age >= 10 ? SENIOR : ADULT);
        } else if (sizeCode.equals(MIDDLE.codeValue)) {
            return age < 12 ? PUPPY : (age >= 9 ? SENIOR : ADULT);
        } else if (sizeCode.equals(BIG.codeValue)) {
            return age < 15 ? PUPPY : (age >= 7 ? SENIOR : ADULT);
        }
        return ADULT;
    }

}