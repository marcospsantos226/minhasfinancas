package com.marcosp.minhasfinancas.api.resources;

import com.marcosp.minhasfinancas.api.dto.LancamentoDTO;
import com.marcosp.minhasfinancas.exception.RegraNegocioException;
import com.marcosp.minhasfinancas.model.entity.Lancamento;
import com.marcosp.minhasfinancas.model.entity.Usuario;
import com.marcosp.minhasfinancas.model.enums.StatusLancamento;
import com.marcosp.minhasfinancas.model.enums.TipoLancamento;
import com.marcosp.minhasfinancas.service.LancamentoService;
import com.marcosp.minhasfinancas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    }

    public Lancamento converter(LancamentoDTO dto){
        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setAno(dto.getAno());
        lancamento.setMes(dto.getMes());
        lancamento.setValor(dto.getValor());

        Usuario usuario = usuarioService.ObterPorId(dto.getUsuario()).orElseThrow( () -> new RegraNegocioException("Usuario nao encontrado para o id informado"));
        lancamento.setUsuario(usuario);
        lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
        lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));

        return lancamento;

    }
}
