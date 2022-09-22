package uz.itm.pc_market.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Currency {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String sign;


}
