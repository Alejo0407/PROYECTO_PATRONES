<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>

<div id='accordion'>
  
  <div class='card'>
    <div class='card-header bg-dark text-light' id='headingOne'>
      <h5 class='mb-0'>
        <button class='btn bg-dark text-light btn-block text-left' 
        	data-toggle='collapse' data-target='#navBajas' 
        	aria-expanded='true' aria-controls='collapseOne'>
          Lotes
        </button>
      </h5>
    </div>

    <div id='navLotes' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>
      <div class='card-body'>
      	<nav class='nav flex-column'>
      		<a class='nav-link' href='#'>Generador en Lotes</a>
      	</nav>
      </div>
    </div>
  </div>

  <div class='card'>
    <div class='card-header bg-dark text-light' id='headingOne'>
      <h5 class='mb-0'>
        <button class='btn bg-dark text-light btn-block text-left' 
        	data-toggle='collapse' data-target='#navDiarios' 
        	aria-expanded='true' aria-controls='collapseOne'>
          Diarios
        </button>
      </h5>
    </div>

    <div id='navDiarios' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>
      <div class='card-body'>
      	<nav class='nav flex-column'>
      		<a class='nav-link' href='#'>Resumen Diario</a>
      		<a class='nav-link' href='#'>Estado de Resumen Diario</a>
      		<a class='nav-link' href='#'>Estado de Resumen de Bajas</a>
      	</nav>
      </div>
    </div>
  </div>


  <div class='card'>
    <div class='card-header bg-dark text-light' id='headingOne'>
      <h5 class='mb-0'>
        <button class='btn bg-dark text-light btn-block text-left' 
        	data-toggle='collapse' data-target='#navBajas' 
        	aria-expanded='true' aria-controls='collapseOne'>
          Bajas
        </button>
      </h5>
    </div>

    <div id='navBajas' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>
      <div class='card-body'>
      	<nav class='nav flex-column'>
      		<a class='nav-link' href='#'>Anular Documento</a>
      		<a class='nav-link' href='#'>Anular Documento (Error de Sistema)</a>
      	</nav>
      </div>
    </div>
  </div>


  <div class='card'>
    <div class='card-header bg-dark text-light' id='headingOne'>
      <h5 class='mb-0'>
        <button class='btn bg-dark text-light btn-block text-left' 
        	data-toggle='collapse' data-target='#navEmergencia' aria-expanded='true' aria-controls='collapseOne'>
          Emergencia
        </button>
      </h5>
    </div>

    <div id='navEmergencia' class='collapse' aria-labelledby='headingOne' data-parent='#accordion'>
      <div class='card-body'>
      	<nav class='nav flex-column'>
      		<a class='nav-link' href='#'>Reenvio de Documentos</a>
      	</nav>
      </div>
    </div>
  </div>

 </div>