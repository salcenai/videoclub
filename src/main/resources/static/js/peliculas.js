function buscarPeliculas(pagina = 0){

    var busqueda = $("#txtBusqueda").val();

    $.ajax({
        url : "/pelicula/buscarPeliculas",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
            pagina: pagina,
        },
        contentType: "application/json",
        dataType: "json",
        success : function (data, status) {
            console.log(data);

            impresionPeliculas(data.peliculas);
            impresionEncontradas(data.totalPeliculas);
            impresionPaginacion(data.paginaActual, data.totalPaginas, "buscarPeliculas");

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });

}

function buscarPeliculasAvanzado(pagina = 0){

    var busqueda = $("#txtBusqueda").val();
    var pais = $("#selPais").val();
    var genero = $("#selGenero").val();
    var anioDesde = $("#numAnioDesde").val();
    var anioHasta = $("#numAnioHasta").val();

    console.log(busqueda);
    console.log(pais);
    console.log(genero);
    console.log(anioDesde);
    console.log(anioHasta);

    $.ajax({
        url : "/pelicula/buscarPeliculasAvanzado",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
            codigoPais: pais,
            codigoGenero: genero,
            anioDesde: anioDesde,
            anioHasta: anioHasta,
            pagina: pagina,
        },
        contentType: "application/json",
        dataType: "json",
        success : function (data, status) {
            console.log(data);

            impresionPeliculas(data);

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });

}

function nuevaPelicula(){

    var titulo = $("#txtTitulo").val();
    var tituloCompacto = $("#txtTituloCompacto").val();
    var pais = $("#selPais").val();
    var anio = $("#numAnio").val();
    var duracion = $("#numDuracion").val();
    var sinopsis = $("#txtSinopsis").val();
    var generos = [];
    $('#divGeneros > button').each(function(){
        generos.push($(this).text());
    });
    var json = JSON.stringify({
            titulo: titulo,
            tituloCompacto: tituloCompacto,
            codigoPais: pais,
            anio: anio,
            duracion: duracion,
            sinopsis: sinopsis,
            lstGeneros: generos,
    });

    $.ajax({
        url : "/pelicula/nueva",
        type : "POST",
        async: true,
        data : json,
        contentType: "application/json",
        dataType: "text",
        success : function (data, status) {
            console.log(data);

            $('#lblMensaje').text("");

            alert("Correcto");

            window.location.href = window.location.href

        },
        error : function (xhr, status, error) {
            console.log(status);

            $('#lblMensaje').text(xhr.responseText);

        }
    });

}

function buscarPeliculasVistas(pagina = 0){

    var busqueda = $("#txtBusqueda").val();
    var codigoTipoEstado = "VISTA";

    $.ajax({
        url : "/pelicula/buscarPeliculasPorTipoEstado",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
            codigoTipoEstado: codigoTipoEstado,
            pagina: pagina,
        },
        contentType: "application/json",
        dataType: "json",
        success : function (data, status) {
            console.log(data);

            impresionPeliculas(data.peliculas, $("#idPeliculasVistas"));
//            impresionEncontradas(data.totalPeliculas, $("#idPeliculasVistasEncontradas"));
            impresionPaginacion(data.paginaActual, data.totalPaginas, "buscarPeliculasVistas", $("#idPeliculasVistasPaginacion"));

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });
}

function buscarPeliculasPendientes(pagina = 0){

    var busqueda = $("#txtBusqueda").val();
    var codigoTipoEstado = "PENDIENTE";

    $.ajax({
        url : "/pelicula/buscarPeliculasPorTipoEstado",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
            codigoTipoEstado: codigoTipoEstado,
            pagina: pagina,
        },
        contentType: "application/json",
        dataType: "json",
        success : function (data, status) {
            console.log(data);

            impresionPeliculas(data.peliculas, $("#idPeliculasPendientes"));
//            impresionEncontradas(data.totalPeliculas, $("#idPeliculasPendientesEncontradas"));
            impresionPaginacion(data.paginaActual, data.totalPaginas, "buscarPeliculasPendientes", $("#idPeliculasPendientesPaginacion"));

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });

}

