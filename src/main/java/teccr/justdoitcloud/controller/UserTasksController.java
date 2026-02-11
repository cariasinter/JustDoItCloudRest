package teccr.justdoitcloud.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import teccr.justdoitcloud.data.Task;
import teccr.justdoitcloud.data.User;
import java.time.LocalDateTime;

@Slf4j
@Controller
@RequestMapping("/user/tasks")
@SessionAttributes("user")
public class UserTasksController {

    @GetMapping
    public String showUserTasks(Model model) {
        model.addAttribute("newTask", new Task("", LocalDateTime.now(), null, Task.Status.INPROGRESS));
        return "usertasks";
    }

    @PostMapping
    public String addTask(@Valid @ModelAttribute(name = "newTask") Task newTask,
                          Errors errors,
                          @ModelAttribute("user") User user) {
        log.info("Adding task: " + newTask);
        if (errors.hasErrors()) {
            return "usertasks";
        }

        user.addTask(newTask);
        return "redirect:/user/tasks";
    }
}
