package com.thehyundai.thepet.subscription;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subMapper;
}
