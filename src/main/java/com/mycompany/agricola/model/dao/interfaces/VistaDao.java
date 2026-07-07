package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.VistaEntity;

public interface VistaDao {

    List<VistaEntity> getAll();

    VistaEntity getById(int id);

    ResultadoPersistencia create(VistaEntity vista);

    ResultadoPersistencia update(VistaEntity vista);

    ResultadoPersistencia delete(int id);
}
