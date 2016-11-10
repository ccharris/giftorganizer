package com.harris.carolyn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harris.carolyn.beans.Event;
import com.harris.carolyn.beans.Gift;
import com.harris.carolyn.beans.Recipient;
import com.harris.carolyn.beans.User;
import com.harris.carolyn.repository.EventRecipientRepository;
import com.harris.carolyn.repository.EventRepository;
import com.harris.carolyn.repository.GiftRepository;
import com.harris.carolyn.repository.RecipientRepository;
import com.harris.carolyn.repository.UserRepository;

@Controller
public class MainController {

	@Autowired
	private EventRecipientRepository eventRecipRepo;

	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private GiftRepository giftRepo;

	@Autowired
	private RecipientRepository recipientRepo;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit() {
		return "index";
	}

	@GetMapping("/recipients")
	public String users(Model model, @RequestParam(name = "srch-term", required = false) String searchTerm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User v = userRepo.findOneByEmail(name);
		if (searchTerm == null || "".equals(searchTerm)) {
			model.addAttribute("recipients", recipientRepo.findByUserId(v.getId()));
		} else {
			List<Recipient> userRecipients = recipientRepo.findByUserId(v.getId());
			List<Recipient> searchRecipients = recipientRepo
					.findByLastNameContainsOrFirstNameContainsOrEmailContainsOrBirthdayContainsOrAnniversaryContainsAllIgnoreCase(
							searchTerm, searchTerm, searchTerm, searchTerm, searchTerm);
			List<Recipient> recipients = new ArrayList<Recipient>();
			for (Recipient recipient : searchRecipients) {
				if (userRecipients.contains(recipient)) {
					recipients.add(recipient);
				}
			}
			model.addAttribute("recipients", recipients);

		}
		return "recipients";
	}

	@GetMapping("/recipient/{id}/gifts")
	public String gifts(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("id", id);
		Recipient u = recipientRepo.findOne(id);
		model.addAttribute("recipient", u);
		model.addAttribute("gifts", giftRepo.findByRecipientId(id));

		return "gifts";
	}

	@GetMapping("/events")
	public String events(Model model, @RequestParam(name = "srch-term", required = false) String searchTerm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User v = userRepo.findOneByEmail(name);
		if (searchTerm == null || "".equals(searchTerm)) {
			model.addAttribute("events", eventRepo.findByUserId(v.getId()));
		} else {
			List<Event> userEvents = eventRepo.findByUserId(v.getId());
			List<Event> searchEvents = eventRepo.findByNameContainsOrBudgetContainsAllIgnoreCase(searchTerm,
					searchTerm);
			List<Event> events = new ArrayList<Event>();
			for (Event event : searchEvents) {
				if (userEvents.contains(event)) {
					events.add(event);
				}
			}
			model.addAttribute("events", events);

		}
		return "events";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute(new User());
		return "signup";
	}

