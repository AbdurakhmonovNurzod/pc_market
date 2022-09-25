package uz.itm.pc_market.entity;

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
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "basket")
    private List<Product> product;

    private double fullPrice;

    @OneToOne
    private Customer customer;

    private boolean delivered=false;

    public void addProduct(Product product){
        this.product.add(product);
    }
    public void removeProduct(Product product){
        this.product.remove(product);
    }

}
