package tietodoggy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tietodoggy.demo.entity.Fact;

import java.util.List;

public interface FactsRepository extends JpaRepository<Fact, Long> {
    @Query("select f from Fact f where f.isDeleted=false")
    public List<Fact> findAll();
}
