package com.project.Co2emission.repository;

import com.project.Co2emission.repository.entity.EmissionToCheck;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmissionToCheckRepository {

    @PersistenceContext
    private EntityManager em;

    public List<EmissionToCheck> findAll() {
        TypedQuery<EmissionToCheck> query = em.createQuery("SELECT e FROM EmissionToCheck e", EmissionToCheck.class);
        return query.getResultList();
    }

    @Transactional
    public void createEmissionToCheck(EmissionToCheck emission) {
        em.persist(emission);
    }

    @Transactional
    public void deleteById(Long id) {
        EmissionToCheck emission = em.find(EmissionToCheck.class, id);
        if (emission != null) {
            em.remove(emission);
        }
    }
}
