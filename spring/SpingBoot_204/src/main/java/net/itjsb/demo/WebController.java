package net.itjsb.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/tvshowconfirm").setViewName("tvshowconfirm");
    }

    @GetMapping("/tvform")
    public String loadTvForm(Model model){
        model.addAttribute("tvshow", new Tvshow());
        return "tvform";
    }

    @PostMapping("/tvform")
    public String processTvForm(@Valid Tvshow tvshow,
                                BindingResult result){
        if (result.hasErrors()){
            return "tvform";
        }
        return "redirect:/tvshowconfirm";
    }
}
