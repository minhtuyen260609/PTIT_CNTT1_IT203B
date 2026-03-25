public class ProductDAOTest {
    public boolean decreaseStock(Connection conn, int productId, int quantity) throws SQLException {
        String sql = SQLConstants.UPDATE_STOCK;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);

            int affectedRows = ps.executeUpdate();

            return affectedRows > 0; // false = hết hàng
        }
    }

    public double getProductPrice(Connection conn, int productId) throws SQLException {
        String sql = "SELECT price FROM Products WHERE product_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("price");
            }
        }

        throw new SQLException("Product not found");
    }
}
