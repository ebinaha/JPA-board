package miniproject.board.controller;

import miniproject.board.domain.Boards;
import miniproject.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/board/{id}")
    public String board(Model model, @PathVariable("id") Integer id){
        Boards board = service.board(id);
        model.addAttribute("board", board);
        return "board";
    }

    @GetMapping("/boardwrite")
    public String boardwrite(){
        return "boardwrite";
    }

    // 던지는 data를 매개변수로 받아옴 : input name ="" <= 로 받아옴
    @PostMapping("/boardwrite")
    public String boardwritepost(BoardForm form){
        Boards board = new Boards();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        service.boardSave(board);
        //service 로직을 repository db에 저장

        System.out.println("게시글이 등록되었습니다.");
        return "redirect:/boardlist";
    }

    @PostMapping("/boardUpdate/{id}")
    public String boardUpdate(BoardForm form, @PathVariable("id") Integer id){
        System.out.println(form);

        Boards board = new Boards();
        board.setId(id);
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        service.boardSave(board); //신규등록(id 없는 상태), 업데이트(id 있는 상태)

        return "redirect:/boardlist";

    }
    @PostMapping("/boardDelete/{id}")
    public String boardDelete(@PathVariable("id") Integer id){
        service.boardDelete(id);
        return "redirect:/boardlist";
    }


}
