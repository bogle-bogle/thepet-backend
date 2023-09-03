package com.thehyundai.thepet.domain.member;

import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final AuthTokensGenerator authTokensGenerator;

    @Override
    public MemberVO loginOrRegister(MemberVO requestVO) {
        MemberVO member = memberMapper.findMemberBySocialId(requestVO.getSocialId())
                                      .orElseGet(() -> register(requestVO));
        member.setJwt(authTokensGenerator.generate(member.getId()));
        return member;
    }

    private MemberVO register(MemberVO member) {
        if (memberMapper.register(member) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return member;
    }
}



