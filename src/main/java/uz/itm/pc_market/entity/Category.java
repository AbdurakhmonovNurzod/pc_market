package uz.itm.pc_market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false,unique = true)
    private String name;
    @JsonIgnore
    @ManyToOne
    private Category parentCategory;


    @OneToMany(mappedBy = "parentCategory")
    private List<Category> childCategories;

    private boolean mainCategory=false;
}
