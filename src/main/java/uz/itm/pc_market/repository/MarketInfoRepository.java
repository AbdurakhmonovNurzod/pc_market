package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.pc_market.entity.MarketInfo;
import uz.itm.pc_market.projection.MarketInfoProjection;

@RepositoryRestResource(path = "marketInfo",excerptProjection = MarketInfoProjection.class)
public interface MarketInfoRepository  extends JpaRepository<MarketInfo,Integer> {
}
