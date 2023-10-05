package com.thehyundai.thepet.domain.backoffice.member;

import com.thehyundai.thepet.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/backoffice/member")
public class BackOfficeMemberController {

    private final BackOfficeMemberService backOfficeMemberService;

    @GetMapping("/entire")
    public ResponseEntity<BackOfficeResMemberVO> getMembers(@RequestParam Integer page) {
        return new ResponseEntity<>(backOfficeMemberService.getAllMember(page), HttpStatus.OK);
    }

    @GetMapping("/heendy")
    public ResponseEntity<BackOfficeResMemberVO> getHeendyMembers(@RequestParam Integer page) {
        return new ResponseEntity<>(backOfficeMemberService.getAllHeendyMember(page), HttpStatus.OK);
    }

    @GetMapping("/subscribe")
    public ResponseEntity<BackOfficeResMemberVO> getSubscribeMembers(@RequestParam Integer page) {
        return new ResponseEntity<>(backOfficeMemberService.getAllSubscribeMember(page), HttpStatus.OK);
    }

    @GetMapping("/delivery")
    public ResponseEntity<BackOfficeResMemberVO> getDeliveryMembers(@RequestParam Integer page) {
        return new ResponseEntity<>(backOfficeMemberService.getAllDeliveryMember(page), HttpStatus.OK);
    }
}
