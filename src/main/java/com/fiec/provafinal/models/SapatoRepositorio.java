package com.fiec.provafinal.models;

import jakarta.persistence.EntityManager;

public class SapatoRepositorio extends GenericRepositorioImpl<sapato, String> {

    public SapatoRepositorio(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    Class<sapato> getMyClass() {
        return sapato.class;
    }
}
