package uz.itm.pc_market.loader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryLoader {

    @NotNull
    private String name;

    private Integer parentCategoryId=0;
}
