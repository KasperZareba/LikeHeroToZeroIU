package com.project.Co2emission.service;

import com.project.Co2emission.repository.EditorRepository;
import com.project.Co2emission.repository.EmissionRepositoryClass;
import com.project.Co2emission.repository.entity.Editor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddEditorService {

    @Autowired
    EditorRepository editorRepository;

    private List<Editor> editors = new ArrayList<>();

    public void save(Editor editor) {
        editorRepository.createEditor(editor);
    }

    public List<Editor> findAll() {
        return editors;
    }
}
