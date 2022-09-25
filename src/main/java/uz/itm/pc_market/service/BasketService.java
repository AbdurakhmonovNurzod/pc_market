package uz.itm.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.itm.pc_market.entity.Basket;
import uz.itm.pc_market.entity.Customer;
import uz.itm.pc_market.entity.Product;
import uz.itm.pc_market.loader.BasketLoader;
import uz.itm.pc_market.loader.Result;
import uz.itm.pc_market.repository.BasketRepository;
import uz.itm.pc_market.repository.ProductRepository;

import javax.smartcardio.ResponseAPDU;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;

    @Autowired
    ProductRepository productRepository;


    public Result addBasket(BasketLoader basketLoader) {
        List<Product> productList = productRepository.findAllById(basketLoader.getProductId());
        if (!productList.isEmpty()) {
            Basket basket = new Basket();
            double fullPrice=0;
            for (Product product : productList) {
                double price = product.getPrice();
                fullPrice+=price;

                basket.addProduct(product);
            }
            Customer customer=new Customer();
            customer.setName(basketLoader.getCustomerName());
            customer.setAddress(basketLoader.getCustomerAddress());
            customer.setEmail(basketLoader.getCustomerEmail());
            customer.setPhoneNumber(basketLoader.getCustomerPhoneNumber());
            basket.setCustomer(customer);
            basket.setFullPrice(fullPrice);
            basketRepository.save(basket);
            return new Result(true, "added successfully");


        }
        return new Result(false,"there is no products with this name");

    }

    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    public Basket getBasketById(Integer id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        return basketOptional.orElse(null);
    }

    public List<Basket> getNotDeliveredBaskets() {
        return basketRepository.findAllNotDelivered();
    }

    public Result editBasket(Integer id, BasketLoader basketLoader) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        if (basketOptional.isPresent()){
            Basket basket = basketOptional.get();
            basket.setDelivered(basketLoader.isDelivered());
            Customer customer = basket.getCustomer();
            customer.setName(basketLoader.getCustomerName());
            customer.setEmail(basketLoader.getCustomerEmail());
            customer.setPhoneNumber(basketLoader.getCustomerPhoneNumber());
            customer.setAddress(basketLoader.getCustomerAddress());
            basket.setCustomer(customer);
            basket.setProduct(productRepository.findAllById(basketLoader.getProductId()));
            basketRepository.save(basket);
            return new Result(true,"edited successfully");
        }
       return new Result(false,"there is no basket with this id");
    }

    public Result deleteBasket(Integer id) {
        Optional<Basket> basketOptional = basketRepository.findById(id);
        if (basketOptional.isPresent()){
            basketRepository.delete(basketOptional.get());
            return new Result(true,"deleted successfully");
        }
        return new Result(false,"there is no basket with this id");
    }
}
