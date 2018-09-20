package pl.krkteam.battleships.common.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
class HelloController {

    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    String sayHello() {
        return "{\"message\": \"Hello, Tylde!\"}";
    }

    @PostMapping(value = "hello/postt")
    String post(@RequestBody String post) {
        return "{\"message\": \"Cry Argentina!\"}";
    }
}
