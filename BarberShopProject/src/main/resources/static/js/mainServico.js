/**
 * 
 */

$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text=='Editar'){
            $.get(href, function(servico, status){
                $('.editForm #id').val(servico.id);
                $('.editForm #nome').val(servico.nome);
                $('.editForm #preco').val(servico.preco);
                $('.editForm #duracao').val(servico.duracao);
            });

            $('.editForm #editModal').modal();
        }else{
            $('.editForm #nome').val('');
            $('.editForm #preco').val('');
            $('.editForm #duracao').val('');

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