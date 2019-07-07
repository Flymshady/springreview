package cz.cellar.springreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class ViewController {


    private String appMode;
    //constructor injection for app-mode (from application.properties)
    @Autowired
    public ViewController(Environment environment){
        appMode=environment.getProperty("app-mode");
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Štěpán Cellar");
        model.addAttribute("mode", appMode );
        return "index";
    }

    @RequestMapping("/item/{id}")
    public String item(Model model, @PathVariable Long id){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Štěpán Cellar");
        model.addAttribute("mode", appMode );
        model.addAttribute("id", id);
        return "item";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Štěpán Cellar");
        model.addAttribute("mode", appMode );
        return "createItem";
    }
    @RequestMapping("/update/{id}")
    public String updateItem(Model model, @PathVariable Long id){
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Štěpán Cellar");
        model.addAttribute("mode", appMode );
        model.addAttribute("id", id);
        return "updateItem";
    }
}
