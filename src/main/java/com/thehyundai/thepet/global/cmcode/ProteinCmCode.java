package com.thehyundai.thepet.global.cmcode;

import lombok.Getter;

@Getter
public enum ProteinCmCode {
    BEEF("P01", "소고기"),
    SHEEP("P02", "양고기"),
    PORK("P03", "돼지고기"),
    CHICKEN("P04", "닭고기"),
    DUCK("P05", "오리고기"),
    SALMON("P06", "연어"),
    FISH("P07", "생선"),
    VEGI("P08", "베지/곤충"),
    MIX("P09", "혼합"),
    KANGAROO("P10", "캥거루"),
    TURKEY("P11", "칠면조"),
    RABBIT("P12", "토끼고기"),
    ETC("P13", "기타");

    private final String codeValue;
    private final String name;

    ProteinCmCode(String codeValue, String name) {
        this.codeValue = codeValue;
        this.name = name;
    }
}
