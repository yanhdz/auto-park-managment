package mx.prueba.autopark.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.prueba.autopark.domain.TipoAuto;
import mx.prueba.autopark.repository.TipoAutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TipoAutoServiceImpl implements TipoAutoService{

    private final TipoAutoRepository repository;

    @Override
    public TipoAuto saveTipoAuto(TipoAuto tipoAuto) {
        return null;
    }

    @Override
    public void removeTipoAuto(Long id) {

    }

    @Override
    public TipoAuto findById(Long id) {
        return null;
    }

    @Override
    public List<TipoAuto> getTipoAutos() {
        log.info("Fetching all tipoAutos");
        return repository.findAll();
    }
}
