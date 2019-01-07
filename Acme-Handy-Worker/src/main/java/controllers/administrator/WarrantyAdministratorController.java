
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.WarrantyService;
import domain.Warranty;

@Controller
@RequestMapping("/warranty/administrator")
public class WarrantyAdministratorController extends AbstractController {

	@Autowired
	private WarrantyService	warrantyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Warranty> warranties;

		warranties = this.warrantyService.findAll();

		res = new ModelAndView("warranty/list");
		res.addObject("warranties", warranties);
		res.addObject("requestURI", "warranty/administrator/list.do");

		return res;
	}

}
