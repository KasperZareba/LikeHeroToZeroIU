package com.project.Co2emission.controler;

import com.project.Co2emission.repository.entity.Editor;
import com.project.Co2emission.repository.entity.SecurityConfig;
import com.project.Co2emission.service.EmissionToCheckService;
import com.project.Co2emission.service.AddEditorService;
import com.project.Co2emission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AddEditorController {

    @Autowired
    private EmissionToCheckService emissionToCheckService;

    @Autowired
    private AddEditorService addEditorService;

    @Autowired
    private UserService userService;


    @GetMapping("/addEditor")
    public String showAddEditorForm(Model model) {
        model.addAttribute("editor", new Editor());
        return "addEditor";
    }

    @PostMapping("/addEditor")
    public String addEditor(@ModelAttribute Editor editor, Model model) {
        addEditorService.save(editor);
        userService.createNewUser(editor.getLogin(),editor.getPassword());
        model.addAttribute("message", "Edytor został dodany!");
        return "admin";
    }
}
