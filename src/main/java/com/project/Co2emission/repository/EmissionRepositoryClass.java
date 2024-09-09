package com.project.Co2emission.repository;

import com.project.Co2emission.repository.entity.Emission;
import com.project.Co2emission.repository.entity.EmissionToCheck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class EmissionRepositoryClass {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createEmission(Emission emission) {
        em.persist(emission);
    }

    @Transactional
    public Emission getEmissionById(Long id) {
        EmissionToCheck emissionToCheck = em.createQuery("SELECT e FROM EmissionToCheck e WHERE e.id=:id", EmissionToCheck.class)
                .setParameter("id", id).getSingleResult();

        Emission emission = new Emission(emissionToCheck.getCountryCode(),
                emissionToCheck.getAverageEmissionValue(), emissionToCheck.getLocalDate());

        return emission;
    }

    public List<Emission> findAll() {
        TypedQuery<Emission> query = em.createQuery("SELECT e FROM Emission e", Emission.class);
        return query.getResultList();
    }
}
