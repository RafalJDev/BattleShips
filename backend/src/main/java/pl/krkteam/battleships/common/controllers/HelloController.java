package pl.krkteam.battleships.common.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class HelloController {

  @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
  public String sayHello() {
    System.out.println("what");

    return "{\"message\": \"Hello, Tylde!\"}";
  }

  @PostMapping(value = "hello/postt")
  public String post(@RequestBody String post) {
    System.out.println(post);
    return "{\"message\": \"Cry Argentina!\"}";
  }
}
