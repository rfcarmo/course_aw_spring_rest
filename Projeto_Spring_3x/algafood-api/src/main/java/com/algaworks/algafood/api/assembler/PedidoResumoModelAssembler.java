package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.dto.output.PedidoResumoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoResumoModelAssembler {

    private final ModelMapper modelMapper;

    public PedidoResumoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoModel.class);
    }

    public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toModel)
                .toList();
    }

}
