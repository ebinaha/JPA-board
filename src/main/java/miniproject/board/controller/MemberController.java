package miniproject.board.controller;

import miniproject.board.domain.Member;
import miniproject.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String memberList(Model model,
                             @PageableDefault(page=0, size=5, sort="id", direction = Sort.Direction.DESC)
                             Pageable pageable){ // db에 있는 회원정보를 화면에 뿌려준다.
        Page<Member> members = service.findAllMembers(pageable);

        int nowPage = members.getPageable().getPageNumber() + 1;
        int startPage = 1;
        int endPage = members.getTotalPages();

        model.addAttribute("members", members);

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "memberlist";
    }

    /**
     * db에 있는 회원정보 하나를 화면에 뿌려주고 수정하거나 삭제할 수 있게 한다.
     * @param model
     * @param id
     * @return
     */
/*    @GetMapping("/member/{id}")
    public String member(Model model, @PathVariable("id") Long id){
        Member member = service.findMember(id).get();
        model.addAttribute("member", member);
        return "member";
    }*/
    @GetMapping("/member/{id}")
    public String member(Model model, @PathVariable("id") Long id){
        System.out.println("회원 화면입니다.");
        Member member = service.member(id);
        model.addAttribute("member", member);
        return "member";
    }

/*    @PostMapping("/member/{id}")
    public String memberUpdate(MemberForm form, @PathVariable("id") Long id){
        Member member = service.findMember(id).get();
        member.setPhone(form.getPhone());
        member.setEmail(form.getEmail());
        member.setAddress(form.getAddress());
        System.out.println(member);
        Long aLong = service.memberJoin(member);
        return "redirect:/";
    }*/
    @PostMapping("/member")
    public String memberUpdate(MemberForm form){
        Member member = service.member(form.getId());
        if(form.getName() != member.getName()){
            member.setName(form.getName());
        }
        if(form.getAddress() != member.getAddress()){
            member.setAddress(form.getAddress());
        }
        if(form.getEmail() != member.getEmail()){
            member.setEmail(form.getEmail());
        }
        if(form.getPhone() != member.getPhone()){
            member.setPhone(form.getPhone());
        }
        System.out.println(member);
        service.memberJoin(member);
        return "redirect:/";
    }
}
