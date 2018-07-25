package pl.krkteam.battleships.common.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sayHello() {
        logger.debug("Get on /hello has been invoked");
        return "{\"message\": \"Hello, Tylde!\"}";
    }

    @PostMapping(value = "hello/postt")
    public String post(@RequestBody String post) {
        logger.debug("Post on /hello/postt has been invoked. Post body: " + post);
        return "{\"message\": \"Cry Argentina!\"}";
    }
}
