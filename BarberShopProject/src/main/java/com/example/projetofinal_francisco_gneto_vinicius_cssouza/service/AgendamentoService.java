package com.example.projetofinal_francisco_gneto_vinicius_cssouza.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Agendamento;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Barbearia;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.entity.Servico;
import com.example.projetofinal_francisco_gneto_vinicius_cssouza.repository.AgendamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * AgendamentoService
 */
@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    public Boolean salvarAgendamento(Agendamento agendamento, BarbeariaService barbeariaService){
        double total = 0;
        List<Agendamento> agendamentos = repository.getAllAgendamentosAtivos();
        Barbearia barbearia = barbeariaService.getBarbearia();
        LocalDateTime aux;

        agendamento.setBarbearia(barbearia);

        //verifica se não conflita com a data e horários atuais
        if(agendamento.getHorario().isBefore(LocalDateTime.now()))
            return false;
        //verifica se não conflita com o horário de abertura e fechamento
        if(agendamento.getHorario().getHour() < barbearia.gethAbre().getHour())
            return false;
        if(barbearia.gethAbre().getHour() == agendamento.getHorario().getHour())
            if(agendamento.getHorario().getMinute() < barbearia.gethAbre().getMinute())
                return false;
        if(barbearia.gethAbre().getHour() < agendamento.getHorario().getHour()){
            aux = agendamento.getHorario().plusMinutes(agendamento.getTotalMinutosServicos()+5);
            if(aux.getHour() > barbearia.gethFecha().getHour())
                return false;
            else if(aux.getHour() == barbearia.gethFecha().getHour())
                if(aux.getMinute() > barbearia.gethFecha().getMinute())
                    return false;
            }
        //verifica se não conflita com os demais horários
        for(Agendamento a: agendamentos) {
            if(agendamento.getFuncionario().equals(a.getFuncionario()) || agendamento.getCliente().equals(a.getCliente())){
                if(a.getHorario().equals(agendamento.getHorario()) && a.getId() != agendamento.getId()) {
                    return false;
                }
                if(a.getHorario().getYear() == agendamento.getHorario().getYear()) {
                    if(a.getHorario().getMonthValue() == agendamento.getHorario().getMonthValue()) {
                        if(a.getHorario().getDayOfMonth() == agendamento.getHorario().getDayOfMonth()) {                       
                            if(a.getHorario().isBefore(agendamento.getHorario())) {//Verifica se o horário já marcado se encontra antes se sim, adiciona mais o tempo de duração do serviço, se o horário que quer agendar for antes do horário de finalização não deixa
                                aux = a.getHorario().plusMinutes(a.getTotalMinutosServicos() + 5);//Horário de finalização do serviço + 5 minutos de tolerância
                                if(agendamento.getHorario().isBefore(aux) && a.getId() != agendamento.getId())
                                    return false;                         
                            }
                            else{//Se o horário já marcado se encontra depois, adiciona o tempo de serviço ao horário que deseja agendar, se o horário de finalização for maior que o horário já marcado, não deixa   
                                aux = agendamento.getHorario().plusMinutes(agendamento.getTotalMinutosServicos() + 5);//Horário de duração do serviço + 5 minutos de tolerância
                                if(aux.isAfter(a.getHorario()) && a.getId() != agendamento.getId())
                                    return false;
                            }
                        }
                    }
                }
            }
        }
        for(Servico s : agendamento.getServicos()) {//Soma o valor total dos serviços
            total+=s.getPreco();
        }
        agendamento.setTotal(total);
        repository.save(agendamento);
        return true;
    }

    public List<Agendamento> getAgendamentos(){
        return repository.findAll();
    }

    public Agendamento getAgendamentoById (int id){
        return repository.findById(id).get();
    }

    public Page<Agendamento> findAll (int page, int n){
        return repository.findAll(PageRequest.of(page, n));
    }


    public Page<Agendamento> findAllOrderByHorarioAtivos(int page, int n){

        return repository.findAllAgendamentosAtivos(PageRequest.of(page, n));
    }
    
    public Page<Agendamento> findAllOrderByHorariosFinalizados(int page, int n){

        return repository.getAgendamentosHistorico(PageRequest.of(page, n));
    }

    public List<Agendamento> getAgendamentosAtivosOrderByHorarios(){

        return repository.findAllAgendamentosAtivos();
    }
    
    public List<Agendamento> getAgendamentosFinalizadosOrderByHorarios(){

        return repository.getAgendamentosHistorico();
    }

    public List<Agendamento> getAgendamentosAtivosOrderByHorariosByCliente(int ID_CLIENTE){

        return repository.findAllAgendamentosAtivosByCliente(ID_CLIENTE);
    }
    
    public List<Agendamento> getAgendamentosFinalizadosOrderByHorariosByCliente(int ID_CLIENTE){

        return repository.getAgendamentosHistoricoByCliente(ID_CLIENTE);
    }

    public List<Agendamento> getAgendamentosAtivosOrderByHorariosByFuncionario(int ID_FUNCIONARIO){

        return repository.findAllAgendamentosAtivosByFuncionario(ID_FUNCIONARIO);
    }
    
    public List<Agendamento> getAgendamentosFinalizadosOrderByHorariosByFuncionario(int ID_FUNCIONARIO){

        return repository.getAgendamentosHistoricoByFuncionario(ID_FUNCIONARIO);
    }

    public List<Agendamento> getAgendamentosAtivosOrderByHorariosByServico(int ID_SERVICO){

        return repository.findAllAgendamentosAtivosByServico(ID_SERVICO);
    }
    
    public List<Agendamento> getAgendamentosFinalizadosOrderByHorariosByServico(int ID_SERVICO){

        return repository.getAgendamentosHistoricoByServico(ID_SERVICO);
    }

    public Double getValorTotalAgendamentos (){

        Double valorTotal = 0.0;

        List<Agendamento> agendamentos = repository.getAgendamentosHistorico();

        for(Agendamento ag: agendamentos)
        {
            valorTotal += ag.getTotal();
        }

        return valorTotal;
    }

    public void deleteAgendamento (int id){
        repository.deleteById(id);
    }
    
}
