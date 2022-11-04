package miniproject.board.controller;

import miniproject.board.domain.Member;
import miniproject.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private MemberService service ;

    // @Autowired ==> 1) 필드 2) 생성자 (O) ==> 가장 좋은 방법 3) 셋터 메소드

    @Autowired
    public MemberController(MemberService service){
        this.service = service;
    }
/*
    @GetMapping("/")
    public String home() {
        return "home";
    }*/

    @GetMapping("/members/new")
    public String join() {
        return "membersnew"; // 이름 입력받는 화면을 뿌려준다.
    }

    @PostMapping("/members/new")
    public String joinprocess(MemberForm form) { // 입력받은 이름과 회원정보를 db에 추가해준다.
        Member member = new Member();
        member.setName(form.getName());
        member.setPhone(form.getPhone());
        member.setEmail(form.getEmail());
        member.setAddress(form.getAddress());
        service.memberJoin(member);
        return "redirect:/";
    }

    @GetMapping("/memberlist")
    public String memberList(Model model){ // db에 있는 회원정보를 화면에 뿌려준다.
        List<Member> members = service.findAllMembers();
        model.addAttribute("members", members);
        return "memberlist";
    }

    /**
     * db에 있는 회원정보 하나를 화면에 뿌려주고 수정하거나 삭제할 수 있게 한다.
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/member/{id}")
    public String member(Model model, @PathVariable("id") Long id){
        Member member = service.findMember(id).get();
        model.addAttribute("member", member);
        return "member";
    }

    @PostMapping("/member/{id}")
    public String memberUpdate(MemberForm form, @PathVariable("id") Long id){
        Member member = service.findMember(id).get();
        member.setPhone(form.getPhone());
        member.setEmail(form.getEmail());
        member.setAddress(form.getAddress());
        System.out.println(member);
        Long aLong = service.memberJoin(member);
        return "redirect:/";
    }
}
