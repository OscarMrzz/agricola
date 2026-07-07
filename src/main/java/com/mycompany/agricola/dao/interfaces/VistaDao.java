package com.mycompany.agricola.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.VistaEntity;

public interface VistaDao {

    List<VistaEntity> getAll();

    VistaEntity getById(int id);

    ResultadoPersistencia create(VistaEntity vista);

    ResultadoPersistencia update(VistaEntity vista);

    ResultadoPersistencia delete(int id);
}
