package com.example.springboot;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// https://ithelp.ithome.com.tw/articles/10321482
@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) {
        return "Hello, " + user.getUsername();
    }

    @GetMapping("/example/query")
    public String exampleQuery(@RequestParam("name") String name) {
        return name;
    }

    @PostMapping("/example/body")
    public User exampleBody(@RequestBody User user) {
        return user;
    }

    @PostMapping("/example/formData")
    public String exampleFormData(@RequestPart("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("./uploadedfiles/" + file.getOriginalFilename());
                Files.write(path, bytes);
                return "successful";
            } catch (Exception e) {
                return "failed";
            }
        }
        return "No uploaded";
    }

    @GetMapping("/example/url/{name}")
    public String url(@PathVariable("name") String name) {
        return name;
    }
}