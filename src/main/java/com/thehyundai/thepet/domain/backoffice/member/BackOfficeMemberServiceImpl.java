package com.thehyundai.thepet.domain.backoffice.member;

import com.thehyundai.thepet.domain.heendycar.HcReservationVO;
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
    public BackOfficeResMemberVO getAllMember(Integer page) {

        BackOfficeResMemberVO res = new BackOfficeResMemberVO();

        res.setMembers(backOfficeMemberMapper.selectAllMember((page - 1) * 20 + 1));
        res.setCount(backOfficeMemberMapper.selectAllMemberCount());


        for (BackOfficeMemberVO m : res.getMembers()) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 2) {
                char firstChar = name.charAt(0);
                char lastChar = name.charAt(name.length() - 1);
                String middleStars = "*".repeat(name.length() - 2);
                String maskedName = firstChar + middleStars + lastChar;
                m.setName(maskedName);
            }
            // 전화번호를 마스킹 (가운데 4자리를 *로 대체, 형식 변경)
            String phoneNumber = m.getPhoneNumber();
            if (phoneNumber == null) {
                continue;
            }
            if (phoneNumber.length() >= 8) {
                String maskedPhoneNumber = phoneNumber.substring(0, 3) + "-****-" + phoneNumber.substring(phoneNumber.length() - 4);
                m.setPhoneNumber(maskedPhoneNumber);
            }

            String email = m.getEmail();
            if (email == null || email.equals("null")) {
                continue;
            };

            String[] emailSplit = email.split("@");
            String maskedEmail = emailSplit[0].substring(0, 2) + "*".repeat(emailSplit[0].length()-2) + "@" + emailSplit[1];
            m.setEmail(maskedEmail);
        }

        return res;
    }

    @Override
    public BackOfficeResMemberVO getAllHeendyMember(Integer page) {
        BackOfficeResMemberVO res = new BackOfficeResMemberVO();

        res.setMembers(backOfficeMemberMapper.selectHeendyMember((page - 1) * 20 + 1));
        res.setCount(backOfficeMemberMapper.selectHeendyMemberCount());


        for (BackOfficeMemberVO m : res.getMembers()) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 2) {
                char firstChar = name.charAt(0);
                char lastChar = name.charAt(name.length() - 1);
                String middleStars = "*".repeat(name.length() - 2);
                String maskedName = firstChar + middleStars + lastChar;
                m.setName(maskedName);
            }

            // 전화번호를 마스킹 (가운데 4자리를 *로 대체, 형식 변경)
            String phoneNumber = m.getPhoneNumber();
            if (phoneNumber == null) {
                continue;
            }
            if (phoneNumber.length() >= 8) {
                String maskedPhoneNumber = phoneNumber.substring(0, 3) + "-****-" + phoneNumber.substring(phoneNumber.length() - 4);
                m.setPhoneNumber(maskedPhoneNumber);
            }
            String email = m.getEmail();
            String[] emailSplit = email.split("@");
            String maskedEmail = emailSplit[0].substring(0, 2) + "*".repeat(emailSplit[0].length()-2) + "@" + emailSplit[1];
            m.setEmail(maskedEmail);
        }
        return res;
    }

    @Override
    public BackOfficeResMemberVO getAllSubscribeMember(Integer page) {

        BackOfficeResMemberVO res = new BackOfficeResMemberVO();

        res.setMembers(backOfficeMemberMapper.selectSubscribeMember((page - 1) * 20 + 1));
        res.setCount(backOfficeMemberMapper.selectSubscribeMemberCount());

        for (BackOfficeMemberVO m : res.getMembers()) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 2) {
                char firstChar = name.charAt(0);
                char lastChar = name.charAt(name.length() - 1);
                String middleStars = "*".repeat(name.length() - 2);
                String maskedName = firstChar + middleStars + lastChar;
                m.setName(maskedName);
            }

            // 전화번호를 마스킹 (가운데 4자리를 *로 대체, 형식 변경)
            String phoneNumber = m.getPhoneNumber();
            if (phoneNumber == null) {
                continue;
            }
            if (phoneNumber.length() >= 8) {
                String maskedPhoneNumber = phoneNumber.substring(0, 3) + "-****-" + phoneNumber.substring(phoneNumber.length() - 4);
                m.setPhoneNumber(maskedPhoneNumber);
            }
            String email = m.getEmail();
            String[] emailSplit = email.split("@");
            String maskedEmail = emailSplit[0].substring(0, 2) + "*".repeat(emailSplit[0].length()-2) + "@" + emailSplit[1];
            m.setEmail(maskedEmail);
        }
        return res;
    }

    @Override
    public BackOfficeResMemberVO getAllDeliveryMember(Integer page) {

        BackOfficeResMemberVO res = new BackOfficeResMemberVO();

        res.setMembers(backOfficeMemberMapper.selectDeliveryMember((page - 1) * 20 + 1));
        res.setCount(backOfficeMemberMapper.selectDeliveryMemberCount());

        for (BackOfficeMemberVO m : res.getMembers()) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 2) {
                char firstChar = name.charAt(0);
                char lastChar = name.charAt(name.length() - 1);
                String middleStars = "*".repeat(name.length() - 2);
                String maskedName = firstChar + middleStars + lastChar;
                m.setName(maskedName);
            }

            // 전화번호를 마스킹 (가운데 4자리를 *로 대체, 형식 변경)
            String phoneNumber = m.getPhoneNumber();
            if (phoneNumber == null) {
                continue;
            }
            if (phoneNumber.length() >= 8) {
                String maskedPhoneNumber = phoneNumber.substring(0, 3) + "-****-" + phoneNumber.substring(phoneNumber.length() - 4);
                m.setPhoneNumber(maskedPhoneNumber);
            }

            String email = m.getEmail();
            String[] emailSplit = email.split("@");
            String maskedEmail = emailSplit[0].substring(0, 2) + "*".repeat(emailSplit[0].length()-2) + "@" + emailSplit[1];
            m.setEmail(maskedEmail);
        }
        return res;
    }
}
