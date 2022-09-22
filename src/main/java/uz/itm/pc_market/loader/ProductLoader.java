package uz.itm.pc_market.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductLoader {

    private String name;


    private Integer categoryId;

    private double price;

    private boolean withDiscount;

    private boolean recommended;


    private Integer priceInCurrencyId;
}
