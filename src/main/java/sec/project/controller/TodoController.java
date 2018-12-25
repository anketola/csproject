package sec.project.controller;

import sec.project.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.Todo;
import sec.project.repository.AccountRepository;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    
    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("*")
    public String defaultRedirectToFiles() {
        return "redirect:/items";
    }
    
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String getList(Authentication authentication, Model model) {
        model.addAttribute("todos", accountRepository.findByUsername(authentication.getName()).getTodos());
        return "todo";
    }
    
    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String add(Authentication authentication, @RequestParam String name) {
        Account account = accountRepository.findByUsername(authentication.getName());
        Todo t = new Todo();
        t.setName(name);
        t.setAccount(account);
        todoRepository.save(t);
        return "redirect:/items";
    }
    
    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        todoRepository.delete(id);
        return "redirect:/items";
    }
}
