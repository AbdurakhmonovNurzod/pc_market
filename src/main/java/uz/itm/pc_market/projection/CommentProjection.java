package uz.itm.pc_market.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.itm.pc_market.entity.Comment;

@Projection(types = {Comment.class})
public interface CommentProjection {
    Integer getId();
    String getCommentOwnerName();
    String getCommentMessage();

}
