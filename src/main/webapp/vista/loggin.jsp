<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>

        <!-- Loggin del sistema -->
        <div id='logginPanel' class='container'>
            
            <div class='row'>
                <div class='absolute-center is-responsive'>
                    <div id='logginPanel-logo'></div>
                    <div class='col-sm-12 col-md-10 col-md-offset-1'>
                        <fieldset>
                        <div class='form-group input-group'>
                        	<span class='input-group-addon'><i class='glyphicon glyphicon-user'></i></span>
                            <input id = 'form-usr' class='form-control' type='text' name='username' placeholder='username'/>          
						</div>
                        <div class='form-group input-group'>
                        	<span class='input-group-addon'><i class='glyphicon glyphicon-lock'></i></span>
                            <input id= 'form-pass' class='form-control' type='password' name='password' placeholder='password'/>     
						</div>
                        <div class='form-group'>
                        	<button onclick = 'loggin()' class='btn btn-def btn-block' >Login</button>
                        </div>
                        <div class='form-group text-center'>
                        	<a href='#'>¿Olvidó la contraseña?</a>&nbsp;|&nbsp;<a href='#'>Soporte</a>
                        </div>
                        </fieldset>
                    </div>
                </div>
            </div>
        </div>