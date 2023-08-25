package com.thehyundai.thepet.subscription;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sub")
public class SubsController {
    private final SubsService subsService;

    @GetMapping("/curation/monthly")
    public ResponseEntity<?> showCurationOfCurrMonth() {
        CurationVO result = subsService.showCurationOfCurrMonth();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curation/annual")
    public ResponseEntity<?> showCurationOfLastOneYear() {
        List<CurationVO> result = subsService.showCurationOfLastOneYear();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curation/{curationId}")
    public ResponseEntity<?> showCurationDetail(@PathVariable Integer curationId) {
        CurationVO curation = subsService.showCurationDetail(curationId);
        return new ResponseEntity<>(curation, HttpStatus.OK);
    }
}
