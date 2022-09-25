package uz.itm.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.pc_market.entity.Customer;

@Projection(types = {Customer.class})
public interface CustomerProjection {
    Integer getId();
    String getName();
    String getAddress();
    String getEmail();
    String getPhoneNumber();

}
