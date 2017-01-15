/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).off('.datepicker.data-api');

$('.edit').click(function(){
    var id = $(this).data("id");
    fillFields(id);
    $('#action').val('update');
    $('#id').val(id);    
    $("#modalConference").modal('show');
});

$('.delete').click(function(){
    
    var id = $(this).data("id");
    clearFields();
    $('#action').val('delete');
    $('#id').val(id);    
    
    $("#confirm-delete").modal('show');    
});

$('#btn-confirm').click(function(){
    $('#form').submit();
});

$('#btn-create').click(function(){
    clearFields();
    $('#action').val('create');
    $("#modalConference").modal('show');
});

function clearFields(){
    $('#name').val('');
    $('#date').val('');
    $('#description').val('');
}

function fillFields(id){
    var name = $('#name-' + id).val();
    var date = $('#date-' + id).val()
    var description = $('#description-' + id).val();
    
    $('#name').val(name);
    $('#date').val(date);
    $('#description').val(description);
}

/*$('#datetimepicker').datepicker({
    format: 'dd/mm/yyyy'
}).on('changeDate', function (e){
    $ (this).datepicker ('hide');
});*/
    
$('#datepicker').datepicker();
