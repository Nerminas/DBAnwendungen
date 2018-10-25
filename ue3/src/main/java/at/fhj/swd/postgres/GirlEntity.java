package at.fhj.swd.postgres;

import javax.persistence.*;

@Entity
@Table(name = "girl", schema = "ue03", catalog = "swd_ws18_13")
public class GirlEntity {
    private int id;

    @GeneratedValue
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer age;

    @Basic
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private BoyEntity relation;

    @OneToOne
    public BoyEntity getRelation() {
        return relation;
    }

    public void setRelation(BoyEntity relation) {
        this.relation = relation;
    }
}
