package com.example.springboottest;

import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MyController {

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "This service is running healthy";
    }

    @PostMapping("/getName")
    public Map<String, String> getName(@RequestBody Username user) {
        Map<String, String> name = new HashMap<>();

        name.put("first", user.first);
        name.put("last", user.last);

        return name;
    }

}
