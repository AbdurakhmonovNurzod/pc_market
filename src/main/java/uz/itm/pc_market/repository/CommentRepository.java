package uz.itm.pc_market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.itm.pc_market.entity.Comment;
import uz.itm.pc_market.projection.CommentProjection;

@RepositoryRestResource(path = "comment",excerptProjection = CommentProjection.class)
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
