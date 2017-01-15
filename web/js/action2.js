/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

fillAssistences();
function fillAssistences(){
    $.ajax({
        dataType: "json",
        url: "getAsistencias",
        //data: data,        
        success: function(data){           
            
            var table = $("#table-assistences tbody");
            table.empty();
            $.each(data, function(key, value){                
                table.append("<tr><td>"+value.ci+"</td><td>"+value.name+"</td><td>"+value.last_name+"</td><td>"+value.conference_name+"</td><td>"+value.email+"</td><td>" + "<a href='#' onclick='return false;'><span data-id='" + value.id + "' class='delete glyphicon glyphicon-remove' aria-hidden='true' title='Eliminar'></span></a></td></tr>");
                
            });
            
            updateDeleteAction();
    }});    
}

fillConferences();
function fillConferences(){    
    
    $.ajax({
        dataType: "json",
        url: "Conferencias2",
        //data: data,        
        success: function(data){            
            
            $('#conference').empty();
            $.each(data, function(key, value){                
                $('#conference').append(
                    '<option value="' + value.id + '">' + value.name + '</option>'
                );
                
            });
            
    }});    
}

function updateDeleteAction(){
    $('.delete').click(function(){

        var id = $(this).data("id");
        clearFields();
        $('#id').val(id);
        $('#modalConfirmDelete').modal('show');
    });
}

$('#btn-create').click(function(){
    clearFields();
    fillAssistences();
    fillConferences();
    
    $("#modalConference").modal('show');
});

function clearFields(){
    $('#ci').val('');
    $('#name').val('');
    $('#last_name').val('');
    $('#conference').val('');
    $('#email').val('');
}

$('#form-add').submit(function(e) {

    e. preventDefault();
    var url = "createAssistance";

    $.ajax({
           type: "POST",
           url: url,
           data: $("#form-add").serialize(), // serializes the form's elements.
           success: function(data){
                $("#modalConference").modal('hide');
                console.log(data);
                fillAssistences();
                fillConferences();
           }
    });
});


$('#btn-confirm').click(function(){
    var url = "deleteAssistance";
    var id = $('#id').val();
    
    $.ajax({
           type: "POST",
           url: url,
           data: {'id':id}, // serializes the form's elements.
           success: function(data){
               
                console.log(data);
                fillAssistences();
                fillConferences();
           }
    });
});
