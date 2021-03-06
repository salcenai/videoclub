function buscarGeneros(){

    var busqueda = $("#txtGenero").val();

    $.ajax({
        url : "/genero/buscarGeneros",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
        },
        contentType: "application/json",
        dataType: "json",
        success : function (data, status) {

            var generos = "";

            $.each(data, function(i, obj) {
                generos = generos + formarBoton(obj);
            });

            $("#divGenerosCandidatos").html(generos);

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });

}

function formarBoton(nombre){

    return "<button type='button' onclick='intercambiar(this)' class='btn btn-outline-primary'>" + nombre + "</button>";
}