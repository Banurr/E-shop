package banurr.final_project.controllers;

import banurr.final_project.models.Comment;
import banurr.final_project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Comment> allComments()
    {
        return commentService.allComments();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteComment(@RequestBody Long id)
    {
        commentService.deleteComment(id);
    }
}
