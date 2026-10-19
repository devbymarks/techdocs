package com.techdocs.document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @Transactional
public class DocumentService {
 private final DocumentRepository repository;
 public DocumentService(DocumentRepository repository){this.repository=repository;}
 @Transactional(readOnly=true) public List<TechnicalDocument> findAll(String q,String category,DocumentStatus status){return repository.search(q==null||q.isBlank()?null:q.trim(),category==null||category.isBlank()?null:category.trim(),status);}
 @Transactional(readOnly=true) public TechnicalDocument findById(Long id){return repository.findById(id).orElseThrow(()->new DocumentNotFoundException(id));}
 public TechnicalDocument create(TechnicalDocument d){d.setId(null);return repository.save(d);}
 public TechnicalDocument update(Long id,TechnicalDocument input){TechnicalDocument d=findById(id);d.setTitle(input.getTitle());d.setCategory(input.getCategory());d.setAuthor(input.getAuthor());d.setSummary(input.getSummary());d.setContent(input.getContent());if(input.getStatus()!=null)d.setStatus(input.getStatus());return repository.save(d);}
 public void delete(Long id){repository.delete(findById(id));}
 @Transactional(readOnly=true) public DocumentStats stats(){return new DocumentStats(repository.count(),repository.countByStatus(DocumentStatus.PUBLISHED),repository.countByStatus(DocumentStatus.DRAFT),repository.countByStatus(DocumentStatus.ARCHIVED));}
 public record DocumentStats(long total,long published,long drafts,long archived){}
}
