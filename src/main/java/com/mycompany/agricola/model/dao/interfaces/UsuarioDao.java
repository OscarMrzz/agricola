package com.mycompany.agricola.model.dao.interfaces;

import java.util.List;
import com.mycompany.agricola.model.dao.resultados.ResultadoPersistencia;
import com.mycompany.agricola.model.entity.UsuarioEntity;

public interface UsuarioDao {

    List<UsuarioEntity> getAll();

    UsuarioEntity getById(int id);

    UsuarioEntity getByNombre(String nombreUser);

    ResultadoPersistencia create(UsuarioEntity usuario);

    ResultadoPersistencia update(UsuarioEntity usuario);

    ResultadoPersistencia delete(int id);
}
