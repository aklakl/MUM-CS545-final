package edu.mum.coffee.restful;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.domain.Users;
import edu.mum.coffee.service.ProductService;
import edu.mum.coffee.service.UserService;

@RestController
public class SecurityRestController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "restful/home")
	public List<List<Product>> homePage() {
		List<Product> products = productService.getAllProduct();
		List<Product> productsBF = new ArrayList<>();
		List<Product> productsLunch = new ArrayList<>();
		List<Product> productsDinner = new ArrayList<>();
		for (Product product : products) {
			if (product.getProductType() == ProductType.BREAKFAST) {
				productsBF.add(product);
			} else if (product.getProductType() == ProductType.LUNCH) {
				productsLunch.add(product);
			} else {
				productsDinner.add(product);
			}
		}
		return new ArrayList<>(Arrays.asList(productsBF,productsLunch,productsDinner));
	}
	
	@GetMapping(value = "/restful/login")
	public Users login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Users user = userService.findUser(username);
		if (user.getPassword().equals(userService.encodePassword(password))) {
			return user;
		}
		return null;
	}
	
	@PostMapping(value = "/restful/signup")
	public ResponseEntity<?> register(@Validated @RequestBody Users users, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			return ResponseEntity.ok(userService.save(users));
		} else {
			return ResponseEntity.badRequest().body(result);
		}
	}
}
