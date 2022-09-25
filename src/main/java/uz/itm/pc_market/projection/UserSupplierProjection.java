package uz.itm.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.pc_market.entity.UserSupplier;

@Projection(types = {UserSupplier.class})
public interface UserSupplierProjection {

    Integer getId();
    String getFullName();
    String getEmail();
    String getMessage();

}
