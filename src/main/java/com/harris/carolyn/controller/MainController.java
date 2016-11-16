package com.harris.carolyn.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.harris.carolyn.beans.Account;
import com.harris.carolyn.beans.Recipient;
import com.harris.carolyn.repository.EventRecipientRepository;
import com.harris.carolyn.repository.EventRepository;
import com.harris.carolyn.repository.GiftRepository;
import com.harris.carolyn.repository.AccountRepository;
import com.harris.carolyn.repository.RecipientRepository;
import com.stormpath.sdk.servlet.account.AccountResolver;

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
	private AccountRepository userRepo;

	boolean setOnce = false;
	
	@GetMapping("")
	public String index(Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			if(userRepo.findOneByEmail(AccountResolver.INSTANCE.getAccount(req).getEmail()) != null){
				
			} else {
			v.setAccount(req, v);
			userRepo.save(v);
			System.out.println(v.getId());
			}
		}
		return "index";
	}
	
	@GetMapping("/#")
	public String homeThing(Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			if(userRepo.findOneByEmail(AccountResolver.INSTANCE.getAccount(req).getEmail()) != null){
				
			} else {
			v.setAccount(req, v);
			userRepo.save(v);
			System.out.println(v.getId());
			}
		}
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			if(userRepo.findOneByEmail(AccountResolver.INSTANCE.getAccount(req).getEmail()) != null){
				
			} else {
			v.setAccount(req, v);
			userRepo.save(v);
			System.out.println(v.getId());
			}
		}
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit() {
		return "home";
	}

	@GetMapping("/recipients")
	public String users(Model model, @RequestParam(name = "srch-term", required = false) String searchTerm, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			if(userRepo.findOneByEmail(AccountResolver.INSTANCE.getAccount(req).getEmail()) != null){
				
			} else {
			v.setAccount(req, v);
			userRepo.save(v);
			System.out.println(v.getId());
			}
		}
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		if (searchTerm == null || "".equals(searchTerm)) {
			model.addAttribute("recipients", recipientRepo.findByUserId(v.getId()));
		} else {
			List<Recipient> userRecipients = recipientRepo.findByUserId(v.getId());
			List<Recipient> searchRecipients = recipientRepo
					.findByLastNameContainsOrFirstNameContainsOrEmailContainsOrBirthdayContainsOrNotesContainsOrGroupTagContainsAllIgnoreCase(
							searchTerm, searchTerm, searchTerm, searchTerm, searchTerm, searchTerm);
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
	public String gifts(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("id", id);
		Recipient u = recipientRepo.findOne(id);
		model.addAttribute("recipient", u);
		model.addAttribute("gifts", giftRepo.findByRecipientId(id));

		return "gifts";
	}
	
	@GetMapping("/event/{id}/recipients")
	public String eventRecipients(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("id", id);
		Event e = eventRepo.findOne(id);
		model.addAttribute("event", e);

		return "event_recipients";
	}

	@GetMapping("/events")
	public String events(Model model, @RequestParam(name = "srch-term", required = false) String searchTerm, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			if(userRepo.findOneByEmail(AccountResolver.INSTANCE.getAccount(req).getEmail()) != null){
				
			} else {
			v.setAccount(req, v);
			userRepo.save(v);
			System.out.println(v.getId());
			}
		}
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
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
		model.addAttribute(new Account());
		return "signup";
	}

	@PostMapping("signup")
	public String signupSave(@ModelAttribute @Valid Account user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "signup";
		} else {
			userRepo.save(user);
			return "redirect:/";
		}

	}

	@GetMapping("/recipient/{id}")
	public String recipient(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("id", id);
		Recipient u = recipientRepo.findOne(id);
		model.addAttribute("recipient", u);
		return "recipient_detail";
	}
	
	@GetMapping("/event/{id}")
	public String event(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("id", id);
		Event e = eventRepo.findOne(id);
		model.addAttribute("event", e);
		return "event_detail";
	}

	@GetMapping("/recipient/{id}/gift/create")
	public String giftCreate(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
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
			@PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("recipient", recipientRepo.findOne(id));
		Recipient r = recipientRepo.findOne(id);
		model.addAttribute("recipientId", r.getId());
		if (result.hasErrors()) {
			System.out.println("ERROR");
			model.addAttribute("gift", gift);
			return "gift_create";
		} else {
			gift.setRecipient(r);
			if (gift.getLinkOne() == null || gift.getLinkOne().equals("")) {

			} else if (!(gift.getLinkOne().contains("http"))) {
				String link = gift.getLinkOne();
				String linkOne = ("http://" + link);
				gift.setLinkOne(linkOne);
			}
			if (gift.getLinkTwo() == null || gift.getLinkTwo().equals("")) {

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
	public String recipientCreate(Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		long userId = v.getId();
		model.addAttribute("userId", userId);
		model.addAttribute(new Recipient());

		return "recipient_create";
	}

	@PostMapping("/recipient/create")
	public String recipientCreateSave(@ModelAttribute @Valid Recipient recipient, BindingResult result, Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
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
	
	@GetMapping("/event/create")
	public String eventCreate(Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		long userId = v.getId();
		model.addAttribute("userId", userId);
		model.addAttribute("recipients", recipientRepo.findByUserId(v.getId()));
		Event e = new Event();
		e.setUser(v);
		model.addAttribute("event", e);

		return "event_create";
	}

	@PostMapping("/event/create")
	public String eventCreateSave(@ModelAttribute @Valid Event event, BindingResult result, Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		

		if (result.hasErrors()) {
			model.addAttribute("event", event);
			return "event_create";
		} else {
			eventRepo.save(event);
			return "redirect:/events";
		}

	}

	@GetMapping("/recipient/{id}/delete")
	public String recipientDelete(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("gifts", giftRepo.findByRecipientId(id));
		return "recipient_delete";
	}

	@PostMapping("/recipient/{id}/delete")
	public String recipientDeleteSave(@ModelAttribute @Valid Recipient recipient, BindingResult result, Model model,
			@PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		recipientRepo.delete(recipient);
		return "redirect:/recipients";

	}

	@GetMapping("/recipient/{id}/edit")
	public String recipientEdit(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		long userId = v.getId();
		model.addAttribute("userId", userId);
		model.addAttribute("id", id);
		Recipient u = recipientRepo.findOne(id);
		model.addAttribute("recipient", u);
		return "recipient_edit";
	}

	@PostMapping("/recipient/{id}/edit")
	public String recipientEditSave(@PathVariable(name = "id") long id, @ModelAttribute @Valid Recipient recipient,
			BindingResult result, Model model, HttpServletRequest req) {

		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
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
	
	@GetMapping("/event/{id}/edit")
	public String eventEdit(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		long userId = v.getId();
		model.addAttribute("userId", userId);
		model.addAttribute("id", id);
		Event e = eventRepo.findOne(id);
		model.addAttribute("event", e);
		model.addAttribute("recipients", recipientRepo.findByUserId(v.getId()));
		return "event_edit";
	}

	@PostMapping("/event/{id}/edit")
	public String eveentEditSave(@PathVariable(name = "id") long id, @ModelAttribute @Valid Event event,
			BindingResult result, Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}

		if (result.hasErrors()) {
			model.addAttribute("event", event);
			return "event_edit";
		} else {
			eventRepo.save(event);
			return "redirect:/event/" + event.getId();
		}

	}

	@GetMapping("/recipient/{id}/gift/{giftid}/delete")
	public String giftDelete(Model model, @PathVariable(name = "id") long id,
			@PathVariable(name = "giftid") long giftId, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("gift", giftRepo.findOne(giftId));
		return "gift_delete";
	}

	@PostMapping("/recipient/{id}/gift/{giftid}/delete")
	public String giftDeleteSave(@ModelAttribute @Valid Gift gift, BindingResult result, Model model,
			@PathVariable(name = "id") long id, @PathVariable(name = "giftid") long giftId, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		giftRepo.delete(giftRepo.findOne(giftId));
		Recipient r = recipientRepo.findOne(id);

		return "redirect:/recipient/" + r.getId() + "/gifts";

	}
	
	@GetMapping("/event/{id}/delete")
	public String eventDelete(Model model, @PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("event", eventRepo.findOne(id));
		return "event_delete";
	}

	@PostMapping("/event/{id}/delete")
	public String eventDeleteSave(@ModelAttribute @Valid Event event, BindingResult result, Model model,
			@PathVariable(name = "id") long id, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		eventRepo.delete(eventRepo.findOne(id));

		return "redirect:/events";

	}

	@GetMapping("/recipient/{id}/gift/{giftid}/edit")
	public String giftEdit(Model model, @PathVariable(name = "id") long id,
			@PathVariable(name = "giftid") long giftId, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("recipientId", id);
		model.addAttribute("id", giftId);
		Gift g = giftRepo.findOne(giftId);
		model.addAttribute("gift", g);
		return "gift_edit";
	}

	@PostMapping("/recipient/{id}/gift/{giftid}/edit")
	public String recipientEditSave(@PathVariable(name = "id") long id, @PathVariable(name = "giftid") long giftId,
			@ModelAttribute @Valid Gift gift, BindingResult result, Model model, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
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
			if (gift.getLinkOne() == null || gift.getLinkOne().equals("")) {

			} else if (!(gift.getLinkOne().contains("http")) && !(gift.getLinkOne().equals(""))) {
				String link = gift.getLinkOne();
				String linkOne = ("http://" + link);
				gift.setLinkOne(linkOne);
			}
			if (gift.getLinkTwo() == null || gift.getLinkTwo().equals("")) {

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
	public String giftBought(Model model, @PathVariable(name = "id") long id,
			@PathVariable(name = "giftid") long giftId, HttpServletRequest req) {
		Account v = new Account();
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			String email = (AccountResolver.INSTANCE.getAccount(req).getEmail());
			v = userRepo.findOneByEmail(email);
		}
		model.addAttribute("recipient", recipientRepo.findOne(id));
		model.addAttribute("recipientId", id);
		model.addAttribute("id", giftId);
		Gift g = giftRepo.findOne(giftId);
		model.addAttribute("gifts", giftRepo.findByRecipientId(id));
		g.setBought(true);
		giftRepo.save(g);
		Event e = eventRepo.findByRecipients(recipientRepo.findOne(id));
		BigDecimal budget = e.getBudget();
		BigDecimal newBudget = budget.subtract(g.getPrice());
		e.setBudget(newBudget);
		eventRepo.save(e);
		return "gifts";
	}

}
