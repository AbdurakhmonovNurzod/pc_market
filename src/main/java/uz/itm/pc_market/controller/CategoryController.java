package uz.itm.pc_market.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.itm.pc_market.entity.Category;
import uz.itm.pc_market.loader.CategoryLoader;
import uz.itm.pc_market.loader.Result;
import uz.itm.pc_market.service.CategoryService;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Result>addCategory(@RequestBody @Valid CategoryLoader categoryLoader){
        Result result = categoryService.addCategory(categoryLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);

    }
    @GetMapping
    public List<Category>getCategories(){
        return categoryService.getCategories();
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id){
       return categoryService.getCategoryById(id);
    }
    @GetMapping("/mainCategories")
    public List<Category>getMainCategories(){
    return categoryService.getMainCategories();

    }
    @GetMapping("/mainCategories/{parentCategoryId}")
    public List<Category>getCategoriesByParentCategoryId(@PathVariable Integer parentCategoryId){
       return   categoryService.getByParentCategoryId(parentCategoryId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result>editCategory(@PathVariable Integer id,@RequestBody @Valid CategoryLoader categoryLoader){
        Result result = categoryService.editCategory(id, categoryLoader);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Result>deleteCategory(@PathVariable Integer id){
        Result result = categoryService.deleteCategory(id);
        if (result.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getStringStringMap(ex);
    }

    static Map<String, String> getStringStringMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
