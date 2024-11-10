package com.github.Denis;

import org.apache.catalina.webresources.FileResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/") //этот контроллер отвечает за эту директорию
public class MainController {



    @GetMapping // обработчик get запроса
    public String showStatus() {
        return "Hello, zavod!";
    }

}
