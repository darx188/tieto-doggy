package tietodoggy.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tietodoggy.demo.entity.Fact;
import tietodoggy.demo.repository.FactsRepository;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RestController
//@RequestMapping("/dogs")
public class FactsController {
    @Autowired
    FactsRepository factsRepository;


    @GetMapping("/facts")
    public List<Fact> getAllFacts(){
        return factsRepository.findAll();
    }

    @DeleteMapping("/facts/{id}")
    public ResponseEntity<Fact> deleteFact(@PathVariable long id) {
        Fact fact = factsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entity not found"
                ));
        factsRepository.delete(fact);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/facts/{id}")
    public Fact getFact(@PathVariable long id) {
        return factsRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entity not found"
                ));
    }
    @PostMapping("/facts")
    public ResponseEntity<Fact> createFact(@RequestBody Fact fact) {//void
        Fact createdFact = factsRepository.save(fact);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdFact.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/facts/{id}")
    public ResponseEntity<Fact> updateFact(@PathVariable long id, @RequestBody Fact fact) {
        Fact factUpdated = factsRepository.save(fact);
        return new ResponseEntity<Fact>(factUpdated, HttpStatus.OK);
    }
    //siuntimas per postman kaip application/json
    @PostMapping(value ="/uploadFacts", consumes = "application/json", produces = "application/json")
    public Fact newFacts(@RequestBody Fact fact){
        fact.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        fact.setDeleted(false);
        return factsRepository.save(fact);

    }


}
