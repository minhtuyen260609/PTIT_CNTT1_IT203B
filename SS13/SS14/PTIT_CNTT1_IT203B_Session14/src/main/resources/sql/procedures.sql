USE FlashSaleDB;

DROP PROCEDURE IF EXISTS SP_GetTopBuyers;
DROP PROCEDURE IF EXISTS FUNC_CalculateCategoryRevenue;

DELIMITER $$

CREATE PROCEDURE SP_GetTopBuyers()
BEGIN
    SELECT
        u.user_id,
        u.username,
        u.email,
        SUM(od.quantity)            AS total_quantity_bought,
        COUNT(DISTINCT o.order_id)  AS total_orders
    FROM Users u
    JOIN Orders o       ON u.user_id   = o.user_id
    JOIN Order_Details od ON o.order_id = od.order_id
    WHERE o.status = 'SUCCESS'
    GROUP BY u.user_id, u.username, u.email
    ORDER BY total_quantity_bought DESC
    LIMIT 5;
END$$

CREATE PROCEDURE FUNC_CalculateCategoryRevenue(
    IN  p_category      VARCHAR(100),
    OUT p_total_revenue DECIMAL(15, 2)
)
BEGIN
    SELECT
        COALESCE(SUM(od.quantity * od.unit_price), 0)
    INTO p_total_revenue
    FROM Order_Details od
    JOIN Products p ON od.product_id = p.product_id
    JOIN Orders   o ON od.order_id   = o.order_id
    WHERE p.category = p_category
      AND o.status   = 'SUCCESS';
END$$

DELIMITER ;
