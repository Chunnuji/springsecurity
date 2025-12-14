package com.example.practiceSecurity.Service;

import com.example.practiceSecurity.Dao.PersonRepository;
import com.example.practiceSecurity.Domain.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Create or Update
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    // Get all persons
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Get person by ID
    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    // Delete person
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
