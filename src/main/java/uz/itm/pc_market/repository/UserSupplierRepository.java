package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.pc_market.entity.UserSupplier;
import uz.itm.pc_market.projection.UserSupplierProjection;


@RepositoryRestResource(path = "/userSupplier",excerptProjection = UserSupplierProjection.class)
public interface UserSupplierRepository extends JpaRepository<UserSupplier,Integer> {
}
