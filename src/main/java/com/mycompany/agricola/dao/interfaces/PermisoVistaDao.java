package com.mycompany.agricola.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.PermisoVistaEntity;

public interface PermisoVistaDao {

    List<PermisoVistaEntity> getAll();

    List<PermisoVistaEntity> getByRol(int idRol);

    ResultadoPersistencia create(PermisoVistaEntity permiso);

    ResultadoPersistencia update(PermisoVistaEntity permiso);

    ResultadoPersistencia delete(int id);
}
