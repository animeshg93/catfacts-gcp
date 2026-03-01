package com.example.purrfacts.cat.controller;

import com.example.purrfacts.cat.model.Cartoon;
import com.example.purrfacts.cat.repository.CartoonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartoons")
public class CartoonController {

    private final CartoonRepository cartoonRepository;

    public CartoonController(CartoonRepository cartoonRepository) {
        this.cartoonRepository = cartoonRepository;
    }

    @PostMapping
    public ResponseEntity<String> publishMessage(@RequestBody Cartoon cartoon) {
        cartoonRepository.save(cartoon);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cartoon created successfully");
    }

    @GetMapping("/abbreviation/{abbr}")
    public ResponseEntity<Cartoon> getCartoonByAbbr(@PathVariable String abbr) {
        Cartoon cartoon = cartoonRepository.getByAbb(abbr);
        return ResponseEntity.status(HttpStatus.OK).body(cartoon);
    }

    @GetMapping
    public ResponseEntity<List<Cartoon>> getAllCartoons() {
        java.util.List<Cartoon> cartoons = cartoonRepository.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(cartoons);
    }
}
