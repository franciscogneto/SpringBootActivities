/**
 * 
 */

$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text=='Editar'){
            $.get(href, function(cliente, status){
                $('.editForm #id').val(cliente.id);
                $('.editForm #nome').val(cliente.nome);
                $('.editForm #celular').val(cliente.celular);
                $('.editForm #email').val(cliente.email);
            });

            $('.editForm #editModal').modal();
        }else{
            $('.editForm #nome').val('');
            $('.editForm #celular').val('');
            $('.editForm #email').val('');

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