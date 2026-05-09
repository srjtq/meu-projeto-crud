package com.exemplo.crud.controller;

import com.exemplo.crud.dto.VendaRequestDTO;
import com.exemplo.crud.model.Venda;
import com.exemplo.crud.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public ResponseEntity<Venda> realizarVenda(
            @RequestBody VendaRequestDTO dto) {

        Venda venda = vendaService.realizarVenda(dto);
        return ResponseEntity.ok(venda);
    }
}
