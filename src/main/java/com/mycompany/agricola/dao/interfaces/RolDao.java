package com.mycompany.agricola.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.util.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.RolEntity;

public interface RolDao {

    List<RolEntity> getAll();

    RolEntity getById(int id);

    ResultadoPersistencia create(RolEntity rol);

    ResultadoPersistencia update(RolEntity rol);

    ResultadoPersistencia delete(int id);
}
