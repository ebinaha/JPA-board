package miniproject.board.controller;

import miniproject.board.domain.Boards;
import miniproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/boardlist")
    public String boardlist(Model model){
        List<Boards> boards = service.boardsList();
        model.addAttribute("boards", boards);
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
        //service 로직을 repository db에 저장
        service.boardSave(boards);
        System.out.println("게시글이 등록되었습니다.");
        return "redirect:/boardlist";
    }

}
