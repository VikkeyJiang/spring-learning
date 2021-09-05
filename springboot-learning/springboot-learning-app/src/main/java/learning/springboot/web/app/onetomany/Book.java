package learning.springboot.web.app.onetomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_one_to_many_book")
class Book {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 子对象设定父对象的关联关系
     */
    @ManyToOne
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
