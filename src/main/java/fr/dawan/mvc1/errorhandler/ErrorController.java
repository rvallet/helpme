package fr.dawan.mvc1.errorhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

	@RequestMapping(value = "errors", method = RequestMethod.GET)
	public String renderErrorPage(HttpServletRequest httpRequest, Model m) {

		int httpErrorCode = getErrorCode(httpRequest);
		HttpStatus httpStatus = HttpStatus.valueOf(httpErrorCode);
		String errorMsg = httpStatus.getReasonPhrase();

		switch (httpErrorCode) {
		case 400: {
			m.addAttribute("httpErrorCode", httpErrorCode);
			m.addAttribute("errorMsg", errorMsg);
			return "/httperrors/400";
		}
		case 403: {
			m.addAttribute("httpErrorCode", httpErrorCode);
			m.addAttribute("errorMsg", errorMsg);
			return "/httperrors/403";
		}
		case 404: {
			m.addAttribute("httpErrorCode", httpErrorCode);
			m.addAttribute("errorMsg", errorMsg);
			return "/httperrors/404";
		}
		case 405: {
			m.addAttribute("httpErrorCode", httpErrorCode);
			m.addAttribute("errorMsg", errorMsg);
			return "/httperrors/405";
		}
		case 500: {
			m.addAttribute("httpErrorCode", httpErrorCode);
			m.addAttribute("errorMsg", errorMsg);
			return "/httperrors/500";
		}
		}
		m.addAttribute("httpErrorCode", httpErrorCode);
		m.addAttribute("errorMsg", errorMsg);
		return "/httperrors/errorPage";
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}
