package exercise.controller;

import exercise.repository.PersonRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Person;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person show(@PathVariable long id) {
        return personRepository.findById(id).get();
    }

    @GetMapping()
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Person addNewPerson(@RequestBody Person p) {
        personRepository.save(Optional.of(p).orElseGet(Person::new));
        return p;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
    }
}
