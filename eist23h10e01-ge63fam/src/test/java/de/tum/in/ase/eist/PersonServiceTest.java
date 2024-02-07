package de.tum.in.ase.eist;

import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import de.tum.in.ase.eist.service.PersonService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @Test
    void testAddPerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        personService.save(person);

        assertEquals(1, personRepository.findAll().size());
    }

    @Test
    void testDeletePerson() {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        person = personRepository.save(person);

        personService.delete(person);

        assertTrue(personRepository.findAll().isEmpty());
    }

    // TODO: Add more test cases here

    @Test
    void testAddParent() {
        var parent = new Person();
        parent.setFirstName("Max");
        parent.setLastName("Mustermann");
        parent.setBirthday(LocalDate.now());
        parent = personRepository.save(parent);

        var child = new Person();
        child.setFirstName("Anna");
        child.setLastName("Bauer");
        child.setBirthday(LocalDate.now());
        child = personRepository.save(child);

        personService.addParent(child,parent);

        assertEquals(2, personRepository.findAll().size());
        assertEquals(1, child.getParents().size());
        assertTrue(child.getParents().contains(parent));
    }
    @Test
    void testAddThreeParents() {
        var parent1 = new Person();
        parent1.setFirstName("Max");
        parent1.setLastName("Mustermann");
        parent1.setBirthday(LocalDate.now());
        parent1 = personRepository.save(parent1);

        var parent2 = new Person();
        parent2.setFirstName("Tim");
        parent2.setLastName("Bosch");
        parent2.setBirthday(LocalDate.now());
        parent2 = personRepository.save(parent2);

        var parent3 = new Person();
        parent3.setFirstName("Mike");
        parent3.setLastName("Volker");
        parent3.setBirthday(LocalDate.now());
        parent3 = personRepository.save(parent3);

        var child = new Person();
        child.setFirstName("Anna");
        child.setLastName("Bauer");
        child.setBirthday(LocalDate.now());
        child = personRepository.save(child);

        assertEquals(4, personRepository.findAll().size());
        Person person = personService.addParent(child, parent1);
        assertEquals(1, child.getParents().size());
        assertTrue(child.getParents().contains(parent1));
        Person person1 = personService.addParent(person, parent2);
        assertTrue(person1.getParents().contains(parent2));

        final Person c = person1;
        final Person p = parent3;
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            personService.addParent(c,p);
        });
        String expectedMessage = "test exception";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }



}
