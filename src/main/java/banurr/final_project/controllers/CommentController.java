package banurr.final_project.controllers;

import banurr.final_project.models.Comment;
import banurr.final_project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    public List<Comment> allComments()
    {
        return commentService.allComments();
    }

    @DeleteMapping("/delete")
    public void deleteComment(@RequestBody Long id)
    {
        commentService.deleteComment(id);
    }
}
