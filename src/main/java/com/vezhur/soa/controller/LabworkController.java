package com.vezhur.soa.controller;

import com.vezhur.soa.DTO.LabworkDetails;
import com.vezhur.soa.service.LabworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoints.base-url}/labworks")
public class LabworkController {

    @Autowired
    private LabworkService labworkService;

    @GetMapping
    public ResponseEntity<String> getLabworks() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity<LabworkDetails> createLabwork(@RequestBody LabworkDetails labwork) {
        return ResponseEntity.ok(labworkService.createLabwork(labwork));
    }

    @GetMapping("/{labwork-id}")
    public ResponseEntity<String> getLabworkById(@PathVariable("labwork-id") Long id) {
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/{labwork-id}")
    public ResponseEntity<String> updateLabwork(@PathVariable("labwork-id") Long id) {
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{labwork-id}")
    public ResponseEntity<String> deleteLabwork(@PathVariable("labwork-id") Long id) {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/minimal-point/sum")
    public ResponseEntity<String> getMinimalPointSum() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/difficulty/min")
    public ResponseEntity<String> getEasiestLabwork() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/author/count")
    public ResponseEntity<String> getLabworksCountByAuthor() {
        return ResponseEntity.ok("OK");
    }
}
