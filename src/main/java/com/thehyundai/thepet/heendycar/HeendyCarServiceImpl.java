package com.thehyundai.thepet.heendycar;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class HeendyCarServiceImpl implements HeendyCarService {
    private final HeendyCarMapper heendyCarMapper;
}
