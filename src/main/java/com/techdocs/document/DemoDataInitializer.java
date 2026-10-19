package com.techdocs.document;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataInitializer {
    @Bean
    CommandLineRunner seedDocuments(DocumentRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            repository.save(document("Checklist de diagnóstico de servidor Linux", "Linux", "Equipe de Infraestrutura",
                    "Sequência inicial para investigar indisponibilidade ou lentidão em um servidor.",
                    "Objetivo: coletar evidências antes de alterar o ambiente.\n\n1. Confirme o acesso ao host e o horário do incidente.\n2. Execute uptime e verifique a carga.\n3. Use df -h para conferir espaço em disco.\n4. Use free -h e top para observar memória e processos.\n5. Verifique os serviços com systemctl status NOME.\n6. Consulte journalctl -u NOME --since '30 minutes ago'.\n7. Registre comandos, horários e resultados no chamado.\n\nValidação: compare com a linha de base e confirme com o solicitante antes de encerrar.", DocumentStatus.PUBLISHED));
            repository.save(document("Validar integridade de backup PostgreSQL", "Backup", "Equipe de Banco de Dados",
                    "Passos para confirmar que um arquivo de backup foi gerado e está pronto para validação.",
                    "1. Confira o log da rotina e o código de retorno.\n2. Verifique data, tamanho e permissões do arquivo.\n3. Compare o tamanho com execuções anteriores.\n4. Para dumps customizados, execute pg_restore --list ARQUIVO.\n5. Registre o resultado e o local do arquivo.\n6. Faça teste de restauração em ambiente isolado conforme a política interna.\n\nAtenção: a existência do arquivo, sozinha, não comprova que a restauração funciona.", DocumentStatus.PUBLISHED));
            repository.save(document("Checklist para investigar falha de conectividade", "Redes", "Equipe de Suporte",
                    "Roteiro para separar falhas locais, DNS, rota e disponibilidade do destino.",
                    "1. Confirme endereço IP, gateway e interface de rede.\n2. Teste conectividade com o gateway.\n3. Verifique resolução DNS com nslookup ou dig.\n4. Teste a porta necessária com ferramenta aprovada pela equipe.\n5. Compare o resultado em outra máquina ou rede autorizada.\n6. Registre origem, destino, horário e mensagens de erro.\n\nNão altere regras de firewall sem autorização e plano de rollback.", DocumentStatus.PUBLISHED));
            repository.save(document("Procedimento de reinício controlado de serviço", "Aplicações", "Equipe de Sistemas",
                    "Como reiniciar um serviço de forma controlada e validar a recuperação.",
                    "Pré-requisitos: autorização, janela aprovada e plano de retorno.\n\n1. Identifique dependências e impacto.\n2. Registre o estado inicial e os logs relevantes.\n3. Reinicie apenas o serviço previsto no procedimento local.\n4. Verifique status, logs e endpoint de saúde.\n5. Confirme a funcionalidade com o usuário ou monitoramento.\n6. Se não recuperar, execute o plano de rollback e escale com as evidências.", DocumentStatus.DRAFT));
            repository.save(document("Rotina de revisão de espaço em disco", "Linux", "Equipe de Infraestrutura",
                    "Pontos de controle para detectar crescimento de disco antes de afetar aplicações.",
                    "1. Consulte df -h e identifique os pontos de montagem críticos.\n2. Use du -xhd1 CAMINHO para localizar diretórios maiores, conforme disponibilidade da ferramenta.\n3. Confira logs rotacionados e arquivos temporários segundo a política local.\n4. Não remova arquivos de aplicação ou banco sem validação do responsável.\n5. Registre tendência de crescimento e abra ação preventiva quando necessário.", DocumentStatus.DRAFT));
            repository.save(document("Modelo de registro de mudança técnica", "Operações", "Equipe de TI",
                    "Estrutura mínima para documentar uma mudança e facilitar análise posterior.",
                    "Objetivo e escopo:\n- Serviço/servidor afetado:\n- Responsável e aprovador:\n- Janela planejada:\n- Pré-requisitos e backup:\n- Etapas de execução:\n- Validação pós-mudança:\n- Plano de rollback:\n- Evidências e resultado final:", DocumentStatus.ARCHIVED));
        };
    }

    private TechnicalDocument document(String title, String category, String author, String summary, String content, DocumentStatus status) {
        TechnicalDocument d = new TechnicalDocument();
        d.setTitle(title); d.setCategory(category); d.setAuthor(author); d.setSummary(summary); d.setContent(content); d.setStatus(status);
        return d;
    }
}
