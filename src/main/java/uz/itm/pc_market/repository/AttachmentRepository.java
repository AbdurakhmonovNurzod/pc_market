package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.itm.pc_market.entity.Attachment;

import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
    List<Attachment>findAllByProduct_Id(Integer product_id);


}
