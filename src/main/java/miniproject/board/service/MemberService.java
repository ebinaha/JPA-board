package miniproject.board.service;

import miniproject.board.domain.Member;
import miniproject.board.repository.JpaMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// 데이터를 저장하고 변경할 때는 항상 서비스 쪽에 @Transactional 애너테이션이 있어야 한다.
// 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작한다.
// 메서드가 정상 종료되면 트랜잭션을 커밋(commit)하고 런타임 예외가 발생하면 롤백한다.(Rollback)
// JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
@Transactional
@Service
public class MemberService {

    private final JpaMemberRepository repository ;

    @Autowired
    public MemberService(JpaMemberRepository repository){
        this.repository = repository;
    }
    /**
     * 회원 가입
     */
    public Long memberJoin(Member member){
        return repository.save(member).getId();
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findAllMembers(){
        return repository.findAll();
    }

    /**
     * 한건의 회원정보를 수정하거나 삭제하기 위해 가져온다.
     * @param id
     * @return
     */
    public Optional<Member> findMember(Long id){
        return repository.findById(id);
    }
    /**
     * 한건의 회원정보를 삭제한다.
     * @param id
     * @return
     */
    public void deleteMember(Long id) {
        repository.deleteById(id);
    }
    /**
     * 한건의 회원정보를 수정한다.
     * @param member
     * @return
     */
    public Long updateMember(Member member){
        return repository.save(member).getId();
    }
/*    public void clearMap(){
        repository.clearMap();
    }*/
}
