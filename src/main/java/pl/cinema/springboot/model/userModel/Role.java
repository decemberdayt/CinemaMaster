package pl.cinema.springboot.model.userModel;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDROLE")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "NAME", length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
