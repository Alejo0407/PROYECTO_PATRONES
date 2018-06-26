<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
 
<div class="row">
        <div class="col-sm-2">
            <div class="form-group">
                <div class="input-group date" id="datetimepicker4" data-target-input="nearest">
                    <input id = "fecha" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker4"/>
                    <div class="input-group-append" data-target="#datetimepicker4" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
            </div>
        </div>
        <div class='col-sm-1'>
        	<button onclick="msGenerarTabla()">generar</button>
		</div>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker4').datetimepicker({
                    format: 'L'
                });
                
            });
        </script>
</div>

    
<div class = 'row' id = 'monitoreo-tabla'>
	
</div>