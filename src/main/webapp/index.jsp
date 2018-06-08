<%@ page language='java' contentType='text/html; charset=UTF-8'
    pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html lang='es'>
    <head>
        <title>Facturación Electrónica</title>
        <meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>
        <script type='text/javascript' src = 'webjars/jquery/3.3.1-1/jquery.min.js'></script>
        
        <link rel='stylesheet' type='text/css' href='webjars/bootstrap/4.1.0/css/bootstrap.min.css'>
        <link rel='stylesheet' type='text/css' href='css/index-style.css'>
        
        <script type='text/javascript' src='webjars/bootstrap/4.1.0/js/bootstrap.min.js'></script>
		<script type='text/javascript' src='js/index-script.js' ></script>
        <script type='text/javascript' src='js/contenidoPrincipal-script.js'></script>

    </head>
    <body>
        <!-- Loggin del sistema -->
        <div id='logginPanel' class='container'>
            
            <div class='row'>
                <div class='absolute-center is-responsive'>
                    <div id='logginPanel-logo'></div>
                    <div class='col-sm-12 col-md-10 col-md-offset-1'>
                        <form method ='POST' id = 'logginPanel-logginForm'>
                            <fieldset>
                            <div class='form-group input-group'>
                                <span class='input-group-addon'><i class='glyphicon glyphicon-user'></i></span>
                                <input class='form-control' type='text' name='username' placeholder='username'/>          
                            </div>
                            <div class='form-group input-group'>
                                <span class='input-group-addon'><i class='glyphicon glyphicon-lock'></i></span>
                                <input class='form-control' type='password' name='password' placeholder='password'/>     
                            </div>
                            <div class='form-group'>
                                <input type='submit' class='btn btn-def btn-block' value = 'Login'></input>
                            </div>
                            <div class='form-group text-center'>
                                <a href='#'>¿Olvidó la contraseña?</a>&nbsp;|&nbsp;<a href='#'>Soporte</a>
                            </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>



    </body>
</html>