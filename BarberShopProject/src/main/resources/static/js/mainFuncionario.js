/**
 * 
 */

$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text=='Editar'){
            $.get(href, function(funcionario, status){
                $('.editForm #id').val(funcionario.id);
                $('.editForm #nome').val(funcionario.nome);
                $('.editForm #celular').val(funcionario.celular);
                $('.editForm #salario').val(funcionario.salario);
            });

            $('.editForm #editModal').modal();
        }else{
            $('.editForm #nome').val('');
            $('.editForm #celular').val('');
            $('.editForm #salario').val('');

            $('.newForm #newModal').modal();
        }
    });

    $('.table .delBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');

        $('#deleteModal #delRef').attr('href', href);

        $('#deleteModal').modal();
    });
 });