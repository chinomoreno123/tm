package pl.poleng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.poleng.dao.OrderRepository;
import pl.poleng.dao.model.Order;
import pl.poleng.dao.model.User;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository dao;
	
	@Override
	public User findById(Long paramLong) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Order> findAllOrders() {
		return (List<Order>) this.dao.findAll();
	}

	@Override
	public DataTablesOutput<Order> findAllOrders(DataTablesInput input) {
		return this.dao.findAll(input);
	}

}
