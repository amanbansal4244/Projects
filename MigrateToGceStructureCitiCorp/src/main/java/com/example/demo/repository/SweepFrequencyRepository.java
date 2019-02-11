package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SweepFrequency;
/****
 * @Repository annotation is a stereotype and is used at class level. The class,
 *             whose behavior is to store, fetch or search data (basically all DB related operation), comes to the
 *             repository category. These types of classes should be annotated
 *             with @Repository annotation for auto-detection through class-path
 *             scanning. DAO classes should be annotated with @Repository
 *             annotation for auto-detection. @Repository annotation is the
 *             specialization of @Component annotation. @Repository annotation
 *             has an attribute value which is the suggestion of component name
 *             as well as spring bean name for that class.
 *
 */
@Repository
public interface SweepFrequencyRepository extends JpaRepository<SweepFrequency, Long> {

}
