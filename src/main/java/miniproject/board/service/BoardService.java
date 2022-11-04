package miniproject.board.service;

import miniproject.board.domain.Boards;
import miniproject.board.repository.JpaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Integer boardSave(Boards boards){
        Boards save = repository.save(boards);
        return save.getId();
    }

    /**
     * 게시판리스트, domain.pageable : 추상인터페이스
     * @return
     */
    public Page<Boards> boardsList(Pageable pageable){
        Page<Boards> boards = repository.findAll(pageable);
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
