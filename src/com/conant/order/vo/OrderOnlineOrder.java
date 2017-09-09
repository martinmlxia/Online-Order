package com.conant.order.vo;


import org.hibernate.criterion.Order;

/**
 * <p>Title: Online Order Management System</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: conant</p>
 *
 * @author Martin
 * @version 1.0
 */

public class OrderOnlineOrder extends Order {

    public OrderOnlineOrder(String propertyName, boolean ascending) {
        super(propertyName, ascending);
    }
}
