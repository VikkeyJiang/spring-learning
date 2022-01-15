package learning.springboot.web.app.jpa.onetomany;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "tbl_one_to_many_person")
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Phone中没有指定ManyToOne，是单向关联，创建person和phone的连接表
     * <p>
     * 父对象持有OneToMany的关系
     */
    @OneToMany
    private List<Phone> phoneList;

    /**
     * Book中有ManyToOne，是双向关联，在Book表上创建关联字段
     * <p>
     * 关联的属性在Book中名字为person
     */
    @OneToMany(mappedBy = "person")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
