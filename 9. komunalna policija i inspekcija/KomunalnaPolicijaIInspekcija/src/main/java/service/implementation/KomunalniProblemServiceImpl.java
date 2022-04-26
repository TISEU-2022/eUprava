package service.implementation;

import model.KomunalniProblem;
import org.springframework.beans.factory.annotation.Autowired;
import repository.KomunalniProblemRepository;
import service.KomunalniProblemService;

import java.util.List;
import java.util.UUID;

public class KomunalniProblemServiceImpl implements KomunalniProblemService {

    @Autowired
    private KomunalniProblemRepository komunalniProblemRepository;

    @Override
    public List<KomunalniProblem> getAll() {
        return komunalniProblemRepository.findAll();
    }

    @Override
    public KomunalniProblem getOne(UUID id) {
        return komunalniProblemRepository.findById(id);
    }

    @Override
    public UUID createKomunalniProblem(KomunalniProblem komunalniProblem) {
        return null;
    }

    @Override
    public KomunalniProblem updateKomunalniProblem(KomunalniProblem komunalniProblem) {
        return null;
    }

    @Override
    public void deleteKomunalniProblem(UUID id) {

    }
}
