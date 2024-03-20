package com.example.ApiRemedios.Remedio.Services;

import com.example.ApiRemedios.Remedio.DTO.DadosAtualizarRemedio;
import com.example.ApiRemedios.Remedio.DTO.DadosCadastroRemedio;
import com.example.ApiRemedios.Remedio.DTO.DadosDetalhamentoRemedio;
import com.example.ApiRemedios.Remedio.DTO.DadosListagemRemedios;
import com.example.ApiRemedios.Remedio.Entities.Remedio;
import com.example.ApiRemedios.Remedio.Repository.RemediosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ServicesRemedio {

    @Autowired
    private final RemediosRepository repository;

    public ServicesRemedio(RemediosRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRemedio dados) {
        repository.save(new Remedio(dados));

        return ResponseEntity.ok(dados);
    }

    public ResponseEntity<List<DadosListagemRemedios>> listar() {
        var lista = repository.findAllByAtivoTrue().stream().map(DadosListagemRemedios::new).toList();

        return ResponseEntity.ok(lista);
    }

    public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
        var remedio = repository.getReferenceById(dados.id());
        remedio.atualizarInfomacao(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    public ResponseEntity<Void> reativar(@PathVariable Long id){
        Optional<Remedio> remedio = repository.findById(id);
        remedio.ifPresent(r -> {
            r.reativar();
            repository.save(r);
        });
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> excluir(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> inativar(@PathVariable Long id){
        var remedio = repository.getReferenceById(id);
        remedio.inativar();

        return ResponseEntity.noContent().build();
    }
}