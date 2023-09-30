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
    public List<BackOfficeMemberVO> getAllMember() {

        List<BackOfficeMemberVO> member = backOfficeMemberMapper.selectAllMember();

        for (BackOfficeMemberVO m : member) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 1) {
                char firstChar = name.charAt(0);
                String maskedName = firstChar + "*".repeat(name.length() - 1);
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

        return member;
    }

    @Override
    public List<BackOfficeMemberVO> getAllHeendyMember() {
        List<BackOfficeMemberVO> member = backOfficeMemberMapper.selectHeendyMember();

        for (BackOfficeMemberVO m : member) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 1) {
                char firstChar = name.charAt(0);
                String maskedName = firstChar + "*".repeat(name.length() - 1);
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
        return member;
    }

    @Override
    public List<BackOfficeMemberVO> getAllSubscribeMember() {
        List<BackOfficeMemberVO> member = backOfficeMemberMapper.selectSubscribeMember();

        for (BackOfficeMemberVO m : member) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 1) {
                char firstChar = name.charAt(0);
                String maskedName = firstChar + "*".repeat(name.length() - 1);
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
        return member;
    }

    @Override
    public List<BackOfficeMemberVO> getAllDeliveryMember() {
        List<BackOfficeMemberVO> member = backOfficeMemberMapper.selectDeliveryMember();

        for (BackOfficeMemberVO m : member) {
            // 이름을 마스킹 (첫 글자와 나머지를 *로 대체)
            String name = m.getName();
            if (name.length() > 1) {
                char firstChar = name.charAt(0);
                String maskedName = firstChar + "*".repeat(name.length() - 1);
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
        return member;
    }
}
