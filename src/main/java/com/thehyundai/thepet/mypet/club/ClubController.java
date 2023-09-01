package com.thehyundai.thepet.mypet.club;

import com.thehyundai.thepet.mypet.pet.AllergyVO;
import com.thehyundai.thepet.mypet.pet.PetService;
import com.thehyundai.thepet.mypet.pet.PetVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;
    private final PetService petService;

    @PostMapping
    public ResponseEntity<Integer> club(@RequestBody PetVO petVO){
        return ResponseEntity.ok(petService.registerClub(petVO));
    }
}
