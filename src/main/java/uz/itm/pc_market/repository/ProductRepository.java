package uz.itm.pc_market.repository;

import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.itm.pc_market.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByName(String name);

    List<Product>findAllByCategory_Id(Integer category_id);

    @Query(value = "select * from product where with_discount=true",nativeQuery = true )
    List<Product>findAllByWithDiscount();
    @Query(value = "select * from products where recommended=true",nativeQuery = true)
    List<Product>findAllByRecommended();
}
