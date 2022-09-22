package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.pc_market.entity.Currency;
import uz.itm.pc_market.projection.CurrencyProjection;

@RepositoryRestResource(path = "currency",excerptProjection =CurrencyProjection.class)
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
