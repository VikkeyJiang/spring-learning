package learning.springboot.web.app.manytoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_many_to_one_phone")
public class Phone {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 子对象持有ManyToOne的关系
     */
    @ManyToOne
    @JoinColumn(name = "conn_person_id")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
