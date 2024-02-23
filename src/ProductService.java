import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.time.LocalDate;
//import java.util.Arrays;

public class ProductService {
    
    List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProduct(String name) {
        for(Product p : products) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }


    public List<Product> getProductByPlace(String place) {
        List<Product> result = new ArrayList<>();

        for(Product p : products) {
            if(p.getPlace().equals(place)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Product> getExpiredProducts() {
        List<Product> expired = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int currYear = currentDate.getYear();

        for(Product p : products) {
            if(p.getWarranty() < currYear) {
                expired.add(p);
            }
        }
        return expired;
    }

    // public List<Product> getProductWithText(String text) {
    //     String str = text.toLowerCase();
    //     List<Product> res = new ArrayList<>();

    //     for(Product p : products) {
    //         String name = p.getName().toLowerCase();
    //         String type = p.getType().toLowerCase();
    //         String place = p.getPlace().toLowerCase();
    //         if(name.contains(str) ||
    //             type.contains(str) ||
    //             place.contains(str)) {
    //                 res.add(p);
    //         }
    //     }        

    //     return res;
    // }

    public List<Product> getProductWithText(String text) {
        String str = text.toLowerCase();
        List<Product> res = new ArrayList<>();
        Stream<Product> productStream = products.stream();
        
        Predicate<Product> pred = new Predicate<Product>() {

            @Override
            public boolean test(Product t) {
                String name = t.getName().toLowerCase();
                String type = t.getType().toLowerCase();
                String place = t.getPlace().toLowerCase();

                if(name.contains(str) || type.contains(str) || place.contains(str)) {
                    return true;
                } else{
                    return false;
                }
            }

        };

        productStream.filter(pred).forEach(n -> res.add(n));


        //res = products.forEach(prod -> )

        return res;
    }
}