function buscarPeliculasPorGenero(pagina = 0){

    var busqueda = $("#txtBusqueda").val();

    $.ajax({
        url : "/genero/buscarPeliculasPorGenero",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
            codigoGenero: codigoGenero,
            pagina: pagina,
        },
        contentType: "application/json",
        dataType: "json",
        success : function (data, status) {
            console.log(data);

            impresionPeliculas(data.peliculas);
            impresionEncontradas(data.totalPeliculas);
            impresionPaginacion(data.paginaActual, data.totalPaginas, "buscarPeliculasPorGenero");

        },
        error : function (xhr, status, error) {
            console.log(status);
        }
    });

}

function impresionPeliculas(data, contenedorPeliculas = $("#idPeliculas")){

    var peliculas = "";

    $.each(data, function(i, obj) {
        peliculas = peliculas + "<div class='col-md-2 p-1'>";
        peliculas = peliculas + "<a href='/pelicula/" + obj.id + "'>";
        peliculas = peliculas + "<img class='card-img' src='/images/portada/portada_" + obj.tituloCompacto + ".jpg' alt='" + obj.titulo + "'>";
        peliculas = peliculas + "<div class='card-img-overlay m-0 p-1 pt-4 text-white'>";

        peliculas = peliculas + "<div class='p-1 m-0' style='background-color: rgba(20, 23, 25, 0.75);'>";
        peliculas = peliculas + "<span>" + obj.titulo + "</span>";
        peliculas = peliculas + "</div>";

        peliculas = peliculas + "<div class='row p-1 m-0 justify-content-around' style='background-color: rgba(20, 23, 25, 0.75);'>";
        peliculas = peliculas + "<div><small>" + obj.anio + "</small></div>";
        if(obj.puntuacionMedia != null){
            peliculas = peliculas + "<div>";
            if(obj.puntuacionMedia >= 70){
                peliculas = peliculas + "<small class='px-1 rounded bg-success'>" + obj.puntuacionMedia + "</small>";
            } else if(obj.puntuacionMedia >= 50){
                peliculas = peliculas + "<small class='px-1 rounded bg-warning'>" + obj.puntuacionMedia + "</small>";
            } else{
                peliculas = peliculas + "<small class='px-1 rounded bg-danger'>" + obj.puntuacionMedia + "</small>";
            }
            if(obj.puntuacionPersonal != null){
                peliculas = peliculas + "&nbsp;<small class='px-1 rounded bg-primary'>" + obj.puntuacionPersonal + "</small>";
            }
            peliculas = peliculas + "</div>";
        }
        peliculas = peliculas + "</div>";

        peliculas = peliculas + "</div>";
        peliculas = peliculas + "</a>";
        peliculas = peliculas + "</div>";
    });

    contenedorPeliculas.html(peliculas);
}

function impresionEncontradas(totalPeliculas, contenedorEncontradas = $("#idPeliculasEncontradas")){

    if(totalPeliculas == 0){
        contenedorEncontradas.html("<span>No se han encontrado peliculas</span>");
    } else {
        contenedorEncontradas.html("<span>Se han encontrado " + totalPeliculas + " peliculas</span>");
    }
}

function impresionPaginacion(paginaActual, totalPaginas, funcion, contenedorPaginacion = $("#idPeliculasPaginacion")){

    var paginacion = "";
    for(var i = 0; i < totalPaginas; i++){
        if(i == paginaActual){
            paginacion = paginacion + "<li class='page-item active' onclick='" + funcion + "(" + i + ")'><a class='page-link' href='#'>" + (i + 1) + "</a></li>";
        } else {
            paginacion = paginacion + "<li class='page-item' onclick='" + funcion + "(" + i + ")'><a class='page-link' href='#'>" + (i + 1) + "</a></li>";
        }
    }
    contenedorPaginacion.html(paginacion);
}