	@PostMapping("signup")
	public String signupSave(@ModelAttribute @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "signup";
		} else {
			userRepo.save(user);
			return "redirect:/";
		}

	}

	@GetMapping("/recipient/{id}")
	public String recipient(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("id", id);
		Recipient u = recipientRepo.findOne(id);
		model.addAttribute("recipient", u);
		return "recipient_detail";
	}

	@GetMapping("/recipient/{id}/gift/create")
	public String giftCreate(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("recipient", recipientRepo.findOne(id));
		Gift g = new Gift();
		Recipient r = recipientRepo.findOne(id);
		g.setRecipient(r);
		model.addAttribute("recipientId", r.getId());
		model.addAttribute("gift", g);

		return "gift_create";
	}

	@PostMapping("/recipient/{id}/gift/create")
	public String giftCreateSave(@ModelAttribute @Valid Gift gift, BindingResult result, Model model,
			@PathVariable(name = "id") long id) {
		model.addAttribute("recipient", recipientRepo.findOne(id));
		Recipient r = recipientRepo.findOne(id);
		model.addAttribute("recipientId", r.getId());
		if (result.hasErrors()) {
			System.out.println("ERROR");
			model.addAttribute("gift", gift);
			return "gift_create";
		} else {
			gift.setRecipient(r);
			if (gift.getLinkOne() == null) {

			} else if (!(gift.getLinkOne().contains("http"))) {
				String link = gift.getLinkOne();
				String linkOne = ("http://" + link);
				gift.setLinkOne(linkOne);
			}
			if (gift.getLinkTwo() == null) {

			} else if (!(gift.getLinkTwo().contains("http"))) {
				String link = gift.getLinkTwo();
				String linkTwo = ("http://" + link);
				gift.setLinkTwo(linkTwo);
			}

			giftRepo.save(gift);

			return "redirect:/recipient/" + r.getId() + "/gifts";
		}

	}

	@GetMapping("/recipient/create")
	public String recipientCreate(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User v = userRepo.findOneByEmail(name);
		boolean isUser = false;
		long userId = v.getId();
		model.addAttribute("userId", userId);
		model.addAttribute(new Recipient());

		return "recipient_create";
	}

	@PostMapping("/recipient/create")
	public String recipientCreateSave(@ModelAttribute @Valid Recipient recipient, BindingResult result, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User v = userRepo.findOneByEmail(name);
		long userId = v.getId();
		model.addAttribute("userId", userId);

		if (result.hasErrors()) {
			model.addAttribute("recipient", recipient);
			return "recipient_create";
		} else {
			recipient.setUser(v);
			recipientRepo.save(recipient);
			return "redirect:/recipients";
		}

	}

	@GetMapping("/recipient/{id}/delete")
	public String recipientDelete(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("recipient", recipientRepo.findOne(id));
		return "recipient_delete";
	}

	@PostMapping("/recipient/{id}/delete")
	public String recipientDeleteSave(@ModelAttribute @Valid Recipient recipient, BindingResult result, Model model,
			@PathVariable(name = "id") long id) {
		recipientRepo.delete(recipient);
		return "redirect:/recipients";

	}

	@GetMapping("/recipient/{id}/edit")
	public String recipientEdit(Model model, @PathVariable(name = "id") long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User v = userRepo.findOneByEmail(name);
		long userId = v.getId();
		model.addAttribute("userId", userId);
		model.addAttribute("id", id);
		Recipient u = recipientRepo.findOne(id);
		model.addAttribute("recipient", u);
		return "recipient_edit";
	}

	@PostMapping("/recipient/{id}/edit")
	public String recipientEditSave(@PathVariable(name = "id") long id, @ModelAttribute @Valid Recipient recipient,
			BindingResult result, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User v = userRepo.findOneByEmail(name);
		long userId = v.getId();
		model.addAttribute("userId", userId);

		if (result.hasErrors()) {
			model.addAttribute("recipient", recipient);
			return "recipient_edit";
		} else {
			recipient.setUser(v);
			recipientRepo.save(recipient);

			return "redirect:/recipient/" + recipient.getId();
		}

	}

	@GetMapping("/recipient/{id}/gift/{giftid}/delete")
	public String giftDelete(Model model, @PathVariable(name = "id") long id,
			@PathVariable(name = "giftid") long giftId) {
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("gift", giftRepo.findOne(giftId));
		return "gift_delete";
	}

	@PostMapping("/recipient/{id}/gift/{giftid}/delete")
	public String giftDeleteSave(@ModelAttribute @Valid Gift gift, BindingResult result, Model model,
			@PathVariable(name = "id") long id, @PathVariable(name = "giftid") long giftId) {
		giftRepo.delete(giftRepo.findOne(giftId));
		Recipient r = recipientRepo.findOne(id);

		return "redirect:/recipient/" + r.getId() + "/gifts";

	}

	@GetMapping("/recipient/{id}/gift/{giftid}/edit")
	public String giftEdit(Model model, @PathVariable(name = "id") long id,
			@PathVariable(name = "giftid") long giftId) {
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("recipientId", id);
		model.addAttribute("id", giftId);
		Gift g = giftRepo.findOne(giftId);
		model.addAttribute("gift", g);
		return "gift_edit";
	}

	@PostMapping("/recipient/{id}/gift/{giftid}/edit")
	public String recipientEditSave(@PathVariable(name = "id") long id, @PathVariable(name = "giftid") long giftId,
			@ModelAttribute @Valid Gift gift, BindingResult result, Model model) {
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("recipientId", id);
		model.addAttribute("id", giftId);
		Gift g = giftRepo.findOne(giftId);
		model.addAttribute("gift", g);
		Recipient r = recipientRepo.findOne(id);
		if (result.hasErrors()) {
			model.addAttribute("gift", gift);
			return "gift_edit";
		} else {
			gift.setRecipient(recipientRepo.findOne(id));
			if (gift.getLinkOne() == null) {

			} else if (!(gift.getLinkOne().contains("http")) && !(gift.getLinkOne().equals(""))) {
				String link = gift.getLinkOne();
				String linkOne = ("http://" + link);
				gift.setLinkOne(linkOne);
			}
			if (gift.getLinkTwo() == null) {

			} else if (!(gift.getLinkTwo().contains("http")) && !(gift.getLinkTwo().equals(""))) {
				String link = gift.getLinkTwo();
				String linkTwo = ("http://" + link);
				gift.setLinkTwo(linkTwo);
			}

			giftRepo.save(gift);

			return "redirect:/recipient/" + r.getId() + "/gifts";
		}

	}

	@GetMapping("/recipient/{id}/gift/{giftid}/bought")
	public String giftBough(Model model, @PathVariable(name = "id") long id,
			@PathVariable(name = "giftid") long giftId) {
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("recipientId", id);
		model.addAttribute("id", giftId);
		Gift g = giftRepo.findOne(giftId);
		model.addAttribute("gifts", giftRepo.findByRecipientId(id));
		g.setBought(true);
		giftRepo.save(g);
		return "gifts";
	}

}
