package uz.itm.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.pc_market.entity.Brand;

@Projection(types = {Brand.class})
public interface BrandProjection {
    Integer getId();
    String getBrandName();
}
