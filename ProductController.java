package application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.sql.*;

public class ProductController {
    @FXML
    private TilePane tilePane;

    public void initialize() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx_db", "root", "Huzaifa123$%");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                String productName = resultSet.getString("name");
                String productprice = resultSet.getString("productprice");

                ImageView imageView = new ImageView("path_to_product_image");
                Label nameLabel = new Label(productName);
                Label descriptionLabel = new Label(productprice);

                tilePane.getChildren().addAll(imageView, nameLabel, descriptionLabel);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
