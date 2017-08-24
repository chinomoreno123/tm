package pl.poleng.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import pl.poleng.dao.model.Order;
import pl.poleng.dao.model.User;
import pl.poleng.service.OrderService;

@Controller
@RequestMapping({ "/order" })
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = { "", "/", "/list" }, method = { RequestMethod.GET })
	public String listUsers(ModelMap model) {
			
		List<Order> orders = this.orderService.findAllOrders();
		model.addAttribute("orders", orders);
		//model.addAttribute("loggedinuser", preferences.getName());
		model.addAttribute("errorMessage", "ERROR MESSAGE");
		return "orderslist";
	}
	
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = "/listData", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Order> getOrders(@Valid DataTablesInput input) {
		return orderService.findAllOrders(input);
	}
	
}
