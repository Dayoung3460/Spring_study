package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 클라이언트에서 요청이 왔을 때 컨트롤러를 먼저 뒤진 후에 없으면 static 파일을 불러옴
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
