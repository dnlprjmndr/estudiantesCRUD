package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;

import dao.EstudianteDAO;
import model.Estudiante;

/**
 * Servlet implementation class EstudianteServlet
 */
@WebServlet(name = "EstudianteServlet", urlPatterns = { "/estudiante" })
public class EstudianteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EstudianteDAO estudianteDAO = new EstudianteDAO();

	public EstudianteServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("buscarAction");
		System.out.println("action: " + action);
		if (action != null) {
			if (action.equals("buscarPorId")) {
				buscarPorId(request, response);
			} else if (action.equals("buscarPorNombre")) {
				buscarPorNombre(request, response);
			} else if (action.equals("nuevo")) {
				nuevoEstudiante(request, response);
			}
		} else {
			List<Estudiante> resultMongo = estudianteDAO.obtenerEstudiantesMongo();
			//List<Estudiante> result = estudianteDAO.obtenerEstudiantes();
			mostrarListaEstudiantes(request, response, resultMongo);
		}
	}

	private void buscarPorId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idEstudiante = Integer.valueOf(req.getParameter("idEstudiante"));
		Estudiante estudiante = null;
		try {
			estudiante = estudianteDAO.obtenerPorId(idEstudiante);
		} catch (Exception ex) {
			Logger.getLogger(EstudianteServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		req.setAttribute("estudiante", estudiante);
		req.setAttribute("action", "actualizar");
		String paginaJSP = "/vista/nuevo-estudiante.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJSP);
		dispatcher.forward(req, resp);
	}

	private void nuevoEstudiante(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("action", "");
		String nombre = req.getParameter("nombre");
		String alias = req.getParameter("alias");
		String apellidos = req.getParameter("apellidos");
		String fnacimiento = req.getParameter("fnacimiento");
		String telefono = req.getParameter("telefono");
		String email = req.getParameter("email");
		
		Estudiante estudiante = new Estudiante(nombre, alias, apellidos,fnacimiento,telefono,email);
		EstudianteDAO estudDao = new EstudianteDAO();
		estudDao.saveMongo(estudiante);
		String paginaJSP = "/vista/nuevo-estudiante.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJSP);
		dispatcher.forward(req, resp);
	}

	private void buscarPorNombre(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nombreEstudiante = req.getParameter("nombreEstudiante");
		List<Estudiante> result = estudianteDAO.buscarPorNombre(nombreEstudiante);
		mostrarListaEstudiantes(req, resp, result);
	}

	private void mostrarListaEstudiantes(HttpServletRequest req, HttpServletResponse resp, List listaEstudiantes)
			throws ServletException, IOException {
		String paginaJsp = "/vista/lista-estudiantes.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaJsp);
		req.setAttribute("listaEstudiantes", listaEstudiantes);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("Do Post action: " + action);
		System.out.println();
		if (action.equals("guardar")) {
			guardarEstudiante(req, resp);
		} else if (action.equals("actualizar")) {
			actualizarEstudiante(req, resp);
		} else if (action.equals("eliminar")) {
			eliminarEstudiante(req, resp);
		}
	}

	private void guardarEstudiante(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String alias = req.getParameter("alias");
		String apellidos = req.getParameter("apellidos");
		String fnacimiento = req.getParameter("fnacimiento");
		String telefono = req.getParameter("telefono");
		String email = req.getParameter("email");

		Estudiante estudiante = new Estudiante(nombre, alias, apellidos, fnacimiento, telefono, email);
		long idEstudiante = estudianteDAO.guardarEstudiante(estudiante);
		List<Estudiante> listaEstudiantes = estudianteDAO.obtenerEstudiantes();
		req.setAttribute("idEstudiante", idEstudiante);
		String message = "Resgistro creado satisfactoriamente.";
		req.setAttribute("message", message);
		mostrarListaEstudiantes(req, resp, listaEstudiantes);
	}

	private void actualizarEstudiante(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String alias = req.getParameter("alias");
		String apellidos = req.getParameter("apellidos");
		String fnacimiento = req.getParameter("fnacimiento");
		String telefono = req.getParameter("telefono");
		String email = req.getParameter("email");
		int idEstudiante = Integer.valueOf(req.getParameter("idEstudiante"));
		
		Estudiante estudiante = new Estudiante(nombre, alias, apellidos, fnacimiento, telefono, email);
		estudiante.setId(idEstudiante);
		
		boolean success = estudianteDAO.actualizarEstudiante(estudiante);
		String message = null;
		if (success) {
			message = "Registro actualizado satisfactoriamente.";
		}
		List<Estudiante> listaEstudiantes = estudianteDAO.obtenerEstudiantes();
		req.setAttribute("idEstudiante", idEstudiante);
		req.setAttribute("message", message);
		mostrarListaEstudiantes(req, resp, listaEstudiantes);
	}

	private void eliminarEstudiante(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		long idEstudiante = Integer.valueOf(req.getParameter("idEstudiante"));
		boolean confirmar = estudianteDAO.eliminarEstudiante(idEstudiante);
		if (confirmar) {
			String message = "Registro eliminado satisfactoriamente.";
			req.setAttribute("message", message);
		}
		List<Estudiante> listaEstudiantes = estudianteDAO.obtenerEstudiantes();
		mostrarListaEstudiantes(req, resp, listaEstudiantes);
	}

}