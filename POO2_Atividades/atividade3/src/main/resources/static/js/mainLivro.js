/**
 * 
 */

$(document).ready(function(){

    $('.nBtn, .table .eBtn').on('click', function(event){

        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();

        if(text=='Edit'){
            $.get(href, function(livro, status){
                $('.editForm #id').val(livro.id);
                $('.editForm #nome').val(livro.nome);
                $('.editForm #editora').val(livro.editora.nome);
            });

            $('.editForm #editModal').modal();
        }else{
            $('.newForm #nome').val('');

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