package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("hello") // http://localhost:8080/hello 연결
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello"; //resources/templates 내 ViewName : hello 찾아 처리
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name",name); //key, balue
        return "hello-template";
    }
}
