package com.thehyundai.thepet.domain.member;

import com.thehyundai.thepet.domain.mypet.pet.PetService;
import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/member")
public class MemberController {
    private final MemberService memberService;
    private final PetService petService;

    @PostMapping("/login")
    public ResponseEntity<?> loginOrRegister(@RequestBody MemberVO requestVO) {
        MemberVO member = memberService.loginOrRegister(requestVO);
        List<PetVO> pets = petService.findPetsWithAllergies(member.getId());
        LoginVO result = new LoginVO(member, pets);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/entire")
    public ResponseEntity<List<MemberVO>> getMembers() {
        return new ResponseEntity<>(memberService.getAllMember(), HttpStatus.OK);
    }

    @GetMapping("/heendy")
    public ResponseEntity<List<MemberVO>> getHeendyMembers() {
        return new ResponseEntity<>(memberService.getAllHeendyMember(), HttpStatus.OK);
    }

    @GetMapping("/subscribe")
    public ResponseEntity<List<MemberVO>> getSubscribeMembers() {
        return new ResponseEntity<>(memberService.getAllMember(), HttpStatus.OK);
    }

}
