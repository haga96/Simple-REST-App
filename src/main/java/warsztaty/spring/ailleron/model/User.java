package warsztaty.spring.ailleron.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@TableGenerator(name = "test", initialValue = 3)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "test")
    private Long id;
    @Size(min = 3,max = 20, message = "{user.name.info}")
    private String name;
    private String surname;
    @Min(value = 18,message = "age should be grather than 17")
    @Max(value = 60,message = "age should be smaller than 61")
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public User(Long id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;

    }

}
