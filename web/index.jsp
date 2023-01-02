<%-- 
    Document   : index
    Created on : 14 de nov. de 2021, 23:41:58
    Author     : KawaN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br" >
<head>
  <meta charset="UTF-8">
  <title>ADEGA</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="css/estilos.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="log-form">
  <h2>Faça login na sua conta</h2>
   <FORM ACTION="UsuariosServlet" style="center"> 
    <input type="text" name="login" placeholder="Login" />
    <input type="password" name="senha" placeholder="Senha" />
    <button name="oper" value="Logar" type="submit" class="btn">Entrar</button>
   
    <font size="2.5px"color="red">  <%if(request.getAttribute("erro") != null){out.print(request.getAttribute("erro"));}%></font>
    
    
    <a onclick="document.getElementById('id01').style.display='block'" class="forgot" href="#">Esqueceu a senha?</a>
  </FORM>
</div><!--end log form -->
<!-- partial -->
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  
  
  <div class="w3-container"> 
 

  <div id="id01" class="w3-modal">
    <div class="w3-modal-content">
      <div class="w3-container">
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
        <p><font color="red">Entrar em contato com o Administrador do Sistema!</font></p>
        
      </div>
    </div>
  </div>
</div>
  
  
</body>
</html>
