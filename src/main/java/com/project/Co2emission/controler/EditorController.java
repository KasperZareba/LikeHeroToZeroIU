package com.project.Co2emission.controler;

import com.project.Co2emission.repository.entity.Emission;
import com.project.Co2emission.service.EmissionService;
import com.project.Co2emission.service.EmissionToCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/editor")
public class EditorController {

    private static final Logger logger = LoggerFactory.getLogger(EditorController.class);

    @Autowired
    private EmissionToCheckService emissionToCheckService;

    @Autowired
    private EmissionService emissionService;

    @PostMapping("/delete/{id}")
    public String deleteEmission(@PathVariable Long id) {
        emissionToCheckService.deleteEmission(id);
        return "redirect:/editor";
    }


}