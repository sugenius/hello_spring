package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") // http://localhost:8080/hello 연결
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello"; //resources/templates 내 ViewName : hello 찾아 처리
    }
}
