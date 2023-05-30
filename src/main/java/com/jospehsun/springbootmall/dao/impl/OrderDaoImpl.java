package com.jospehsun.springbootmall.dao.impl;

import com.jospehsun.springbootmall.dao.OrderDao;
import com.jospehsun.springbootmall.dto.OrderQueryParams;
import com.jospehsun.springbootmall.model.Order;
import com.jospehsun.springbootmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        String sql = "SELECT count(*) FROM `order` WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        String sql = "SELECT orderId, userId, totalAmount, createdDate, lastModifiedDate FROM `order` WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        // 查詢條件
        sql = addFilteringSql(sql, map, orderQueryParams);

        // 排序
        sql = sql + " ORDER BY createdDate DESC ";

        // 分頁
        sql = sql + " LIMIT :limit OFFSET :offset ";
        map.put("limit", orderQueryParams.getLimit());
        map.put("offset", orderQueryParams.getOffset());

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(Order.class));

        return orderList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT orderId, userId, totalAmount, createdDate, lastModifiedDate " +
                "FROM `order` WHERE orderId = :orderId ";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(Order.class));

        if (orderList.size() > 0) {
            return orderList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT orderItem.orderItemId, orderItem.orderId, orderItem.productId, orderItem.quantity, orderItem.amount, " +
                "product.productName, product.imageUrl " +
                "FROM orderItem as orderItem " +
                "LEFT JOIN product as product ON orderItem.productId = product.productId " +
                "WHERE orderItem.orderId = :orderId ";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);

        return namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(OrderItem.class));
    }

    @Override
    public Integer createOrder(Integer userId, Integer totalAmount) {
        String sql = "INSERT INTO `order` (userId, totalAmount, createdDate, lastModifiedDate) " +
                "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate) ";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId = keyHolder.getKey().intValue();

        return orderId;
    }

    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
        // 使用 batchUpdate 一次性加入數據，效率更高
        String sql = "INSERT INTO orderItem (orderId, productId, quantity, amount) " +
                "VALUES (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);

            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, OrderQueryParams orderQueryParams) {
        if (orderQueryParams.getUserId() != null) {
            sql = sql + " AND userId = :userId ";
            map.put("userId", orderQueryParams.getUserId());
        }

        return sql;
    }
}
