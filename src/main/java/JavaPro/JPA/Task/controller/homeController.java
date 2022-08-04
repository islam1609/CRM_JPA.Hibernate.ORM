package JavaPro.JPA.Task.controller;

import JavaPro.JPA.Task.model.Course;
import JavaPro.JPA.Task.model.Operator;
import JavaPro.JPA.Task.model.Request;
import JavaPro.JPA.Task.repository.courseRepository;
import JavaPro.JPA.Task.repository.operatorRepository;
import JavaPro.JPA.Task.repository.requestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class homeController {
    @Autowired
    private requestRepository requestRepository;

    @Autowired
    private courseRepository courseRepository;

    @Autowired
    private operatorRepository operatorRepository;


    @GetMapping(value = "/")
    public String homePage(Model model){
        List<Request> requests = requestRepository.findAll();
        model.addAttribute("request" ,requests);
        return "homePage";
    }
    @GetMapping(value = "/addRequest")
    public String addRequest(Model model){
        List<Course> allcourses = courseRepository.findAll();
        model.addAttribute("courses",allcourses);
        return "addRequest";
    }

    @PostMapping(value = "/addRequest")
    public String addRequest(@RequestParam(name = "userName") String name,
                             @RequestParam(name = "course_id") Long id,
                             @RequestParam(name = "commentary") String commentary,
                             @RequestParam(name = "phoneNumber") String phoneNumber){
        boolean handled = false;

        Course course = courseRepository.findById(id).orElse(null);

        Request request = new Request();

        request.setUserName(name);
        request.setCourse(course);
        request.setCommentary(commentary);
        request.setPhoneNumber(phoneNumber);
        request.setHandled(handled);

        requestRepository.save(request);

        return "redirect:addRequest";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id,
                          Model model){
        List <Course> courses = courseRepository.findAll();
        Request request =  requestRepository.findById(id).orElse(null);
        List<Operator> operators = operatorRepository.findAll();
        model.addAttribute("operators",operators);
        model.addAttribute("request",request);
        model.addAttribute("courses",courses);
        return "details";
    }

    @PostMapping(value = "/deleteRequest")
    public String deleteRequest(@RequestParam(name = "id") Long id){
        requestRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = "update    Request")
    public String updateRequest(@RequestParam(name = "userName") String name,
                                @RequestParam(name = "commentary") String commentary,
                                @RequestParam(name = "phoneNumber") String phoneNumber,
                                @RequestParam(name = "course_id") Long course_id,
                                @RequestParam(name = "id") Long id){
       boolean handled = true;

       Course course= courseRepository.findById(course_id).orElse(null);

        Request request = new Request();
        request.setUserName(name);
        request.setCommentary(commentary);
        request.setCourse(course);
        request.setPhoneNumber(phoneNumber);
        request.setHandled(handled);
        request.setId(id);

        requestRepository.save(request);

        return "redirect:/";
    }
}
