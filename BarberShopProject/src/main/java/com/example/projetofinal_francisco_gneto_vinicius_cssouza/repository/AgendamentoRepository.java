package com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository;



import java.util.List;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Agendamento;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * AgendamentoRepository
 */
public interface AgendamentoRepository extends JpaRepository<Agendamento,Integer> {
    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO > CURRENT_TIMESTAMP ORDER BY HORARIO", nativeQuery = true)
    public Page<Agendamento> findAllAgendamentosAtivos(Pageable pageable);

    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO <= CURRENT_TIMESTAMP ORDER BY HORARIO", nativeQuery = true)
    public Page<Agendamento> getAgendamentosHistorico(Pageable pageable);

    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO > CURRENT_TIMESTAMP ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> findAllAgendamentosAtivos();

    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO <= CURRENT_TIMESTAMP ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> getAgendamentosHistorico();

    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO > CURRENT_TIMESTAMP", nativeQuery = true)
    public List<Agendamento> getAllAgendamentosAtivos();

    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO > CURRENT_TIMESTAMP AND ID_CLIENTE = ?1 ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> findAllAgendamentosAtivosByCliente(Integer ID_CLIENTE);

    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO <= CURRENT_TIMESTAMP AND ID_CLIENTE = ?1 ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> getAgendamentosHistoricoByCliente(Integer ID_CLIENTE);
    
    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO > CURRENT_TIMESTAMP AND ID_FUNCIONARIO = ?1 ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> findAllAgendamentosAtivosByFuncionario(Integer ID_FUNCIONARIO);

    @Query(value = "SELECT * FROM AGENDAMENTO WHERE HORARIO <= CURRENT_TIMESTAMP AND ID_FUNCIONARIO = ?1 ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> getAgendamentosHistoricoByFuncionario(Integer ID_FUNCIONARIO);

    @Query(value = "SELECT * FROM AGENDAMENTO A, SERVICO_AGENDAMENTO S WHERE  A.ID = S.AGENDAMENTO_ID AND S.SERVICO_ID = ?1 AND HORARIO > CURRENT_TIMESTAMP ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> findAllAgendamentosAtivosByServico(Integer ID_SERVICO);

    @Query(value = "SELECT * FROM AGENDAMENTO A, SERVICO_AGENDAMENTO S WHERE  A.ID = S.AGENDAMENTO_ID AND S.SERVICO_ID = ?1 AND HORARIO <= CURRENT_TIMESTAMP ORDER BY HORARIO", nativeQuery = true)
    public List<Agendamento> getAgendamentosHistoricoByServico(Integer ID_SERVICO);
}