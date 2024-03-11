package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // @Controller 사용하며 MemberController 객체를 생성하여 컨테이너에 등록하여 사용됨 ==> 스프링 컨테이너에서 스프링 빈이 관리된다.
public class MemberController {

    //private final MemberService memberService = new MemberService();
    // new 키워드를 사용해도 되겠지, 하지만 스프링을 사용할때 스프링 컨테이너 에게 등록하고 관리되도록 사용하여야 한다.

    private final MemberService memberService;

    /* new 대신 생성자로 사용하고, @Autowired를 사용하도록 한다.
    우선 스프링이 뜰 때, MemberController가 @Controller 에 의해 컨테이너에 등록된 상태에서
    그때, 생성자를 호출하는데 생성자에 @Autowired가 선언되어 있다면
    스프링 컨테이너에 있는 MemberService를 가져다 연결시켜주게 된다.

    안될때(memberService 에서 빨간줄) , MemberService 를 가보면 순수 자바 클래스임을 알 수 있음
    그땐 MemberService에서 @Service 어노테이션을 사용해야
    @Service : 스프링이 스프링 컨테이너에 서비스를 등록해줌
    * */
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
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
