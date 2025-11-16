package com.algaworks.algafood.domain.exception;

public class ErrorMessages {

    public static final String VALIDATION_ERROR_COZINHA_EM_USO = "Cozinha de código %s não pode ser removida, pois está em uso.";
    public static final String VALIDATION_ERROR_COZINHA_NAO_ENCONTRADA = "Cozinha de código %s não encontrada.";
    public static final String VALIDATION_ERROR_COZINHA_NAO_ENCONTRADA_POR_NOME = "Nenhuma cozinha encontrada com o nome informado.";

    public static final String VALIDATION_ERROR_CIDADE_EM_USO = "Cidade de id %s não pode ser removida, pois está em uso";
    public static final String VALIDATION_ERROR_CIDADE_NAO_ENCONTRADA = "Cidade de código %s não encontrada.";

    public static final String VALIDATION_ERROR_ESTADO_EM_USO = "Estado de id %s não pode ser removido, pois está em uso";
    public static final String VALIDATION_ERROR_ESTADO_NAO_ENCONTRADO = "Estado de id %s não encontrado";

    public static final String VALIDATION_ERROR_RESTAURANTE_NAO_ENCONTRADO = "Restaurante com ID %s não encontrado";
}
