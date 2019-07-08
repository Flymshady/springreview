package cz.cellar.springreview.api;

import cz.cellar.springreview.model.Person;
import cz.cellar.springreview.model.Role;
import cz.cellar.springreview.repository.PersonRepository;
import cz.cellar.springreview.repository.RoleRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public PersonController(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

   /* @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    Person create(){
        Role adminRole = roleRepository.getOne(2L);
        Person admin=new Person("User1", passwordEncoder.encode("user1"),"User1", adminRole);

        return personRepository.save(admin);


    }*/


}
