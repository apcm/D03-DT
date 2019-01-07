package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import domain.Box;
import security.LoginService;
import services.BoxService;
import services.MessageService;

@Controller
@RequestMapping("/box")
public class BoxController extends AbstractController{
	
	@Autowired
	BoxService boxService;
	
	@Autowired
	MessageService messageService;
	
	// Constructors -----------------------------------------------------------

		public BoxController() {
			super();
		}
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView res;
			Collection<Box> boxes;
			boxes=boxService.findByUser(LoginService.getPrincipal().getId());
			res = new ModelAndView("box/list");
			res.addObject("boxes", boxes);
			res.addObject("requestURI", "box/list.do");

			return res;
		}
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int boxId) {
			final ModelAndView res;
			Box box=new Box();
			box.setId(boxId);
			box = this.boxService.findOne(box);
			Assert.notNull(box);
			res = this.createEditModelAndView(box);

			return res;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Box box, final BindingResult binding) {
			ModelAndView res;

			if (binding.hasErrors())
				res = this.createEditModelAndView(box);
			else
				try {
					this.boxService.save(box);
					res = new ModelAndView("redirect:list.do");
				} catch (final Throwable oops) {
					res = this.createEditModelAndView(box, "box.commit.error");
				}

			return res;
		}

		protected ModelAndView createEditModelAndView(final Box box) {
			ModelAndView res;

			res = this.createEditModelAndView(box, null);

			return res;
		}

		protected ModelAndView createEditModelAndView(final Box box, final String messageCode) {
			ModelAndView res;

			res = new ModelAndView("box/edit");
			res.addObject("box", messageCode);

			return res;
		}
		
}
