package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.global.cmcode.CmCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @PutMapping("/feed/{id}")
    public ResponseEntity<Integer> updateFeed(@PathVariable int id, @RequestBody PetVO petVO){
        petVO.setId(id);
        return ResponseEntity.ok(petService.updateFeed(petVO));
    }

    @GetMapping
    public ResponseEntity<List<PetVO>> myPet(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(petService.myPet(token));
    }

    @GetMapping("/code")
    public ResponseEntity<List<CmCodeVO>> getAllCode(){
        return ResponseEntity.ok(petService.getAllCode());
    }
}
