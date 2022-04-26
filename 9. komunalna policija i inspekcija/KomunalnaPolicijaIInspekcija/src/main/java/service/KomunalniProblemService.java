package service;

import model.KomunalniProblem;

import java.util.List;
import java.util.UUID;

public interface KomunalniProblemService {

    List<KomunalniProblem> getAll();
    KomunalniProblem getOne(UUID id);
    UUID createKomunalniProblem(KomunalniProblem komunalniProblem);
    KomunalniProblem updateKomunalniProblem(KomunalniProblem komunalniProblem);
    void deleteKomunalniProblem(UUID id);
}
