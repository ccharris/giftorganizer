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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harris.carolyn.beans.Event;
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
		if(searchTerm == null || "".equals(searchTerm)){
		model.addAttribute("recipients", recipientRepo.findByUserId(v.getId()));
		} else {
			List<Recipient> userRecipients = recipientRepo.findByUserId(v.getId());
			List<Recipient> searchRecipients = recipientRepo.findByLastNameContainsOrFirstNameContainsOrEmailContainsOrBirthdayContainsOrAnniversaryContainsAllIgnoreCase(searchTerm, searchTerm, searchTerm, searchTerm, searchTerm);
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
	
	@GetMapping("/events")
	public String events(Model model, @RequestParam(name = "srch-term", required = false) String searchTerm) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User v = userRepo.findOneByEmail(name);
		if(searchTerm == null || "".equals(searchTerm)){
		model.addAttribute("events", eventRepo.findByUserId(v.getId()));
		} else {
			List<Event> userEvents = eventRepo.findByUserId(v.getId());
			List<Event> searchEvents = eventRepo.findByNameContainsOrBudgetContainsAllIgnoreCase(searchTerm, searchTerm);
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
	public String signupSave(@ModelAttribute @Valid User user,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "signup";
		} else {
			userRepo.save(user);
			return "redirect:/";
		}

	}

}
