package com.ijb.beneficiarios_api.services;

import java.util.List;

public abstract class AbstractService<Entity, DTO, Id> {

    public abstract Entity create(DTO dto);

    public abstract Entity update(DTO dto);

    public abstract Entity getById(Id id);

    public abstract List<Entity> getAll();

    public abstract Entity deleteById(Id id);
}
