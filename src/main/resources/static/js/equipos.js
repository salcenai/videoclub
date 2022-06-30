function buscarEquipo(busqueda, contenedor){

    $.ajax({
        url : "/equipo/buscarEquipo",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
        },
        contentType: "application/json",
        dataType: "json",
        success : function (data, status) {

            var equipos = "";

            $.each(data, function(i, obj) {
                equipos = equipos + formarBoton(obj);
            });

            contenedor.html(equipos);

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });

}