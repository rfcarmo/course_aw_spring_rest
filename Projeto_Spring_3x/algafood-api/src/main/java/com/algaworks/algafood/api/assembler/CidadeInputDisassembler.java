package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.dto.input.CidadeInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CidadeInputDisassembler {

    private final ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput cidadeInput) {
        return modelMapper.map(cidadeInput, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
        modelMapper.map(cidadeInput, cidade);
    }

}
