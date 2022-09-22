package uz.itm.pc_market.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)private Integer id;

    @Column(nullable = false)
    private String fileOriginalName;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String contentType;

    @ManyToOne
    private Product product;


}
