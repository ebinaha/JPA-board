package miniproject.board.controller;

import miniproject.board.domain.Boards;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping("/boardlist")
    public String boardlist(){
        return "boardlist.html";
    }

    @GetMapping("/board")
    public String board(){
        return "board";
    }

    @GetMapping("/boardwrite")
    public String boardwrite(){
        return "boardwrite";
    }

    // 던지는 data를 매개변수로 받아옴 : input name ="" <= 로 받아옴
    @PostMapping("/boardwrite")
    public String boardwritepost(Boards boards){
        System.out.println(boards.getTitle() +":"+ boards.getContent());
        return "redirect:/boardlist";
    }


}
