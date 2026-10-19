package com.techdocs.document;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc @TestPropertySource(properties={"spring.datasource.url=jdbc:h2:mem:techdocs-test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1","spring.datasource.driver-class-name=org.h2.Driver","spring.datasource.username=sa","spring.datasource.password=","spring.jpa.hibernate.ddl-auto=create-drop"})
class DocumentControllerTest {
 @Autowired MockMvc mvc; @Autowired ObjectMapper mapper; @Autowired DocumentRepository repository;
 @BeforeEach void clean(){repository.deleteAll();}
 @Test void createsAndRetrievesDocument() throws Exception {String json=mapper.writeValueAsString(Map.of("title","Validar backup","category","Backup","author","Equipe de TI","summary","Conferir integridade","content","1. Conferir arquivo","status","PUBLISHED"));mvc.perform(post("/api/documents").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.title").value("Validar backup"));mvc.perform(get("/api/documents").param("q","backup")).andExpect(status().isOk()).andExpect(jsonPath("$[0].title").value("Validar backup"));}
 @Test void searchesByCategoryAndAuthor() throws Exception {String json=mapper.writeValueAsString(Map.of("title","Procedimento interno","category","Observabilidade","author","Equipe QA","content","Verificar alertas","status","DRAFT"));mvc.perform(post("/api/documents").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isCreated());mvc.perform(get("/api/documents").param("q","Observabilidade")).andExpect(status().isOk()).andExpect(jsonPath("$[0].category").value("Observabilidade"));mvc.perform(get("/api/documents").param("q","Equipe QA")).andExpect(status().isOk()).andExpect(jsonPath("$[0].author").value("Equipe QA"));}
 @Test void rejectsInvalidDocument() throws Exception {String json=mapper.writeValueAsString(Map.of("title","","category","Backup","author","Equipe","content","passo"));mvc.perform(post("/api/documents").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());}
 @Test void returnsNotFoundForUnknownDocument() throws Exception {mvc.perform(get("/api/documents/99999")).andExpect(status().isNotFound()).andExpect(jsonPath("$.title").value("Documento não encontrado"));}
}
