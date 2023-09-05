package com.thehyundai.thepet.global.cmcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CmCodeVO {
    private Integer id;
    private String codeValue;
    private String name;
    private String sectCode;
    private String parentCode;
}
