package de.tum.in.ase.eist;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tum.in.ase.eist.model.Person;
import de.tum.in.ase.eist.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PersonIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository personRepository;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    void testAddPerson() throws Exception {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        var response = this.mvc.perform(
                post("/persons")
                        .content(objectMapper.writeValueAsString(person))
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(1, personRepository.findAll().size());
    }

    @Test
    void testDeletePerson() throws Exception {
        var person = new Person();
        person.setFirstName("Max");
        person.setLastName("Mustermann");
        person.setBirthday(LocalDate.now());

        person = personRepository.save(person);

        var response = this.mvc.perform(
                delete("/persons/" + person.getId())
                        .contentType("application/json")
        ).andReturn().getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
        assertTrue(personRepository.findAll().isEmpty());
    }

    // TODO: Add more test cases here

    @Test
    public void testAddParent() throws Exception {
        var parent = new Person();
        parent.setFirstName("Max");
        parent.setLastName("Mustermann");
        parent.setBirthday(LocalDate.now());

        parent = personRepository.save(parent);

        var child = new Person();
        child.setFirstName("Max");
        child.setLastName("Mustermann");
        child.setBirthday(LocalDate.now());

        child = personRepository.save(child);


        String parentJson = objectMapper.writeValueAsString(parent);

        mvc.perform(MockMvcRequestBuilders.put("/api/persons/{personId}/parents", child.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parentJson))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Person updatedChild = personRepository.findById(child.getId()).get();
        assertEquals(child.getId(), updatedChild.getId());
        assertEquals(2, personRepository.findAll().size());
    }

    @Test
    public void testAddThreeParents() throws Exception {
        var person1 = new Person();
        person1.setFirstName("Max");
        person1.setLastName("Mustermann");
        person1.setBirthday(LocalDate.now());

        person1 = personRepository.save(person1);

        var person2 = new Person();
        person2.setFirstName("Max");
        person2.setLastName("Mustermann");
        person2.setBirthday(LocalDate.now());

        person2 = personRepository.save(person2);

        var person3 = new Person();
        person3.setFirstName("Max");
        person3.setLastName("Mustermann");
        person3.setBirthday(LocalDate.now());

        person3 = personRepository.save(person3);

        var child = new Person();
        child.setFirstName("Max");
        child.setLastName("Mustermann");
        child.setBirthday(LocalDate.now());

        child = personRepository.save(child);

        String parentJson1 = objectMapper.writeValueAsString(person1);
        String parentJson2 = objectMapper.writeValueAsString(person2);
        String parentJson3 = objectMapper.writeValueAsString(person3);

        // Add first parent
        mvc.perform(MockMvcRequestBuilders.put("/api/persons/{personId}/parents", child.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parentJson1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(child.getId()));

        // Add second parent
        mvc.perform(MockMvcRequestBuilders.put("/api/persons/{personId}/parents", child.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parentJson2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(child.getId()));

        // Attempt to add third parent, expect 400 error
        mvc.perform(MockMvcRequestBuilders.put("/api/persons/{personId}/parents", child.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(parentJson3))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // Verify that child has correct parents
        Person updatedChild = personRepository.findById(child.getId()).get();
        assertTrue(updatedChild.getParents().contains(person1));
        assertEquals(4, personRepository.findAll().size());
    }

}
