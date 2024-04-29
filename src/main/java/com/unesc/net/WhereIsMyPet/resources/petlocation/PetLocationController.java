package com.unesc.net.WhereIsMyPet.resources.petlocation;

import com.unesc.net.WhereIsMyPet.entity.petlocation.SavePetLocationDTO;
import com.unesc.net.WhereIsMyPet.service.petlocation.PetLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "petsLocation")
public class PetLocationController {

    @Autowired
    private PetLocationService petLocationService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SavePetLocationDTO savePetLocationDTO) {
        this.petLocationService.saveLocation(savePetLocationDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getLocations() {
        return ResponseEntity.ok(this.petLocationService.getLocations());
    }

}
