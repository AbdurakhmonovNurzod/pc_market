package uz.itm.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itm.pc_market.entity.Category;
import uz.itm.pc_market.loader.CategoryLoader;
import uz.itm.pc_market.loader.Result;
import uz.itm.pc_market.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryLoader categoryLoader) {
        boolean existsByName = categoryRepository.existsByName(categoryLoader.getName());
        if (existsByName)
            return new Result(false,"the Category with this name is already exist");
        Category category=new Category();
        category.setName(categoryLoader.getName());
        Optional<Category> categoryOptional = categoryRepository.findById(categoryLoader.getParentCategoryId());
        if (categoryOptional.isPresent()){
            category.setParentCategory(categoryOptional.get());
            categoryRepository.save(category);
            return new Result(true, "added successfully");
        }else if(categoryLoader.getParentCategoryId()==0){
        category.setMainCategory(true);
        categoryRepository.save(category);
        return new Result(true, "added successfully");
        }
        return new Result(false,"there is no parent category with this id");

    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> getMainCategories() {
        return categoryRepository.findAllMainCategories();
    }

    public List<Category> getByParentCategoryId(Integer parentCategoryId) {
        return categoryRepository.findAllByParentCategory_Id(parentCategoryId);
    }

    public Result editCategory(Integer id, CategoryLoader categoryLoader) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        boolean existsByName = categoryRepository.existsByName(categoryLoader.getName());
        if (existsByName)
            return new Result(false,"the category with this name is already exist");

        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            category.setName(categoryLoader.getName());
            Optional<Category> categoryOptionalParent = categoryRepository.findById(categoryLoader.getParentCategoryId());
            if (Objects.equals(category.getId(), categoryLoader.getParentCategoryId()))
                return new Result(false,"this category could not be parent category");
            if (categoryOptionalParent.isPresent()){
                category.setParentCategory(categoryOptionalParent.get());
                categoryRepository.save(category);
                return new Result(true,"added successfully");
            }else if(categoryLoader.getParentCategoryId()==0){
                categoryRepository.save(category);
                return new Result(true,"added successfully");

            }
        }
        return new Result(false,"there is no category with this name");
    }

    public Result deleteCategory(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            categoryRepository.delete(categoryOptional.get());
            return new Result(true,"deleted successfully");
        }
        return new Result(false,"there is no category with this name");
    }
}
