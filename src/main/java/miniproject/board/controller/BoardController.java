package miniproject.board.controller;

import miniproject.board.domain.Boards;
import miniproject.board.service.BoardService;
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
public class BoardController {

    @Autowired
    private BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/boardlist")
    public String boardlist(Model model,
                            @PageableDefault(page=0, size=20, sort="id", direction = Sort.Direction.DESC)
                            Pageable pageable){
        Page<Boards> boards = service.boardsList(pageable);

        int nowPage = boards.getPageable().getPageNumber() + 1;
        int startPage = 1;
        int endPage = boards.getTotalPages();

        model.addAttribute("boards", boards);

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist.html";
    }


    @GetMapping("/board/{id}")
    public String board(Model model, @PathVariable("id") Integer id){
        System.out.println("게시판 화면입니다.");
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
    public String boardwritepost(BoardForm form, Model model){ //message 처리와 redirect uri 전달 > model 매개변수 추가
        Boards board = new Boards();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());
        service.boardSave(board);
        //service 로직을 repository db에 저장

        Integer res = service.boardSave(board);
        String message = "";
        if(res == form.getId()){
            message = "글 등록에 성공했습니다.";
        } else {
            message = "글 등록에 실패했습니다.";
        }
        String redirectURI = "/boardlist";
        model.addAttribute("message", message);
        model.addAttribute("redirectURI", redirectURI );
        return "messageRedirect";

/*        model.addAttribute("message", "글 저장에 성공했습니다.");
        model.addAttribute("redirectURI", "/boardlist");
        System.out.println("게시글이 등록되었습니다.");

        return "messageRedirect";*/
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(BoardForm form, Model model){
        Boards board = service.board(form.getId());
        // 변경이 있을 때만 업데이트
        if(form.getTitle() != board.getTitle()) {
            board.setTitle(form.getTitle());
        }
        if(form.getContent() != board.getContent()) {
            board.setContent(form.getContent());
        }
        //신규등록(id 없는 상태), 업데이트(id 있는 상태)
        Integer res = service.boardSave(board);
        String message = "";
        if(res == form.getId()){
            message = "글 수정에 성공했습니다.";
        } else {
            message = "글 수정에 실패했습니다.";
        }
        String redirectURI = "/boardlist";
        model.addAttribute("message", message);
        model.addAttribute("redirectURI", redirectURI );
        return "messageRedirect";
    }
    
    @PostMapping("/boardDelete")
    //boardform의 id값만 들고오는 것
    public String boardDelete(Integer id, Model model){
        service.boardDelete(id);

        model.addAttribute("message", "글 삭제에 성공했습니다.");
        model.addAttribute("redirectURI", "/boardlist");
        return "messageRedirect";
    }


}
