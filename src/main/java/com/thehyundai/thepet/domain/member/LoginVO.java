package com.thehyundai.thepet.domain.member;

import com.thehyundai.thepet.domain.mypet.pet.PetSimpleVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginVO {
    private MemberVO member;
    private List<PetSimpleVO> pets;
}
