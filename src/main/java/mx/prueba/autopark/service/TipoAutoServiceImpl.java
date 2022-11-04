package mx.prueba.autopark.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.prueba.autopark.domain.TipoAuto;
import mx.prueba.autopark.repository.TipoAutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TipoAutoServiceImpl implements TipoAutoService{

    @Autowired
    private  TipoAutoRepository repository;

    @Override
    public TipoAuto saveTipoAuto(TipoAuto tipoAuto) {
        log.info("Saving Tipo Auto {} ",tipoAuto);
        return repository.save(tipoAuto);
    }

    @Override
    public void removeTipoAuto(Long id) {
        log.info("Removing Tipo Auto {} ",id);
        Optional<TipoAuto> tipoAuto=repository.findById(id);
        repository.delete(tipoAuto.get());
    }

    @Override
    public Optional<TipoAuto> findById(Long id) {
        log.info("Looking for {}",id);
        return repository.findById(id);
    }

    @Override
    public TipoAuto findByTipo(String tipo) {
        log.info("Looking for {}",tipo);
        return repository.findByTipo(tipo);
    }


    @Override
    public List<TipoAuto> getTipoAutos() {
        log.info("Fetching all tipoAutos");
        return repository.findAll();
    }
}
