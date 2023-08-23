package com.thehyundai.thepet.mypet.club;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {
    private final ClubMapper clubMapper;
}
