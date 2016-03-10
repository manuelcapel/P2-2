package comunicacion;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DBUsuario;
import modelo.Usuario;
/*********
 * 
 * @author M.Capel: clase correspondiente al servlet que recibe las peticiones "post" y "get"
 *
 */
public class ListaCorreosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 	Función que solicita información a un recurso específico.
	 * 	Se actualizará la lista de usuarios en una llamada get.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Usuario> listaUsuarios = DBUsuario.listarUsuarios();
		
		request.setAttribute("usuarios", listaUsuarios);
		request.setAttribute("buscado", null);		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	/**
	 * Función que recibe datos para ser procesados por un recurso específico.
	 * Las funcionalidades incluyen actualizar la lista de usuarios, insertar 
	 * nuevos usuarios, buscar un usuario y eliminarlo.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String accion = request.getParameter("action");
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		
		request.setAttribute("buscado", null);
		if(accion.equals("insertar")){		
			if(!DBUsuario.existeEmail(email)){
				Usuario user = new Usuario(nombre, apellido, email);
				DBUsuario.insertar(user);
			}
		}
		else if(accion.equals("seleccionarUsuario")){
			Usuario user = DBUsuario.seleccionarUsuario(email);
			if(user!=null)
				request.setAttribute("buscado", user);
		}
		else if(accion.equals("actualizar")){
			Usuario user = DBUsuario.seleccionarUsuario(email);
			if(user!=null){
				user.setNombre(nombre);
				user.setApellido(apellido);
				DBUsuario.actualizar(user);
			}
		}
		else if(accion.equals("eliminar")){
			Usuario user = DBUsuario.seleccionarUsuario(email);
			if(user!=null)
				DBUsuario.eliminar(user);
		}
		List<Usuario> listaUsuarios = DBUsuario.listarUsuarios();
		request.setAttribute("usuarios", listaUsuarios);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}