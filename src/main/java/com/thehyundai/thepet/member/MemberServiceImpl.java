package com.thehyundai.thepet.member;

import com.thehyundai.thepet.util.AuthTokensGenerator;
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
    public MemberVO memberLogin(MemberVO member) {
        MemberVO res = memberMapper.login(member.getSocialId());

        if (res == null) {
            memberMapper.register(member);
            res = member;
        }


//        MemberVO requestMemeber = MemberVO.builder()
//                .id(res.getId())
//                .socialId(res.getSocialId())
//                .name(res.getName())
//                .email(res.getEmail())
//                .imgUrl(res.getImgUrl())
//                .address(res.getAddress())
//                .nickname(res.getNickname())
//                .clubHeendyYn("N")
//                .jwt(authTokensGenerator.generate(res.getSocialId()))
//                .build();

        return MemberVO.builder()
                .id(res.getId())
                .socialId(res.getSocialId())
                .name(res.getName())
                .email(res.getEmail())
                .imgUrl(res.getImgUrl())
                .address(res.getAddress())
                .nickname(res.getNickname())
                .clubHeendyYn("N")
                .jwt(authTokensGenerator.generate(res.getSocialId()))
                .build();
    }
}
