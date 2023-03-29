package com.furkank.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/text")
public class TextMergeController {


    @Autowired
    private TextService textService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Text> findAllText() {
        return textService.findAllText();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public String mergeText(String firstInput, String secondInput) {
        return textService.mergeAndSaveText(firstInput, secondInput);
    }

}
