function cambiarEstadoPelicula(){

    var codigoTipoEstado = $("#selTipoEstado").val();
    var puntuacion = $("#numPuntuacion").val();
    var critica = $("#txtCritica").val();

    console.log(idPelicula + ", " + codigoTipoEstado + ", " + puntuacion + ", " + critica);

    var json = JSON.stringify({
            idPelicula: idPelicula,
            codigoTipoEstado: codigoTipoEstado,
            puntuacion: puntuacion,
            critica: critica,
    });

    $.ajax({
        url : "/estado/nuevo",
        type : "PUT",
        async: true,
        data : json,
        contentType: "application/json",
        dataType: "text",
        success : function (data, status) {
            console.log(data);

            location.reload();

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });

}