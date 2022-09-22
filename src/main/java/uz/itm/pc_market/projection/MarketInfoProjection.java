package uz.itm.pc_market.projection;

import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.config.Projection;
import uz.itm.pc_market.entity.MarketInfo;

import javax.persistence.Column;

@Projection(types = {MarketInfo.class})
public interface MarketInfoProjection {

     Integer getId();


     String getAddress();


     String getPhoneNumber();


     String getEmail();


     String getWorkingTime();
}
