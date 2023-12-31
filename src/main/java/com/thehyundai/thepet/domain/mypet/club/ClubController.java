package com.thehyundai.thepet.domain.mypet.club;

import com.thehyundai.thepet.domain.mypet.pet.PetService;
import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.thehyundai.thepet.global.util.Constant.HEADER_TOKEN_PARAM;

@Log4j2
@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubController {
    private final PetService petService;

    @PostMapping
    public ResponseEntity<?> club(@RequestHeader(HEADER_TOKEN_PARAM) String token,
                                  @ModelAttribute ClubRequestVO requestVO) {
        PetVO result = petService.registerClub(token, requestVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}