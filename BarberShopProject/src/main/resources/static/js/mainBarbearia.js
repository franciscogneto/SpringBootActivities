/**
 * 
 */

$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text=='Editar'){
            $.get(href, function(barbearia, status){
                $('.editForm #id').val(barbearia.id);
                $('.editForm #nome').val(barbearia.nome);
                $('.editForm #endereco').val(barbearia.endereco);
                $('.editForm #telefone').val(barbearia.telefone);
                $('.editForm #hAbre').val(barbearia.hAbre.replace(':00', ''));
                $('.editForm #hFecha').val(barbearia.hFecha.replace(':00', ''));
            });

            $('.editForm #editModal').modal();
        }else{
            $('.editForm #nome').val('');
            $('.editForm #endereco').val('');
            $('.editForm #telefone').val('');
            $('.editForm #telefone').val('');
            $('.editForm #hAbre').val('');
            $('.editForm #hFecha').val('');

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