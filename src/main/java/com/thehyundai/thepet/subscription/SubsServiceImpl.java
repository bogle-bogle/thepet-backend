package com.thehyundai.thepet.subscription;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subMapper;
}
