package lk.ijse.gdse68.springpossystembackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : sachini
 * @date : 2024-10-11
 **/
@RestController
@RequestMapping("api/v1/healthTest")
@RequiredArgsConstructor
public class HealthTestController {
    @GetMapping
    public String healthTest(){
        return "Server Controller run Successfully!!";
    }
}
