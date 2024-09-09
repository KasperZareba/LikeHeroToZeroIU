package com.project.Co2emission.repository;

import com.project.Co2emission.repository.entity.Editor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EditorRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createEditor(Editor editor) {
        em.persist(editor);
    }
}
