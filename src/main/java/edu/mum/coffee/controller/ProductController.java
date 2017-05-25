package edu.mum.coffee.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.coffee.domain.Cart;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String action(@RequestParam("action") String action, @RequestParam("productId") String productId,
			Model model) {
		if ("delete".equals(action)) {
			int id = Integer.parseInt(productId);
			productService.deleteById(id);
			return "redirect:product/list";
		} else if ("update".equals(action)) {
			Product product = productService.getProduct(Integer.parseInt(productId));
			model.addAttribute("product", product);
			return "modifyProduct";
		} 
		Product product = new Product();
		model.addAttribute("product", product);
		return "modifyProduct";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "add")
	public String addProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "modifyProduct";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "addToCart")
	public String addProductToCart(@RequestParam("productId") String productId, HttpServletRequest request) {
		Product productToAddToCart = productService.getProduct(Integer.parseInt(productId));
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			cart.getProducts().add(productToAddToCart);
		} else {
			cart.getProducts().add(productToAddToCart);
		}
		request.getSession().setAttribute("cart", cart);
		return "redirect:/home";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="list")
	public String listAllProduct(Model model) {
		List<Product> products = productService.getAllProduct();
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping(value = "modify" , method = RequestMethod.POST)
	public String modifyProduct(@ModelAttribute("product") @Valid Product product,BindingResult result, Model model) {
		if (!result.hasErrors()) {
			productService.save(product);
		} else {
			model.addAttribute("product", product);
			return "modifyProduct"; 
		}
		
		return "redirect:list";
	}
	
}
