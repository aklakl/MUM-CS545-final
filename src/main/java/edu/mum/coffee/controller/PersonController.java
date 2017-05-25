package edu.mum.coffee.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String action(@RequestParam("action") String action, @RequestParam("personId") String personId,
			Model model) {
		if ("update".equals(action)) {
			return "redirect:/person/changeInformation";
		}
		Person person = new Person();
		model.addAttribute("person", person);
		return "modifyPerson";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "add")
	public String addPersonPage(Model model) {
		Person person = new Person();
		model.addAttribute("person", person);
		return "modifyPerson";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="list")
	public String listAllPerson(Model model) {
		List<Person> persons = personService.getAllPerson();
		model.addAttribute("persons", persons);
		return "persons";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "modify" , method = RequestMethod.POST)
	public String modifyPerson(@ModelAttribute("person") @Valid Person person,BindingResult result, Model model) {
		if (!result.hasErrors()) {
			personService.savePerson(person);
		} else {
			model.addAttribute("person", person);
			return "modifyPerson"; 
		}
		
		Collection<SimpleGrantedAuthority> authorities = 
				(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		if ("ROLE_USER".equals(authorities.iterator().next().getAuthority())) {
			return "redirect:/home";
		} else {
			return "redirect:list";
		}
	}
	
	@RequestMapping(value = "changeInformation", method = RequestMethod.GET)
	public String changeInformationPerson(@RequestParam("username") String username, Model model) {
		List<Person> persons = personService.findByEmail(username);
		Person person = persons != null && persons.size() > 0 ?
				persons.get(0) : null;
		model.addAttribute("person", person);
		return "modifyPerson";
	}
}
