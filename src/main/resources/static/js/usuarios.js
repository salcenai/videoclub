function registro(){

    var nombre = $("#txtNombre").val();
    var correo = $("#txtCorreo").val();
    var contrasena = $("#txtContrasena").val();

    var json = JSON.stringify({
        nombre : nombre,
        correo : correo,
        contrasena : contrasena
    });

    $.ajax({
        url : "/usuario/nuevo",
        type : "POST",
        async: true,
        data : json,
        contentType: "application/json",
        dataType: "text",
        success : function (data, status) {

            window.location.replace("/");

        },
        error : function (xhr, status, error) {
            $('#lblMensaje').text(xhr.responseText);
        }
    });

}