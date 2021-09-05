package learning.springboot.web.app.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "tbl_one_to_one_book_page_num")
public class BookPageNum {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}