

/*
Esto es para que se ejecute esto cuando el navegador ha cargado el html
*/
$(function(){
	$("#note_to_add").keyup(function(e){ 
	    var code = e.which; // recommended to use e.which, it's normalized across browsers
	    if(code==13){
	    	addNote();
	    }
	});
	
	//para que se muestren las notas reales de bbdd
	showNotes(); 
});

function addNote(){

	//extrae el valor del input
	var nueva_nota = $("#note_to_add").val();

	if(nueva_nota != ""){
		//notes_array.push(nueva_nota);

		//showNotes();

		//Aquí se va a llamar al servicio rest addNota
		$.ajax({
			url: "${url_rest_service}/rest/xml/nota/addNota/descripcion/" + nueva_nota,
			type: "GET",
			dataType: "json",
			success: function( data ){
				//Si se ha insertado correctamente
				if(data.result == "true"){
					//se muestran las notas actualizadas
					showNotes();

					//dejamos el input vacío
					$("#note_to_add").val("");
					//ponemos el foco en el input
					$("#note_to_add").focus();
					
				//Si no se ha insertado correctamente
				}else{
					//le enseñamos el mensaje de error al usuario
					//alert("Error al insertar: " + data.msg);
					if(data.exceptionCode == 1062){
						alert("Error al insertar: asegúrese de que la nota no esté repetida");
					}else{
						alert("Error desconocido al insertar (contacte " + 
							"con el administrador): " + data.msg);
					}
					
				}
				
			}
		})

		
	}else{
		alert("No se puede introducir una nota vacía");
	}
}

function showNotes(){

	//Aquí se va a llamar al servicio rest getAllNotas
	$.ajax({
		url: "${url_rest_service}/rest/xml/nota/getAllNotas",
		type: "GET",
		dataType: "json",
		success: function( data ){
			var content = '';

			var notes_array = data.nota;
			for (var i = 0; i < notes_array.length; i++) {

				var content = content + '<article>	'
						+ notes_array[i].descripcion
						+ '	<div class="btn-group icons">'
						+ '		'
						+ '		<a href="#" onclick="editNote('
						+ notes_array[i].id
						+ ', \''
						+ notes_array[i].descripcion
						+ '\');" class="icon_enlace">'
						+			'<img src="./icons/edit-512px.svg" class="icon"></img>'
						+		'</a>'
						+'		'
						+'		<a href="#" onclick="removeNote(\'' 
						+ notes_array[i].id
						+ '\');" class="icon_enlace">'
						+			'<img src="./icons/remove2-512px.svg" class="icon"></img>'
						+		'</a>'
						+	'</div>'
						+'</article>';
	
			};

			$('#notes').html(content);
		}
	})
}

function removeNote(idNoteToRemove){

	//Aquí se va a llamar al servicio rest getAllNotas
	$.ajax({
		url: "${url_rest_service}/" + 
			"rest/xml/nota/deleteNota/id/" + idNoteToRemove,
		type: "GET",
		dataType: "json",
		success: function( data ){
			//Si se ha eliminado correctamente
			if(data.result == "true"){
				showNotes();
			}else{
				alert("Error desconocido al eliminar (contacte " + 
					"con el administrador): " + data.msg);
			}
		}
	})
}

function editNote(id, noteToEdit){
	var editedNote = prompt("Edite la nota: ", noteToEdit);

	//Aquí se va a llamar al servicio rest updateNota
	$.ajax({
		url: "${url_rest_service}/" + 
			"rest/xml/nota/updateNota/id/" + id +
			"/descripcion/" + editedNote,
		type: "GET",
		dataType: "json",
		success: function( data ){
			//Si se ha editado correctamente
			if(data.result == "true"){
				showNotes();
			}else{
				alert("Error desconocido al editar (contacte " + 
					"con el administrador): " + data.msg);
			}
		}
	})
}