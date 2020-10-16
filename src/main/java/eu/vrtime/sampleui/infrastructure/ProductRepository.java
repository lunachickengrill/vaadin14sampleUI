package eu.vrtime.sampleui.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eu.vrtime.sampleui.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public Product findByProductIdEqualsIgnoreCase(final String productId);
	
	public List<Product> findByProductIdStartingWithIgnoreCaseOrderByProductIdDesc(final String productId);

}
