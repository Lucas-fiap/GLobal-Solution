package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.entity.ReportEntity;
import org.acme.exception.ReportException;
import org.acme.repository.ReportRepository;

@ApplicationScoped
public class ReportService {

    @Inject
    private ReportRepository reportRepository;

    public List<ReportEntity> listAll() {
        return reportRepository.listAll();
    }

    public List<ReportEntity> getReportsByEmail(String email) {
        return reportRepository.findByEmail(email);
    }

    @Transactional
    public ReportEntity create(ReportEntity entity) {
        List<ReportEntity> existentes = reportRepository.findByEmail(entity.getEmail());
        if (existentes != null && !existentes.isEmpty()) {
            throw new ReportException(
                    "Já existe um report cadastrado com o e-mail: " + entity.getEmail()
            );
        }
        reportRepository.persist(entity);
        return entity;
    }

    @Transactional
    public List<ReportEntity> updateByEmail(String oldEmail, ReportEntity updatedData) {
        List<ReportEntity> listaExistentes = reportRepository.findByEmail(oldEmail);

        if (listaExistentes == null || listaExistentes.isEmpty()) {
            throw new ReportException("Não há nenhum report com o e-mail: " + oldEmail);
        }

        String novoEmail = updatedData.getEmail();
        if (novoEmail != null && !novoEmail.trim().isEmpty()) {
            boolean emailDiferente = !novoEmail.equalsIgnoreCase(oldEmail);
            if (emailDiferente) {
                List<ReportEntity> jaExisteComNovoEmail = reportRepository.findByEmail(novoEmail);
                if (jaExisteComNovoEmail != null && !jaExisteComNovoEmail.isEmpty()) {
                    throw new ReportException(
                            "Já existe outro report cadastrado com o e-mail: " + novoEmail
                    );
                }
            }
        }

        for (ReportEntity entidade : listaExistentes) {
            if (novoEmail != null && !novoEmail.trim().isEmpty()) {
                entidade.setEmail(novoEmail);
            }
            entidade.setName(updatedData.getName());
            entidade.setCidade(updatedData.getCidade());
            entidade.setLatitude(updatedData.getLatitude());
            entidade.setLongitude(updatedData.getLongitude());
            entidade.setNivelGravidade(updatedData.getNivelGravidade());
        }
        return listaExistentes;
    }

    @Transactional
    public boolean deleteById(Integer id) {
        ReportEntity entity = reportRepository.findById(id);
        if (entity == null) {
            return false;
        }
        reportRepository.delete(entity);
        return true;
    }
}
