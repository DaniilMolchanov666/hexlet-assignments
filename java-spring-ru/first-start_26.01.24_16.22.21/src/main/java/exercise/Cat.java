package exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cats_table")
@Getter
@Setter
@ToString
public class Cat {

    @Id
    @Column(name="cat_id")
    @GenericGenerator(name="generatpor")
    @GeneratedValue(generator = "generator")
    public int id;

    @Column(name="cat_get_name")
    public CatGet catGet;

    public Cat() {

    }

    public Cat(int id, CatGet catGet) {
        this.id = id;
        this.catGet = catGet;
    }

    public void say() {
        System.out.println("KEK");
    }
}
