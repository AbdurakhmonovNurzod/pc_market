package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.itm.pc_market.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);

    List<Category>findAllByParentCategory_Id(Integer parentCategory_id);


    @Query(value = "select * from category  where main_category=true",nativeQuery = true)
    List<Category>findAllMainCategories();


}
