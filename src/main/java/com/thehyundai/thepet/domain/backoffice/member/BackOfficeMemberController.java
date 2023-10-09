package com.thehyundai.thepet.domain.backoffice.member;

import com.thehyundai.thepet.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/backoffice/member")
public class BackOfficeMemberController {

    private final BackOfficeMemberService backOfficeMemberService;

    @PostMapping("/entire")
    public ResponseEntity<BackOfficeResMemberVO> getMembers(@RequestBody MemberRequestVO req) {
        return new ResponseEntity<>(backOfficeMemberService.getAllMember(req), HttpStatus.OK);
    }

    @GetMapping("/heendy")
    public ResponseEntity<BackOfficeResMemberVO> getHeendyMembers(@RequestBody MemberRequestVO req) {
        return new ResponseEntity<>(backOfficeMemberService.getAllHeendyMember(req), HttpStatus.OK);
    }

    @GetMapping("/subscribe")
    public ResponseEntity<BackOfficeResMemberVO> getSubscribeMembers(@RequestBody MemberRequestVO req) {
        return new ResponseEntity<>(backOfficeMemberService.getAllSubscribeMember(req), HttpStatus.OK);
    }

    @GetMapping("/delivery")
    public ResponseEntity<BackOfficeResMemberVO> getDeliveryMembers(@RequestBody MemberRequestVO req) {
        return new ResponseEntity<>(backOfficeMemberService.getAllDeliveryMember(req), HttpStatus.OK);
    }
}
