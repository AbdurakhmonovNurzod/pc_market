package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.pc_market.entity.AttachmentContent;

import java.util.List;
import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    List<AttachmentContent>findAllByAttachment_Product_Category_Id(Integer attachment_product_category_id);

    Optional<AttachmentContent> findAttachmentContentByAttachment_Id(Integer id);
}
