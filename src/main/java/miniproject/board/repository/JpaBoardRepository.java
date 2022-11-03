package miniproject.board.repository;

import miniproject.board.domain.Boards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 자동생성된 인터페이스이자 구현체
 */
@Repository
public interface JpaBoardRepository extends JpaRepository<Boards, Integer> {
    //table Entity로 정의 / key??
    //implement override 안하고 사용
}
