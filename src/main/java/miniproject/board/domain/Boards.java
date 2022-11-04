package miniproject.board.domain;


import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "boards")
public class Boards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotNull
    private String title;

    private String content;
/*    @ManyToOne
    @JoinColumn(name = "userid")
    private Member member;*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
