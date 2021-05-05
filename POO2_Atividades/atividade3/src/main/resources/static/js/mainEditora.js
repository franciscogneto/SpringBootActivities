/**
 * 
 */

$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text=='Edit'){
            $.get(href, function(editora, status){
                $('.editForm #id').val(editora.id);
                $('.editForm #nome').val(editora.nome);
                $('.editForm #email').val(editora.email);
            });

            $('.editForm #editModal').modal();
        }else{
            $('.newForm #nome').val('');
            $('.newForm #email').val('');

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