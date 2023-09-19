package com.thehyundai.thepet.domain.member;

import com.thehyundai.thepet.domain.mypet.pet.PetMapper;
import com.thehyundai.thepet.domain.subscription.SubsMapper;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@TimeTraceService
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final PetMapper petMapper;
    private final SubsMapper subsMapper;
    private final AuthTokensGenerator authTokensGenerator;

    @Override
    @Transactional
    public MemberVO loginOrRegister(MemberVO requestVO) {
        MemberVO member = memberMapper.findMemberBySocialId(requestVO.getSocialId())
                                      .orElseGet(() -> register(requestVO));
        member.setJwt(authTokensGenerator.generate(member.getId()));
        return member;
    }

    @Override
    public Optional<MemberVO> showMember(String id) {
        return memberMapper.findMemberById(id);
    }

    private MemberVO register(MemberVO member) {
        if (memberMapper.register(member) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return member;
    }

    @Override
    public MemberVO updateMemberInfo(MemberVO memberVO) {
        if (memberMapper.updateMemberInfo(memberVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return memberVO;
    }

    @Override
    public MypageVO getMypageInfo(String token) {
        String memberId = authTokensGenerator.extractMemberId(token);
        Integer petCnt = petMapper.findPetCountByMemberId(memberId);
        Integer subCnt = subsMapper.findSubsCntByMemberId(memberId);

        return new MypageVO(petCnt, subCnt);
    }
}



