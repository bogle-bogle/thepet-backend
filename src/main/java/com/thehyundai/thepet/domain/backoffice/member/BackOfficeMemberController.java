package com.thehyundai.thepet.domain.backoffice.member;

import com.thehyundai.thepet.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/backoffice/member")
public class BackOfficeMemberController {

    private final BackOfficeMemberService backOfficeMemberService;

    @GetMapping("/entire")
    public ResponseEntity<List<BackOfficeMemberVO>> getMembers() {

        return new ResponseEntity<>(backOfficeMemberService.getAllMember(), HttpStatus.OK);
    }

    @GetMapping("/heendy")
    public ResponseEntity<List<BackOfficeMemberVO>> getHeendyMembers() {
        return new ResponseEntity<>(backOfficeMemberService.getAllHeendyMember(), HttpStatus.OK);
    }

    @GetMapping("/subscribe")
    public ResponseEntity<List<BackOfficeMemberVO>> getSubscribeMembers() {
        return new ResponseEntity<>(backOfficeMemberService.getAllSubscribeMember(), HttpStatus.OK);
    }

    @GetMapping("/delivery")
    public ResponseEntity<List<BackOfficeMemberVO>> getDeliveryMembers() {
        return new ResponseEntity<>(backOfficeMemberService.getAllDeliveryMember(), HttpStatus.OK);
    }
}
