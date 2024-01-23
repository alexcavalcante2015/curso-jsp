package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"})/*Intercepta todas as requisições que vierem do projeto ou mapeamento*/
public class FilterAutenticacao implements Filter {
       
    public FilterAutenticacao() {

    }
    /*Encerra os processos quando o servidor é parado*/
    /*Ex.:Mataria os processos de conexão com banco*/
	public void destroy() {

	}

	/*Intercepta as requisições e respostas no sistema*/
	/*Tudo que fizer no sistema vai fazer por aqui*/
	/*Ex.: Validação de autenticação*/
	/*Ex.: Dar commit e rollback de transações do banco*/
	/*Ex.: Validar e fazer redirecionamento de páginas*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath();/*Url que está sendo acessada*/
		
		/*Validar se está logado senão redireciona para a tela de login*/
		if (usuarioLogado == null && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/*Não está logado*/
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");
			redireciona.forward(request, response);
			return; /*Para a execução e redireciona para o login*/
			
		} else {
			chain.doFilter(request, response);
		}
		
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto*/
	/*Ex.:Iniciar a conexão com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
