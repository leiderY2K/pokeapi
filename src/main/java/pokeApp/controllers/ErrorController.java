package pokeApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

	@RequestMapping("/error")
	public String handleError(WebRequest request, Model model) {
		var message = (String) request.getAttribute("javax.servlet.error.message", WebRequest.SCOPE_REQUEST);
		var status = (Integer) request.getAttribute("javax.servlet.error.status_code", WebRequest.SCOPE_REQUEST);
		model.addAttribute("message", message != null ? message : "Hubo un error desconocido en la aplicación");
		model.addAttribute("status", status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR.value());
		return "error/index";
	}
}
