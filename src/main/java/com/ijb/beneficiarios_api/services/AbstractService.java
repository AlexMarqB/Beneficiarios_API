package com.ijb.beneficiarios_api.services;

import java.util.List;

public abstract class AbstractService<Entity, CreateDTO, DTO, Id> {

    public abstract Entity create(CreateDTO dto);

    public abstract Entity update(DTO dto);

    public abstract Entity getById(Id id);

    public abstract List<Entity> getAll();

    public abstract boolean deleteById(Id id);
}
