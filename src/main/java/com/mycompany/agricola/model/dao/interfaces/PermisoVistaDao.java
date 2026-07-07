package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.PermisoVistaEntity;

public interface PermisoVistaDao {

    List<PermisoVistaEntity> getAll();

    List<PermisoVistaEntity> getByRol(int idRol);

    ResultadoPersistencia create(PermisoVistaEntity permiso);

    ResultadoPersistencia update(PermisoVistaEntity permiso);

    ResultadoPersistencia delete(int id);
}
