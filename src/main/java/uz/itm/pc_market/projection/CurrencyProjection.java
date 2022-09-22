package uz.itm.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.pc_market.entity.Currency;

@Projection(types = {Currency.class})
public interface CurrencyProjection {

    Integer getId();

    String getName();

    String getSign();
}
