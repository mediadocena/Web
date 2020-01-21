<html>
    <head>        
        <title>MODIFICACION DE DEPARTAMENTOS</title>
    </head>
    <body>
        <br>
        <h1 align="center">MODIFICACION DE DEPARTAMENTO</h1>
        <%
            String mensaje = (String) request.getAttribute("mensaje");
        %>
        <p align="center"><font color="red">
                <%=mensaje%></font></p>
        <p align='center'>
            <a href="Controlador?accion=modificar">Modificacion de departamento</a></p>  
    <p align="center">
        <a href="index.jsp">Inicio</a> </p>
</body>
</html>