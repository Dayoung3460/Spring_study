package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// controller라는 어노테이션이 있으면 MemberController를 객체로 만들어(MemberController 생성자 호출)
// 스프링 컨테이너가 관리를 함
@Controller
public class MemberController {
    private final MemberService memberService;

    // Autowired: 스프링 컨테이너에 있던 memberService 와
    // 생성자 호출할 때 필요한 memberService연결(wired)해줌
    // 컨트롤러와 서배스 연결
    // 생성자 매개변수 타입인 MemberService가 @Service로 등록되어야 Autowired 가능
    // 생성자 주입(생성 시점에 this.memberService에 등록되고 this.memberService를 수정못하도록함.)

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }
}