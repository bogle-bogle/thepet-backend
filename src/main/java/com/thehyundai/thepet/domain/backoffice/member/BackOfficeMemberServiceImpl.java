package com.thehyundai.thepet.domain.backoffice.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BackOfficeMemberServiceImpl implements BackOfficeMemberService{

    private final BackOfficeMemberMapper backOfficeMemberMapper;

    @Override
    public List<BackOfficeMemberVO> getAllMember() {
        return backOfficeMemberMapper.selectAllMember();
    }

    @Override
    public List<BackOfficeMemberVO> getAllHeendyMember() {
        return backOfficeMemberMapper.selectHeendyMember();
    }

    @Override
    public List<BackOfficeMemberVO> getAllSubscribeMember() { return backOfficeMemberMapper.selectSubscribeMember(); }

    @Override
    public List<BackOfficeMemberVO> getAllDeliveryMember() { return backOfficeMemberMapper.selectDeliveryMember(); }
}
