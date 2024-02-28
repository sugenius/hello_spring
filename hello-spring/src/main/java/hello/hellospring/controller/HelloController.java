package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody //http 에서 헤더부와 바디부 중 바디부에 데이터를 직접 넣어주겠다라는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
