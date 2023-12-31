package com.thehyundai.thepet.domain.member;

import com.thehyundai.thepet.domain.backoffice.member.BackOfficeMemberVO;
import com.thehyundai.thepet.domain.mypet.pet.PetService;
import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.thehyundai.thepet.global.util.Constant.HEADER_TOKEN_PARAM;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/member")
public class MemberController {
    private final MemberService memberService;
    private final PetService petService;

    @GetMapping("/card")
    public ResponseEntity<?> getCardInfo(@RequestParam String memberId) {
        Optional<MemberVO> result = memberService.showMember(memberId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginOrRegister(@RequestBody MemberVO requestVO) {
        MemberVO member = memberService.loginOrRegister(requestVO);
        List<PetVO> pets = petService.findPetsWithAllergies(member.getId());
        LoginVO result = new LoginVO(member, pets);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> getMypageInfo(@RequestHeader(HEADER_TOKEN_PARAM) String token) {
        MypageVO result = memberService.getMypageInfo(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/auth/login")
    public ResponseEntity<?> authToLogin(@RequestParam String code) {
        MemberVO member = memberService.authToLogin(code);
        List<PetVO> pets = petService.findPetsWithAllergies(member.getId());
        LoginVO result = new LoginVO(member, pets);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<LoginVO> getMember(@PathVariable String id) {
        MemberVO member = memberService.getMember(id);
        List<PetVO> pets = petService.findPetsWithAllergies(member.getId());
        LoginVO result = new LoginVO(member, pets);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
