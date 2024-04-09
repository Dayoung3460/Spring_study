package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        // 문자열 그대로 노출됨
        // 템플릿 엔진을 통하지 않았기 때문에
        return "hello11 " + name;
    }

    @GetMapping("hello-api")
    // @ResponseBody가 없으면 viewResolver(템플릿)으로 던짐
    // 있으면 return값(hello(name:spring))을 그대로 던짐
    // 근데 return 값이 객체야
    // viewResolver 대신에 HttpMessageConverter가 작동함
    // 객체면 HttpMessageConverter 중에서도 MappingJackson2HttpMessageConverter(라이브러리) 작동함
    // json스타일로 바꿔서 부라우저로 반환해줌
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // json으로 리턴됨
        return hello;
    }

    static class Hello {
        // java bean 표준 방식(property 접근 방식):
        // 변수는 private으로 닫아놓고 게터, 세터로 다른 곳에서 접근
        private String name;

        public String getName() {
            return name;

        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
