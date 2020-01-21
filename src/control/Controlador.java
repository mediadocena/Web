/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dep.DAOFactory;
import Dep.Departamento;
import Dep.DepartamentoDAO;


/**
 *
 * @author mjesus
 */
@WebServlet("/")
public class Controlador extends HttpServlet {    
       DAOFactory bd = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
       DepartamentoDAO depDAO = bd.getDepartamentoDAO();
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
     
        // se obtiene la acción a realizar
        String op = request.getParameter("accion");
        System.out.println(op);

        // pantalla de alta de departamento
        if (op.equals("alta")) {
            response.sendRedirect("/Web/vista/alta.jsp");
        }

        // se inserta departamento en la tabla
        if (op.equals("insertar")) {
            pantalla.Departamentos dep = (pantalla.Departamentos) request.getAttribute("depart");// obtenerlos

            Departamento departamento = new Departamento(dep.getDeptno(), dep.getDnombre(), dep.getLoc());
            boolean insertar = depDAO.InsertarDep(departamento);
            String mensaje = "";
            if (insertar) {
                mensaje = "Departamento " + dep.getDeptno() + " insertado";
            } else {
                mensaje = "Error al insertar Departamento " + dep.getDeptno();
            }

            request.setAttribute("mensaje", mensaje); //se envía mensaje al jsp
            RequestDispatcher rd
                    = request.getRequestDispatcher("/vista/DepartamentoInsertado.jsp");
            rd.forward(request, response);

        }
        
        if(op.equals("eliminar")) {
        	//obtener datos del formulario
        	depDAO.EliminarDep(Integer.parseInt(request.getParameter("id")));
    		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    		dispatcher.forward(request, response);
        	
        }
        // se obtienen los datos de los departamentos para visualizarlos
        if (op.equals("listado")) {
            ArrayList lista = depDAO.ObtenerDepartamentos();
            request.setAttribute("departamentos", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/vista/listado.jsp");
            rd.forward(request, response);
        }
        if(op.equals("modificar")) {
        	ArrayList lista = depDAO.ObtenerDepartamentos();
            request.setAttribute("departamentos", lista);
            RequestDispatcher rd = request.getRequestDispatcher("/vista/Modificar.jsp");
            rd.forward(request, response);
        	
        }
        if(op.equals("modificacion")) {
        	try {
				showEditar(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        if (op.equals("Modific")) {
        	try {
				editar(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
       

    }
    private void editar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		Departamento dpto = new Departamento(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"), request.getParameter("localidad"));
		System.out.println(dpto.getLoc());
		depDAO.ModificarDep(dpto.getDeptno(), dpto);
		String mensaje = "Departamento " + dpto.getDeptno() + " modificado";
		request.setAttribute("mensaje", mensaje);
		RequestDispatcher rd = request.getRequestDispatcher("/vista/DepModificado.jsp");
		rd.forward(request, response);
	}
    private void showEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		Departamento dpto = depDAO.ConsultarDep(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("dpto", dpto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/vista/Modificacion.jsp");
		dispatcher.forward(request, response);
	}
   
     public void destroy() {            
            bd = null;
        }
}