$(document).ready(function(){
    $('#txtDni').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
    $('#txtRecibo').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9]/);
        if (verified) {
            e.preventDefault();
        }
    }); 
    
    $('#txtReciboDetalle').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9]/);
        if (verified) {
            e.preventDefault();
        }
    });    
    
    $('#txtNRecibo').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9]/);
        if (verified) {
            e.preventDefault();
        }
    }); 
    
    $('#txtMontoPension').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9.]/);
        if (verified) {
            e.preventDefault();
        }
    }); 

    $('#txtMoraPension').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9.]/);
        if (verified) {
            e.preventDefault();
        }
    }); 
    
    $('#txtTotalPension').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9.]/);
        if (verified) {
            e.preventDefault();
        }
    });     
    
    $('.classRegAlumno').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    $('.classRegAlumno').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });    
    
    $('.classRegPago').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    $('.classRegAlumnoProc').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    $('.classRegAlumnoAp').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    $('.classRegAlumnoAlternativo').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    $('#txtOtroAa').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    //Reporte asistencia 
    // mayuscula
    $('#txtApNom').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    //Registro de profesores
    $('#txtCodigo').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
    $('.classRegProfesor').keypress(function(e){
        $(this).css('text-transform','uppercase');        
    });
    
    // Registro de Horarios
    
    $('input[type="text"][name="EntradaProfesor"]').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9:]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
    $('input[type="text"][name="SalidaProfesor"]').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9:]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
    $('.salidaMasivo').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9:]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
    $('.ingresoMasivo').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9:]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
    $('.salidaDias').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9:]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
    $('.entradaDias').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9:]/);
        if (verified) {
            e.preventDefault();
        }
    });
    // REGISTRO DE NOTAS
    $('#tblAlumnoNotas tr td > input').keypress(function(e) {
        var verified = (e.which == 8 || e.which == undefined || e.which == 0) ? null : String.fromCharCode(e.which).match(/[^0-9:]/);
        if (verified) {
            e.preventDefault();
        }
    });
    
});


