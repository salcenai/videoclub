function buscarPeliculas(nPagina = 0){

    var busqueda = $("#txtBusqueda").val();

    $.ajax({
        url : "/pelicula/buscarPeliculas",
        type : "GET",
        async: true,
        data : {
            busqueda: busqueda,
            nPagina: nPagina,
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

function buscarPeliculasAvanzado(nPagina = 0){

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
            nPagina: nPagina,
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
    var generos = new Array();
    $('#selGeneros option').each(function(){
        generos.push($(this).val());
    });
    var subgeneros = new Array();
    $('#selSubgeneros option').each(function(){
        subgeneros.push($(this).val());
    });

    var json = JSON.stringify({
            titulo: titulo,
            tituloCompacto: tituloCompacto,
            codigoPais: pais,
            anio: anio,
            duracion: duracion,
            sinopsis: sinopsis,
            lstCodigosGeneros: generos,
            lstCodigosSubgeneros: subgeneros,
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

function impresionPeliculas(data){

    if(data.totalPeliculas == 0){
        $('#idPeliculasEncontradas').html("<span>No se han encontrado peliculas</span>");
    } else {
        $('#idPeliculasEncontradas').html("<span>Se han encontrado " + data.totalPeliculas + " peliculas</span>");
    }

    var peliculas = "";

    $.each(data.peliculas, function(i, obj) {

        peliculas = peliculas + "<div class='col-md-2 p-1'>";

        peliculas = peliculas + "<img class='card-img' src='images/portada/portada_" + obj.tituloCompacto + ".jpg' alt='" + obj.titulo + "'>";

        peliculas = peliculas + "<div class='card-img-overlay m-0 p-1 pt-4'>";
        peliculas = peliculas + "<div class='text-white p-2' style='background-color: rgba(20, 23, 25, 0.75);'>";
        peliculas = peliculas + "<a href='/pelicula/" + obj.id + "'><h6>" + obj.titulo + "</h6></a>";
        peliculas = peliculas + "<h7>" + obj.anio + "</h7>";
        peliculas = peliculas + "</div>";
        peliculas = peliculas + "</div>";

        peliculas = peliculas + "</div>";

    });

    $('#idPeliculas').html(peliculas);

    var paginas = "";

    for(var i = 0; i < data.totalPaginas; i++){

        if(i == data.actualPagina){
            paginas = paginas + "<li class='page-item active' onclick='buscarPeliculas(" + i + ")'><a class='page-link' href='#'>" + (i + 1) + "</a></li>";
        } else {
            paginas = paginas + "<li class='page-item' onclick='buscarPeliculas(" + i + ")'><a class='page-link' href='#'>" + (i + 1) + "</a></li>";
        }

    }

    $('#idPeliculasPaginacion').html(paginas);

}