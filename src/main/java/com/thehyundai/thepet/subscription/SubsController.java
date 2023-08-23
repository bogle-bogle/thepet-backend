package com.thehyundai.thepet.subscription;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sub")
public class SubsController {
    private final SubsService subsService;

    @PostMapping("/curation")
    public ResponseEntity<?> subscribeCuration(@RequestBody SubscriptionVO requestVO) {
        subsService.subscribeCuration(requestVO);
        return ResponseEntity.ok(requestVO);
    }

}
