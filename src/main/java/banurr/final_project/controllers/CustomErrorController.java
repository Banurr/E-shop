package banurr.final_project.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController
{
    @RequestMapping("/error")
    public String handleError()
    {
        System.out.println("Some error occured");// Provide your custom error page view name
        return "error";
    }

    public String getErrorPath()
    {
        return "/error";
    }
}

