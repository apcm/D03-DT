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
import domain.Message;

import services.BoxService;
import services.CustomerService;
import services.HandyWorkerService;
import services.MessageService;
import services.RefereeService;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController{

	@Autowired
	MessageService messageService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	HandyWorkerService handyworkerService;
	
	@Autowired
	RefereeService refereeService;
	
	@Autowired
	BoxService boxService;
	

	// Constructors -----------------------------------------------------------

	public MessageController() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int boxId) {
		ModelAndView res;
		Collection<Message> messages;
		Box box = new Box();
		box.setId(boxId);
		messages=boxService.findOne(box).getMessages();
		res = new ModelAndView("message/list");
		res.addObject("messages", messages);
		res.addObject("requestURI", "message/list.do");

		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int messageId) {
		final ModelAndView res;
		final Message message;

		message = this.messageService.findOne(messageId);
		Assert.notNull(message);
		res = this.createEditModelAndView(message);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message message, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(message);
		else
			try {
				this.messageService.save(message);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(message, "message.commit.error");
			}

		return res;
	}

	protected ModelAndView createEditModelAndView(final Message message) {
		ModelAndView res;

		res = this.createEditModelAndView(message, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(final Message message, final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("message/edit");
		res.addObject("message", messageCode);

		return res;
	}
	
}
