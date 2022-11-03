package miniproject.board.service;

import miniproject.board.domain.Boards;
import miniproject.board.repository.JpaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 게시글 관리 기능 구현
 */
@Service
public class BoardService {
    @Autowired
    private final JpaBoardRepository repository;

    public BoardService(JpaBoardRepository repository){
        this.repository = repository;
    }

    /**
     * 저장하는 비즈니스 로직
     * @param boards
     * @return
     */
    /* public Integer boardSave(Boards boards){
        Boards board = repository.save(boards);
        return board.getId();
    }*/
    public void boardSave(Boards boards){
        repository.save(boards);
    }

    /**
     * 게시판리스트
     * @return
     */
    public List<Boards> boardsList(){
        List<Boards> boards = repository.findAll();
        return boards;
    }

    /**
     * id를 매개변수로 삭제하기
     * @param id
     */
    public void boardDelete(Integer id){
        repository.deleteById(id);
    }

    /**
     * id를 매개변수로 조회하기
     * @param id
     * @return
     */
    public Boards board(Integer id){
        //optional ==> 리스트 형태
        Optional<Boards> boardslist = repository.findById(id);
        return boardslist.get();
    }



}
