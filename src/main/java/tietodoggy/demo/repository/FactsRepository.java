package tietodoggy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tietodoggy.demo.entity.Facts;

public interface FactsRepository extends JpaRepository<Facts, Long> {
}
