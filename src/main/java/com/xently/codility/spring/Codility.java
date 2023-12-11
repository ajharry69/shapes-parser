package com.xently.codility.spring;

/*import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Entity
class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Pet> pets;

    public String getFirstName() {
        return firstName;
    }

    public Set<Pet> getPets() {
        return this.pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}

@Entity
class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;

    public String getOwnerName() {
        return owner.getFirstName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getOwner() {
        return owner;
    }
}

class PersonNotFoundException extends RuntimeException {

}

@Service
class PersonService {

    @PersistenceContext
    private final EntityManager entityManager;

    PersonService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void addPet(Long personId, String petName) {
        Person owner = entityManager.find(Person.class, personId);
        if (owner == null) {
            throw new PersonNotFoundException();
        }
        Pet pet = new Pet();
        pet.setName(petName);
        pet.setOwner(owner);
        owner.getPets().add(pet);
        this.entityManager.persist(owner);
    }
}*/
