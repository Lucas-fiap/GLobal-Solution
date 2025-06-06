package org.acme.bo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.entity.ReportEntity;
import org.acme.exception.ReportException;
import org.acme.service.ReportService;

@ApplicationScoped
public class ReportBO {

    @Inject
    private ReportService reportService;

    public List<ReportEntity> listAll() {
        return reportService.listAll();
    }

    public List<ReportEntity> getReportsByEmail(String email) {
        return reportService.getReportsByEmail(email);
    }

    @Transactional
    public ReportEntity createReport(ReportEntity entity) {
        if (entity.getCidade() == null || entity.getCidade().trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser nula ou vazia.");
        }
        if (entity.getEmail() == null || entity.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio.");
        }
        if (entity.getNivelGravidade() == null) {
            throw new IllegalArgumentException("Nível de gravidade é obrigatório.");
        }
        return reportService.create(entity);
    }

    @Transactional
    public List<ReportEntity> updateReportsByEmail(String email, ReportEntity updatedData) {
            return reportService.updateByEmail(email, updatedData);
    }

    @Transactional
    public boolean deleteReportById(Integer id) {
        return reportService.deleteById(id);
    }
}
