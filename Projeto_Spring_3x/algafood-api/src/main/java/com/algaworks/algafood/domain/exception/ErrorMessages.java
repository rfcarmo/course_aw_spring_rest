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

    public static final String VALIDATION_ERROR_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %s não pode ser removida, pois está em uso.";
    public static final String VALIDATION_ERROR_FORMA_PAGAMENTO_NAO_ENCONTRADA = "Forma de pagamento de código %s não encontrada.";

    public static final String VALIDATION_ERROR_GRUPO_EM_USO = "Grupo com ID %s não pode ser removido, pois está em uso";
    public static final String VALIDATION_ERROR_GRUPO_NAO_ENCONTRADO = "Grupo com ID %s não encontrado";

    public static final String VALIDATION_ERROR_USUARIO_EM_USO = "Usuário com ID %s não pode ser removido, pois está em uso";
    public static final String VALIDATION_ERROR_USUARIO_NAO_ENCONTRADO = "Usuário com ID %s não encontrado";

    public static final String VALIDATION_ERROR_PRODUTO_NAO_ENCONTRADO = "Produto com ID %s não encontrado para o restaurante de ID %s";

    public static final String VALIDATION_ERROR_PERMISSAO_NAO_ENCONTRADA = "Permissão com ID %s não encontrada";

    public static final String VALIDATION_ERROR_PEDIDO_NAO_ENCONTRADO = "Pedido com ID %s não encontrado";

    public static final String MSG_ERROR_DADOS_INVALIDOS = "Um ou mais dados estão inválidos. Corrija e tente novamente.";

    public static final String MSG_ERROR_SENHA_NAO_COINCIDE = "Senha atual informada não coincide com a senha do usuário.";

    public static final String MSG_ERROR_EMAIL_JA_CADASTRADO = "Já existe um usuário cadastrado com o e-mail %s.";
}
