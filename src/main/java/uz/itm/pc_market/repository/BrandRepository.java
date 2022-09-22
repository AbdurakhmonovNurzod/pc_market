package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.pc_market.entity.Brand;
import uz.itm.pc_market.projection.BrandProjection;

@RepositoryRestResource(path = "brand",excerptProjection = BrandProjection.class)
public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
