var today = new Date();
var day = today.getDay();
var daylist = ["Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"];
var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
var dateTime = date+' '+time;

//No se si vaya a agarrar el Jquery en esta parte pero por eso lleva el puro
//var accionSeleccionada = $('#accionPersona').val();
//var salonSeleccionado = $('#salon').val();
var seleccionAccion = document.getElementById("accionPersona");
var accionSeleccionada = seleccionAccion.options[seleccionAccion.selectedIndex].value;

var seleccionSalon = document.getElementById("salon");
var salonSeleccionado = seleccionSalon.options[seleccionSalon.selectedIndex].value;

var btnGuardar = document.getElementById("btnGuardar");
btnGuardar.addEventListener("click", () => {
    axios.post("http://localhost:4567/registrarNota", {
        id: 0,
        nombre : document.getElementById("nombrePersona").value,
        //nombre : $("nombrePersona").val(),
        matricula : document.getElementById("matriculaPersona").value,
        accion : accionSeleccionada,
        fechahora : dateTime,
        salon : salonSeleccionado,
        nota : document.getElementById("nota").value,
    })
    .then(function(res){
        console.log("Estado: "+ res.data.status);
    })
    .catch(function (error){
        console.log(error)
    })
});