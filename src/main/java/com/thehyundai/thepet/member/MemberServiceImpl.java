package com.thehyundai.thepet.member;

import com.thehyundai.thepet.exception.BusinessException;
import com.thehyundai.thepet.exception.ErrorCode;
import com.thehyundai.thepet.global.TableStatus;
import com.thehyundai.thepet.util.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        return MemberVO.builder()
                .id(member.getId())
                .socialId(member.getSocialId())
                .name(member.getName())
                .email(member.getEmail())
                .imgUrl(member.getImgUrl())
                .address(member.getAddress())
                .nickname(member.getNickname())
                .clubHeendyYn(TableStatus.N.getValue())
                .jwt(authTokensGenerator.generate(member.getId()))
                .build();
    }

    @Override
    public Optional<MemberVO> showMember(Integer id) {
        return memberMapper.findMemberById(id);
    }

    private MemberVO register(MemberVO member) {
        if (memberMapper.register(member) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return member;
    }
}



