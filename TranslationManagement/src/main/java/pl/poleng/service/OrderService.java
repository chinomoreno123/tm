package pl.poleng.service;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import pl.poleng.dao.model.Order;
import pl.poleng.dao.model.User;

public abstract interface OrderService {
	public abstract User findById(Long paramLong);

	public abstract List<Order> findAllOrders();

	public abstract DataTablesOutput<Order> findAllOrders(DataTablesInput input);
}
