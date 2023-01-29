package com.marcosp.minhasfinancas.api.resources;

import com.marcosp.minhasfinancas.api.dto.LancamentoDTO;
import com.marcosp.minhasfinancas.exception.RegraNegocioException;
import com.marcosp.minhasfinancas.model.entity.Lancamento;
import com.marcosp.minhasfinancas.model.entity.Usuario;
import com.marcosp.minhasfinancas.model.enums.StatusLancamento;
import com.marcosp.minhasfinancas.model.enums.TipoLancamento;
import com.marcosp.minhasfinancas.service.LancamentoService;
import com.marcosp.minhasfinancas.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/lancamentos")
public class LancamentoResource {

    private LancamentoService service;
    private UsuarioService usuarioService;

    public LancamentoResource(LancamentoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO dto){
        try{
            Lancamento entidade = converter(dto);
            entidade = service.salvar(entidade);
            return new ResponseEntity(entidade, HttpStatus.CREATED);
        }catch(RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO dto){
        return service.obterPorId(id).map(entity -> {
            try {
                Lancamento lancamento = converter(dto);
                lancamento.setId(entity.getId());
                service.atualizar(lancamento);
                return ResponseEntity.ok(lancamento);
            }catch (RegraNegocioException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Lancamento nao encontrado na base de Dados.", HttpStatus.BAD_REQUEST));
    }

    public Lancamento converter(LancamentoDTO dto){
        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setAno(dto.getAno());
        lancamento.setMes(dto.getMes());
        lancamento.setValor(dto.getValor());

        Usuario usuario = usuarioService.obterPorId(dto.getUsuario()).orElseThrow( () -> new RegraNegocioException("Usuario nao encontrado para o id informado"));
        lancamento.setUsuario(usuario);
        lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
        lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));

        return lancamento;

    }
}
