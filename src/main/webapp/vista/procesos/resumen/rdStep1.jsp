<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>

<div class="col-3">
		<div class="input-group date" id="datetimepicker4" data-target-input="nearest">
	    	<label class="form-check-label" for="fecha">
			    Fecha: *
			</label>
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