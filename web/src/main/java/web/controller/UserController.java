package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.DAO.DAO;
import web.models.User;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private DAO dao;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", dao.index());
        return "users/all";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        dao.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", dao.show(id));
        return "users/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        dao.update(id, user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        dao.delete(id);
        return "redirect:/";
    }

}
