package com.marcosp.minhasfinancas.service.impl;

import com.marcosp.minhasfinancas.model.entity.Lancamento;
import com.marcosp.minhasfinancas.model.enums.StatusLancamento;
import com.marcosp.minhasfinancas.model.repository.LancamentoRepository;
import com.marcosp.minhasfinancas.service.LancamentoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private LancamentoRepository repository;

    public LancamentoServiceImpl(LancamentoRepository repository){
        this.repository = repository;
    }

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {


        return repository.save(lancamento);
    }

    @Override
    public Lancamento atualizar(Lancamento lancamento) {
        return null;
    }

    @Override
    public void deletar(Lancamento lancamento) {

    }

    @Override
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        return null;
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {

    }
}
