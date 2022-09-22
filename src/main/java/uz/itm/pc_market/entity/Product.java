package uz.itm.pc_market.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne
    private Category category;

    private double price;

    private boolean withDiscount;

    private boolean recommended;

    @ManyToOne
    private Currency priceInCurrency;
}
