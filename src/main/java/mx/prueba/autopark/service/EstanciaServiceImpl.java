package mx.prueba.autopark.service;

import lombok.extern.slf4j.Slf4j;
import mx.prueba.autopark.domain.Estancia;
import mx.prueba.autopark.repository.EstanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EstanciaServiceImpl implements EstanciaService{

    @Autowired
    private  EstanciaRepository repository;

    @Override
    public Estancia saveEstancia(Estancia Estancia) {
        log.info("Saving  Estancia {} ",Estancia);
        return repository.save(Estancia);
    }

    @Override
    public void removeEstancia(Long id) {
        log.info("Removing  Estancia {} ",id);
        Optional<Estancia> Estancia=repository.findById(id);
        repository.delete(Estancia.get());
    }

    @Override
    public Optional<Estancia> findById(Long id) {
        log.info("Looking for {}",id);
        return repository.findById(id);
    }

    @Override
    public Optional<Estancia> findEstanciasByAuto(String placa) {
        log.info("Looking for Estancia by placa {}",placa);
        return repository.findEstanciasByAuto(placa);
    }

    @Override
    public List<Estancia> getEstancias() {
        log.info("Fetching all Estancias");
        return repository.findAll();
    }
}
