package com.thehyundai.thepet.mypet.club;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
}
