package com.exemplo.crud.controller;

import com.exemplo.crud.model.enums.GeneroDisco;
import com.exemplo.crud.model.enums.FormaPagamento;
import com.exemplo.crud.model.enums.StatusVenda;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enums")
@Tag(name = "Enums", description = "Enums disponíveis para o frontend")
public class EnumController {

    @Operation(
            summary = "Listar gêneros de disco",
            description = "Retorna todos os valores possíveis do enum GeneroDisco"
    )
    @GetMapping("/generos")
    public List<String> listarGenerosDisco() {
        return Arrays.stream(GeneroDisco.values())
                .map(Enum::name)
                .toList();
    }

    @Operation(
            summary = "Listar formas de pagamento",
            description = "Retorna todos os valores possíveis do enum FormaPagamento"
    )
    @GetMapping("/formas-pagamento")
    public List<String> listarFormasPagamento() {
        return Arrays.stream(FormaPagamento.values())
                .map(Enum::name)
                .toList();
    }

    @Operation(
            summary = "Listar status de venda",
            description = "Retorna todos os valores possíveis do enum StatusVenda"
    )
    @GetMapping("/status-venda")
    public List<String> listarStatusVenda() {
        return Arrays.stream(StatusVenda.values())
                .map(Enum::name)
                .toList();
    }
}

