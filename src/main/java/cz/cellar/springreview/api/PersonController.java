package cz.cellar.springreview.api;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import cz.cellar.springreview.exception.ResourceNotFoundException;
import cz.cellar.springreview.model.Item;
import cz.cellar.springreview.model.Person;
import cz.cellar.springreview.model.Role;
import cz.cellar.springreview.repository.PersonRepository;
import cz.cellar.springreview.repository.RoleRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getById(@PathVariable(value = "id") Long id){
        return personRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Person", "id", id));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody Person create(@Valid @NonNull @RequestBody Person person){
        String password = passwordEncoder.encode(person.getPassword());
        person.setPassword(password);
        Role userRole = roleRepository.getOne(2L);
        person.setRole(userRole);
        if(personRepository.findByUsername(person.getUsername()).isPresent()){
            return null;
        }
        return personRepository.save(person);

    }
    @RequestMapping(value = "/username")
    public Boolean getByUsername(@RequestBody String username){
        if(personRepository.findByUsername(username).isPresent()){
            return false;
        }
        return true;
    }
}
