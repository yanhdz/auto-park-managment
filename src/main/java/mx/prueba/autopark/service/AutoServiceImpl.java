package mx.prueba.autopark.service;

import lombok.extern.slf4j.Slf4j;
import mx.prueba.autopark.domain.Auto;
import mx.prueba.autopark.domain.TipoAuto;
import mx.prueba.autopark.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class AutoServiceImpl implements AutoService{

    @Autowired
    private  AutoRepository repository;

    @Override
    public Auto saveAuto(Auto Auto) {
        log.info("Saving Tipo Auto {} ",Auto);
        return repository.save(Auto);
    }

    @Override
    public void removeAuto(Long id) {
        log.info("Removing Tipo Auto {} ",id);
        Optional<Auto> Auto=repository.findById(id);
        repository.delete(Auto.get());
    }

    @Override
    public Optional<Auto> findById(Long id) {
        log.info("Looking for {}",id);
        return repository.findById(id);
    }

    @Override
    public Optional<Auto> findAutoByPlaca(String placa) {
        log.info("Looking for Placa {}",placa);
        return repository.findAutoByPlaca(placa);
    }

    @Override
    public List<Auto> getAutos() {
        log.info("Fetching all Autos");
        return repository.findAll();
    }

    @Override
    public List<Auto> findAllByTipo(TipoAuto id) {
        log.info("Fetching all Autos by TipoAuto");
        return repository.findAllByFkTipoAuto(id);
    }


}
