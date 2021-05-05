/**
 * 
 */

$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text=='Editar'){
            $.get(href, function(agendamento, status){
                $('.editForm #id').val(agendamento.id);
                $('.editForm #cliente').val(agendamento.cliente.id + ' - ' + agendamento.cliente.nome);
                $('.editForm #funcionario').val(agendamento.funcionario.id + ' - ' + agendamento.funcionario.nome);
                $('.editForm #servicos').val('');
                $('.editForm #horario').val('');
            });

            $('.editForm #editModal').modal();
        }else{
            $('.editForm #cliente').val('');
            $('.editForm #funcionario').val('');
            $('.editForm #servicos').val('');
            $('.editForm #horario').val('');

            $('.newForm #newModal').modal();
        }
    });

    $('.table .delBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');

        $('#deleteModal #delRef').attr('href', href);

        $('#deleteModal').modal();
    });

    $('select').selectpicker();
 });