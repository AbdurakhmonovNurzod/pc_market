package uz.itm.pc_market.loader;

import lombok.*;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasketLoader {
    private List<Integer> productId;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhoneNumber;
    private boolean delivered;
}
