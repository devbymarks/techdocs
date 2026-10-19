package com.techdocs.document;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController @RequestMapping("/api/documents")
public class DocumentController {
 private final DocumentService service;
 public DocumentController(DocumentService service){this.service=service;}
 @GetMapping public List<TechnicalDocument> list(@RequestParam(required=false) String q,@RequestParam(required=false) String category,@RequestParam(required=false) DocumentStatus status){return service.findAll(q,category,status);}
 @GetMapping("/{id}") public TechnicalDocument get(@PathVariable Long id){return service.findById(id);}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public TechnicalDocument create(@Valid @RequestBody TechnicalDocument d){return service.create(d);}
 @PutMapping("/{id}") public TechnicalDocument update(@PathVariable Long id,@Valid @RequestBody TechnicalDocument d){return service.update(id,d);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
 @GetMapping("/stats") public DocumentService.DocumentStats stats(){return service.stats();}
 @GetMapping("/categories") public List<String> categories(){return service.findAll(null,null,null).stream().map(TechnicalDocument::getCategory).distinct().sorted().toList();}
 @GetMapping("/export") public List<Map<String,Object>> export(){return service.findAll(null,null,null).stream().map(d->Map.<String,Object>of("id",d.getId(),"title",d.getTitle(),"category",d.getCategory(),"author",d.getAuthor(),"status",d.getStatus(),"summary",d.getSummary()==null?"":d.getSummary(),"createdAt",d.getCreatedAt(),"updatedAt",d.getUpdatedAt())).toList();}
}
