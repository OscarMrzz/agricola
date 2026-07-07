package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.PermisoAccionEntity;

public interface PermisoAccionDao {

    List<PermisoAccionEntity> getAll();

    List<PermisoAccionEntity> getByRol(int idRol);

    ResultadoPersistencia create(PermisoAccionEntity permiso);

    ResultadoPersistencia update(PermisoAccionEntity permiso);

    ResultadoPersistencia delete(int id);
}
