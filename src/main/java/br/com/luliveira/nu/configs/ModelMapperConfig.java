package br.com.luliveira.nu.configs;

import br.com.luliveira.nu.controllers.dto.CadastroForm;
import br.com.luliveira.nu.entities.PedidoConta;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapperConfig(){
        var modelMapper = new ModelMapper();
        modelMapper.typeMap(CadastroForm.class, PedidoConta.class).addMappings(
                mapper -> {
                    mapper.map(CadastroForm::getLogradouro, (dest, value) -> dest.getEndereco().setLogradouro((String) value));
                    mapper.map(CadastroForm::getNumero, (dest, value) -> dest.getEndereco().setNumero((String) value));
                    mapper.map(CadastroForm::getBairro, (dest, value) -> dest.getEndereco().setBairro((String) value));
                    mapper.map(CadastroForm::getCep,    (dest, value) -> dest.getEndereco().setCep((String) value));
                    mapper.map(CadastroForm::getCidade, (dest, value) -> dest.getEndereco().setCidade((String) value));
                    mapper.map(CadastroForm::getEstado, (dest, value) -> dest.getEndereco().setEstado((String) value));
                });
        return modelMapper;
    }
}
