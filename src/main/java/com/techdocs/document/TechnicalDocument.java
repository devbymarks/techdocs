package com.techdocs.document;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.Instant;
@Entity
@Table(name="technical_documents", indexes={@Index(name="idx_document_title",columnList="title"),@Index(name="idx_document_category",columnList="category")})
public class TechnicalDocument {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=140) @Column(nullable=false,length=140) private String title;
 @NotBlank @Size(max=80) @Column(nullable=false,length=80) private String category;
 @NotBlank @Size(max=80) @Column(nullable=false,length=80) private String author;
 @NotBlank @Size(max=12000) @Column(nullable=false,length=12000) private String content;
 @Size(max=500) @Column(length=500) private String summary;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private DocumentStatus status=DocumentStatus.DRAFT;
 @Column(nullable=false,updatable=false) private Instant createdAt;
 @Column(nullable=false) private Instant updatedAt;
 @PrePersist void onCreate(){Instant now=Instant.now();createdAt=now;updatedAt=now;if(status==null)status=DocumentStatus.DRAFT;}
 @PreUpdate void onUpdate(){updatedAt=Instant.now();}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getTitle(){return title;} public void setTitle(String v){title=v==null?null:v.trim();}
 public String getCategory(){return category;} public void setCategory(String v){category=v==null?null:v.trim();}
 public String getAuthor(){return author;} public void setAuthor(String v){author=v==null?null:v.trim();}
 public String getContent(){return content;} public void setContent(String v){content=v;}
 public String getSummary(){return summary;} public void setSummary(String v){summary=v==null?null:v.trim();}
 public DocumentStatus getStatus(){return status;} public void setStatus(DocumentStatus v){status=v;}
 public Instant getCreatedAt(){return createdAt;} public Instant getUpdatedAt(){return updatedAt;}
}
