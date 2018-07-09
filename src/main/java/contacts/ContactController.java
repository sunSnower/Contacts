package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

//为Contacts应用处理基本的web请求
@Controller
@RequestMapping("/")
public class ContactController {
    private ContactRespository contactRespository;

    //注入ContactRespository
    @Autowired
    public ContactController(ContactRespository contactRespository){
        this.contactRespository = contactRespository;
    }

    //处理GET "/"
    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String, Object> model){
        List<Contact> contacts = contactRespository.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    //处理POST "/"
    @RequestMapping(method = RequestMethod.POST)
    public String submit(Contact contact){
        contactRespository.save(contact);
        return "redirect:/";
    }
}
