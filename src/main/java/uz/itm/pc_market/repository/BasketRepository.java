package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.itm.pc_market.entity.Basket;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket,Integer> {

    @Query(value = "select * from basket where delivered=false ",nativeQuery = true)
    List<Basket>findAllNotDelivered();

}
