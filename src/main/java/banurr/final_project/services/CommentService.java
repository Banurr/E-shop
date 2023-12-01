package banurr.final_project.services;

import banurr.final_project.models.Comment;
import banurr.final_project.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService
{
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> allComments()
    {
        return commentRepository.findAll();
    }

    public Comment getComment(Long id)
    {
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteComment(Long id)
    {
        commentRepository.deleteById(id);
    }
}
