<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<div class = 'row border-left'>
	<div class = 'col-2 '>
		<div class="row border-bottom">
	        <div class="form-group">
	        	<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
	            	<input id = "fecha" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker4"/>
	                <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
						<div class="input-group-text"><i class="fa fa-calendar"></i></div>
	       			</div>
	    		</div>
			</div>
	        <script type="text/javascript">
	            $(function () {
	                $('#datetimepicker4').datetimepicker({
	                    format: 'L'
	                });
	                
	            });
	        </script>
		</div>
		
		<br>
		<br>
		
		<div class="custom-control custom-checkbox">
		  <input type="checkbox" class="custom-control-input" id="ch-factura" value = ''>
		  <label class="custom-control-label" for="ch-factura">Facturas</label>
		</div>
		
		<div class="custom-control custom-checkbox">
		  <input type="checkbox" class="custom-control-input" id="ch-boleta">
		  <label class="custom-control-label" for="ch-boleta">Boletas</label>
		</div>
		
		<div class="custom-control custom-checkbox">
		  <input type="checkbox" class="custom-control-input" id="ch-corregido">
		  <label class="custom-control-label" for="ch-corregido">Corregido</label>
		</div>
		
		<br>
		<br>
		
		<div class="form-group row " >
			<br>
			
      		<div class="col-sm-6">
        		<button type="submit" class="btn btn-dark" onclick='prMigrar()'>Migrar</button>
     	 	</div>
     	 	<div class="col-sm-6">
        		<button type="submit" class="btn btn-dark" onclick='prGenerar()'>Generar</button>
     	 	</div>
    	</div>
	</div>
	<div class = 'col-10 border-left border-right' id = 'tabla-generacion'>
		
	</div>
</div>