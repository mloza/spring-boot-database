package pl.mloza.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mloza.entity.Task;
import pl.mloza.repository.TaskRepository;

@Controller
public class MainController {
    @Autowired
    public TaskRepository taskRepository;

    @RequestMapping("/db")
    @ResponseBody
    public String testMethod() {
        StringBuilder response = new StringBuilder();

        Task task = new Task()
                .withName("Task 1")
                .withDescription("Test task")
                .withBudget(123.55)
                .withDone(true);
        taskRepository.save(task);

        for(Task i: taskRepository.findAll()) {
            response.append(i).append("<br>");
        }

        return response.toString();
    }

    @RequestMapping("/db2")
    @ResponseBody
    public String testMethod2() {
        StringBuilder response = new StringBuilder();

        response.append("Tasks with done set to true:<br>");
        for(Task i: taskRepository.findByDone(true)) {
            response.append(i).append("<br>");
        }

        response.append("Tasks with done set to false:<br>");
        for(Task i: taskRepository.findByDone(false)) {
            response.append(i).append("<br>");
        }

        response.append("Tasks with \"Do\" in description:<br>");
        for(Task i: taskRepository.getByDescriptionLike("Do")) {
            response.append(i).append("<br>");
        }

        return response.toString();
    }
}
