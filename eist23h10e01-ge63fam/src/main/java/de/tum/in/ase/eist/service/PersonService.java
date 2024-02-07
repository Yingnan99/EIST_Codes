package de.tum.in.ase.eist.service;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        if (person.getBirthday().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Birthday may not be in the future");
        }
        return personRepository.save(person);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }

    public Optional<Person> getById(Long id) {
        return personRepository.findWithParentsAndChildrenById(id);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person addParent(Person person, Person parent) {
        // TODO: Implement
        if (person.getParents().size() >= 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person cannot have more than two parents.");
        }
        Set<Person> updatedParents = new HashSet<>(person.getParents());
        updatedParents.add(parent);
        person.setParents(updatedParents);
        return personRepository.save(person);
    }

    public Person addChild(Person person, Person child) {
        // TODO: Implement
        if (child.getParents().size() >= 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Child cannot have more than 2 parents.");
        }
        Set<Person> updatedChildren = new HashSet<>(person.getChildren());
        updatedChildren.add(child);
        person.setChildren(updatedChildren);
        return personRepository.save(person);
    }

    public Person removeParent(Person person, Person parent) {
        // TODO: Implement
        if (person.getParents().size() <= 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person must have at least 1 parent before removing.");
        }
        Set<Person> updatedParents = new HashSet<>(person.getParents());
        updatedParents.remove(parent);
        person.setParents(updatedParents);
        return personRepository.save(person);
    }

    public Person removeChild(Person person, Person child) {
        // TODO: Implement
        if (child.getParents().size() <= 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Child must have at least 1 parent before removing");
        }
        Set<Person> updatedChildren = new HashSet<>(person.getChildren());
        updatedChildren.remove(child);
        person.setChildren(updatedChildren);
        return personRepository.save(person);
    }

    public Person getPersonWithParents(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            Hibernate.initialize(person.getParents());
            return person;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
    }
}
