package com.thehyundai.thepet.heendycar;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class HeendyCarServiceImpl implements HeendyCarService {
    private final HeendyCarMapper heendyCarMapper;
}
