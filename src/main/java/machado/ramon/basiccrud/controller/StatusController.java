package machado.ramon.basiccrud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class StatusController {

  @GetMapping
  public ResponseEntity<Object> checkStatus() {
    return ResponseEntity.ok().body("Ok");
  }
}
