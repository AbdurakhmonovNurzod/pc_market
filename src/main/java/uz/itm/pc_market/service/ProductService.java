package uz.itm.pc_market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.itm.pc_market.entity.*;
import uz.itm.pc_market.loader.ProductLoader;
import uz.itm.pc_market.loader.Result;
import uz.itm.pc_market.repository.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result addProduct(ProductLoader productLoader) {
        boolean existsByName = productRepository.existsByName(productLoader.getName());
        if (existsByName)
            return new Result(false, "the product with this name is already exist");
        Product product = new Product();

        Optional<Category> categoryOptional = categoryRepository.findById(productLoader.getCategoryId());
        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            product.setCategory(category);
            Optional<Currency> currencyOptional = currencyRepository.findById(productLoader.getPriceInCurrencyId());
            if (currencyOptional.isPresent()){
                product.setPriceInCurrency(currencyOptional.get());
                product.setName(productLoader.getName());
                product.setPrice(productLoader.getPrice());
                product.setRecommended(productLoader.isRecommended());
                product.setWithDiscount(product.isWithDiscount());
                productRepository.save(product);
                return new Result(true,"added successfully");
            }
            return new Result(false,"there is no currency with this name");


        }
        return new Result(false,"you have to enter correct category for product");

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }

    public List<Product> getAllProductswithDiscount() {
        return productRepository.findAllByWithDiscount();
    }

    public List<Product> getRecommendedProducts() {
        return productRepository.findAllByRecommended();
    }

    public Result editProduct(Integer id, ProductLoader productLoader) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            product.setName(productLoader.getName());
            product.setPrice(productLoader.getPrice());
            product.setRecommended(productLoader.isRecommended());
            product.setWithDiscount(productLoader.isWithDiscount());
            Optional<Category> categoryOptional = categoryRepository.findById(productLoader.getCategoryId());
            categoryOptional.ifPresent(product::setCategory);
            Optional<Currency> currencyOptional = currencyRepository.findById(productLoader.getPriceInCurrencyId());
            currencyOptional.ifPresent(product::setPriceInCurrency);
            productRepository.save(product);
            return new Result(true,"edited successfully");
        }
        return new Result(false,"there is no product with this name");
    }

    public Result deleteProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            productRepository.delete(productOptional.get());
            return new Result(true,"deleted successfully");
        }
        return new Result(false,"theres is no product with this name");
    }

    public Result addProductPhoto(Integer productId, MultipartHttpServletRequest request) throws ServletException, IOException {
        Attachment attachment=new Attachment();
        AttachmentContent attachmentContent=new AttachmentContent();
        for (Part part : request.getParts()) {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                attachment.setFileOriginalName(part.getSubmittedFileName());
                attachment.setSize(part.getSize());
                attachment.setContentType(part.getContentType());
                attachment.setProduct(productOptional.get());
                MultipartFile file = request.getFile(part.getSubmittedFileName());
                assert file != null;
                attachmentContent.setMainContent(file.getBytes());
                attachmentContent.setAttachment(attachment);
                attachmentContentRepository.save(attachmentContent);
                return new Result(true, "photo added successfully");
            }
        }


            

        return new Result(false,"there is no product with this name");
    }



    public Result deleteProductPhoto(Integer productId, Integer photoId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            Optional<Attachment> attachmentOptional = attachmentRepository.findById(photoId);
            if (attachmentOptional.isPresent()){
                Optional<AttachmentContent> attachmentContentByAttachment_id = attachmentContentRepository.findAttachmentContentByAttachment_Id(photoId);
                if (attachmentContentByAttachment_id.isPresent()){
                   attachmentContentRepository.delete(attachmentContentByAttachment_id.get());
                    return new Result(true, "photo is deleted successfully");
                }
                return new Result(false, "there is no Attachment Content to remove");
            }
            return new Result(false, "there is no photo to remove with this name");
        }
        return new Result(false, "there is no product with this name");
    }

    public void getProductPhotos(Integer productId, HttpServletResponse response) throws IOException {
        List<Attachment> allByProduct_id = attachmentRepository.findAllByProduct_Id(productId);
        for (Attachment attachment : allByProduct_id) {
            Optional<AttachmentContent> attachmentContentByAttachment_id = attachmentContentRepository.findAttachmentContentByAttachment_Id(attachment.getId());
            if (attachmentContentByAttachment_id.isPresent()){
                response.setHeader("Content-Disposition","attachment; filename\""+attachment.getFileOriginalName()+"\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContentByAttachment_id.get().getMainContent(),response.getOutputStream());
            }

        }
    }
}
