package com.thehyundai.thepet.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/member")
public class MemberController {

    @PostMapping("/login")
    public ResponseEntity<MemberVO> login(@RequestBody MemberVO memberVo) {
        log.info(memberVo);
        return ResponseEntity.ok(memberVo);
    }
}
