package learning.springboot.web.app.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "tbl_one_to_one_book")
class Book {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * OneToOne，单向连接使用时，由子对象保存关联关系，次数detail对应的就是 BookDetail的id
     */
    @OneToOne
    private BookDetail detail;

    /**
     * 双向连接时，
     */
    @OneToOne(mappedBy = "book")
    private BookPageNum pageNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookPageNum getPageNum() {
        return pageNum;
    }

    public void setPageNum(BookPageNum pageNum) {
        this.pageNum = pageNum;
    }
}
