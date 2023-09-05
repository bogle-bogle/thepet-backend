package com.thehyundai.thepet.domain.mypet.club;

import com.thehyundai.thepet.domain.mypet.pet.PetService;
import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final PetService petService;

    @PostMapping
    public ResponseEntity<Integer> club(@RequestHeader("Authorization") String token, @RequestBody PetVO petVO){
        return ResponseEntity.ok(petService.registerClub(token,petVO));
    }
}
