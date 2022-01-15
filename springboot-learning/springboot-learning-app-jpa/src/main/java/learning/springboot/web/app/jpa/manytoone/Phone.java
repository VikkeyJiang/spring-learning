package learning.springboot.web.app.jpa.manytoone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_many_to_one_phone")
class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String phoneNum;

    /**
     * 子对象持有ManyToOne的关系
     */
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Person person;

    protected Phone() {
    }

    Phone(Person person, String phoneNum) {
        this.person = person;
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "PhoneCascadeMerge{" +
            "phoneNum='" + phoneNum + '\'' +
            ", person=" + person +
            '}';
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

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
