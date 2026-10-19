package com.techdocs.document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface DocumentRepository extends JpaRepository<TechnicalDocument,Long> {
 @Query("select d from TechnicalDocument d where (:query is null or lower(d.title) like lower(concat('%',:query,'%')) or lower(coalesce(d.summary,'')) like lower(concat('%',:query,'%')) or lower(d.content) like lower(concat('%',:query,'%')) or lower(d.category) like lower(concat('%',:query,'%')) or lower(d.author) like lower(concat('%',:query,'%'))) and (:category is null or lower(d.category)=lower(:category)) and (:status is null or d.status=:status) order by d.updatedAt desc")
 List<TechnicalDocument> search(@Param("query") String query,@Param("category") String category,@Param("status") DocumentStatus status);
 long countByStatus(DocumentStatus status);
}
