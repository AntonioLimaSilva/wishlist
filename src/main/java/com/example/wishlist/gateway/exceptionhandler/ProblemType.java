package com.example.wishlist.gateway.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    RECURSO_JA_EXISTENTE("/recurso-nao-encontrado", "Recurso já existente"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),

    ERRO_ATUALIZAR_BASE_DADOS("/recurso-nao-encontrado", "Erro ao atualizar base de dados");

    private final String title;
    private final String uri;

    ProblemType(String path, String title) {
        this.uri = "https://wishlist.com.br" + path;
        this.title = title;
    }
}
