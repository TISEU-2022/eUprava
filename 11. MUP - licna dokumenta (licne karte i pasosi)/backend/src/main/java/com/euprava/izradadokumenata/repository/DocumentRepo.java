package com.euprava.izradadokumenata.repository;

import com.euprava.izradadokumenata.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentRepo extends JpaRepository<Document, UUID> {
}
