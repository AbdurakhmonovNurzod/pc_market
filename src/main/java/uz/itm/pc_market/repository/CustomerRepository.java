package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.pc_market.entity.Customer;
import uz.itm.pc_market.projection.CustomerProjection;

@RepositoryRestResource(path = "/customer",excerptProjection = CustomerProjection.class)
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
