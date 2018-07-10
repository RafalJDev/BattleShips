package pl.krkteam.controllers;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.krkteam.model.ShipFromFronted;
import pl.krkteam.model.Ships;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ShipController {

  @PostMapping(value = "/post/ships")
  public String communicateWithAngularByPostingShip(@RequestBody String post) {
    System.out.println(post);

    Gson gson = new Gson();

    ShipFromFronted shipFromFronted = gson.fromJson(post, ShipFromFronted.class);

    System.out.println(shipFromFronted);

    return "{\"message\": \"Cry Germoney!\"}";
  }

  @PostMapping(value = "/ships")
  public String shipsFromAngular(@RequestBody String post) {

    System.out.println(post);

    Gson gson = new Gson();

    Ships shipFromFronted = gson.fromJson(post, Ships.class);

    System.out.println(shipFromFronted);

    boolean result = true;

    String send = "{\"result\":" + "\"" + result + "\"" + " }";
    return send;
  }
}
